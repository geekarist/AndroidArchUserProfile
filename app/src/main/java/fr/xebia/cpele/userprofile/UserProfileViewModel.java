package fr.xebia.cpele.userprofile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

class UserProfileViewModel extends ViewModel {

    private UserRepository mUserRepo;

    private LiveData<User> mUser;

    LiveData<User> getUser() {
        return mUser;
    }

    void init(String userId) {
        if (mUser != null) return;
        mUser = mUserRepo.fetchUser(userId);
    }

    void setUserRepo(final UserRepository userRepo) {
        mUserRepo = userRepo;
    }
}
