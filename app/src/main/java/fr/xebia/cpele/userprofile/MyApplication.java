package fr.xebia.cpele.userprofile;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private static MyApplication sInstance;

    public static MyApplication get() {
        return sInstance;
    }

    private UserRepository mUserRepo;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final UserProfileApi api = retrofit.create(UserProfileApi.class);

        mUserRepo = new UserRepository(api);

        if (sInstance == null) sInstance = this;
    }

    public UserRepository getUserRepo() {
        return mUserRepo;
    }
}
