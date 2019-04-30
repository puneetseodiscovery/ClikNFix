package com.cliknfix.user.homeScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.homeScreen.bottomFragments.HomeFragment;
import com.cliknfix.user.homeScreen.bottomFragments.PastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.SettingsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.UserProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenActivity extends BaseClass {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    int defaultTab=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        defaultTab = getIntent().getIntExtra("DefaultTab",0);
        init();
        if(defaultTab == 1) {
            navigation.getMenu().findItem(R.id.navigation_settings).setChecked(true);
            loadFragment(new SettingsFragment());
        }

    }

    private void init() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_settings:
                    loadFragment(new SettingsFragment());
                    return true;
                case R.id.navigation_past_jobs:
                    loadFragment(new PastJobsFragment());
                    return true;
                case R.id.navigation_user_profile:
                    loadFragment(new UserProfileFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //backPressClicked();

    }

    public void backPressClicked() {
        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        if(backStackCount ==0){
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }
}