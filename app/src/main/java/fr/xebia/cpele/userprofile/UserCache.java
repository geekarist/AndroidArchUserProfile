package fr.xebia.cpele.userprofile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;

class UserCache {

    private HashMap<String, LiveData<User>> mUsersById = new HashMap<>();

    public LiveData<User> get(final String userId) {
        return mUsersById.get(userId);
    }

    public void put(final String userId, final MutableLiveData<User> data) {
        mUsersById.put(userId, data);
    }
}
