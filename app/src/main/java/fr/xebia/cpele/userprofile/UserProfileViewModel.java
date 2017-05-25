package fr.xebia.cpele.userprofile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

class UserProfileViewModel extends ViewModel {

    private LiveData<User> user;

    LiveData<User> getUser() {
        return user;
    }
}
