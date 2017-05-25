package fr.xebia.cpele.userprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserProfileFragment extends Fragment {

    public static final String KEY_USER_ID = "USER_ID";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);
        String userId = getArguments().getString(KEY_USER_ID);
        TextView userIdTextView = (TextView) view.findViewById(R.id.userprofile_tv_userid);
        userIdTextView.setText(getString(R.string.userprofile_greeting, userId));
        return view;
    }

    public static Fragment newInstance(String userId) {
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        Bundle args = new Bundle();
        args.putString(KEY_USER_ID, userId);
        userProfileFragment.setArguments(args);
        return userProfileFragment;
    }
}
