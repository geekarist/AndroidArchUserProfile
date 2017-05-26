package fr.xebia.cpele.userprofile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

class UserRepository {

    private UserProfileApi mApi;

    UserRepository(UserProfileApi api) {
        mApi = api;
    }

    LiveData<User> fetchUser(String userId) {

        final MutableLiveData<User> data = new MutableLiveData<>();

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
