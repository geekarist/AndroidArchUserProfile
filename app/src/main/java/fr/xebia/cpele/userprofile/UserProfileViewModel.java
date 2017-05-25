package fr.xebia.cpele.userprofile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

class UserProfileViewModel extends ViewModel {

    private UserRepository mUserRepo;

    private LiveData<User> mUser;

    public UserProfileViewModel() {
        mUserRepo = MyApplication.get().getUserRepo();
    }

    LiveData<User> getUser() {
        return mUser;
    }

    public void init(String userId) {
        if (mUser != null) return;
        mUser = mUserRepo.fetchUser(userId);
    }
}
