package fr.xebia.cpele.userprofile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class UserRepository {

    private static final int FRESH_TIMEOUT = 10;

    private final Executor mExecutor;
    private UserProfileApi mApi;
    private UserCache mUserCache = new UserCache();
    private UserDao mUserDao;

    UserRepository(UserProfileApi api, final UserDao userDao, final Executor executor) {
        mApi = api;
        mUserDao = userDao;
        mExecutor = executor;
    }

    LiveData<User> fetchUser(String userId) {
        refreshUser(userId);
        return mUserDao.load(userId);
    }

    private void refreshUser(final String userId) {

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mUserDao.hasUser(userId)) return;
                    Response<User> response = mApi.fetchUser(userId).execute();
                    User user = response.body();
                    mUserDao.save(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    LiveData<User> fetchUser2(String userId) {

        LiveData<User> cached = mUserCache.get(userId);
        if (cached != null) return cached;

        final MutableLiveData<User> data = new MutableLiveData<>();
        mUserCache.put(userId, data);

        mApi.fetchUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
