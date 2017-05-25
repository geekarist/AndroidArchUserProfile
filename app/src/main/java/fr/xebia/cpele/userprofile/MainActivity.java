package fr.xebia.cpele.userprofile;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment userProfileFragment = UserProfileFragment.newInstance("cpele");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fl_container_userprofile, userProfileFragment)
                .commit();
    }
}
