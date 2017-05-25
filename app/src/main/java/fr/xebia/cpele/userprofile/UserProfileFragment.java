package fr.xebia.cpele.userprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);
        String userId = "cpele";
        TextView userIdTextView = (TextView) view.findViewById(R.id.userprofile_tv_userid);
        userIdTextView.setText(getString(R.string.userprofile_greeting, userId));
        return view;
    }
}
