package fr.xebia.cpele.userprofile;

import android.arch.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {

    private String userId;
    private User user;

    public void init(String userId) {
        userId = userId;
    }
}
