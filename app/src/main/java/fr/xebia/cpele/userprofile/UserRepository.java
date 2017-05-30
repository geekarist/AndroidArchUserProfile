package fr.xebia.cpele.userprofile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class UserRepository {

    private UserProfileApi mApi;
    private UserCache mUserCache = new UserCache();

    UserRepository(UserProfileApi api) {
        mApi = api;
    }

    LiveData<User> fetchUser(String userId) {

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
