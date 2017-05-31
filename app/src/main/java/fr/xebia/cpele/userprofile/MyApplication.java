package fr.xebia.cpele.userprofile;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        final UserProfileApi api = retrofit.create(UserProfileApi.class);

        final Executor executor = Executors.newFixedThreadPool(2);
        MyDatabase myDb = Room.databaseBuilder(this, MyDatabase.class, MyDatabase.class.getSimpleName()).build();
        final UserDao userDao = myDb.userDao();
        mUserRepo = new UserRepository(api, userDao, executor);

        if (sInstance == null) sInstance = this;
    }

    public UserRepository getUserRepo() {
        return mUserRepo;
    }
}
