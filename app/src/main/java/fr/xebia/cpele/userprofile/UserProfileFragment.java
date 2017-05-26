package fr.xebia.cpele.userprofile;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserProfileFragment extends LifecycleFragment {

    public static final String KEY_USER_ID = "USER_ID";

    private UserProfileViewModel mViewModel;
    private TextView mUserIdTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);
        mUserIdTextView = (TextView) view.findViewById(R.id.userprofile_tv_userid);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        mViewModel.setUserRepo(MyApplication.get().getUserRepo());
        mViewModel.init(String.valueOf("geekarist"));

        mViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    mUserIdTextView.setText(getString(R.string.userprofile_greeting, user.getName()));
                }
            }
        });
    }

    public static Fragment newInstance() {
        return new UserProfileFragment();
    }
}
