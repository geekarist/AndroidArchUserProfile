package fr.xebia.cpele.userprofile;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication sInstance;

    public static MyApplication get() {
        return sInstance;
    }

    private UserRepository mUserRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        mUserRepo = new UserRepository();
        if (sInstance == null) sInstance = this;
    }

    public UserRepository getUserRepo() {
        return mUserRepo;
    }
}
