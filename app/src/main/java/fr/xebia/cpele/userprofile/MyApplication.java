package fr.xebia.cpele.userprofile;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull final String message) {
                Log.d(MyApplication.this.getClass().getSimpleName(), message);
            }
        });

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        final UserProfileApi api = retrofit.create(UserProfileApi.class);

        mUserRepo = new UserRepository(api);

        if (sInstance == null) sInstance = this;
    }

    public UserRepository getUserRepo() {
        return mUserRepo;
    }
}
