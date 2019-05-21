package com.cliknfix.user.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.base.MyApp;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.loginSignup.LoginSignupActivity;
import com.cliknfix.user.util.PreferenceHandler;


public class SplashActivity extends BaseClass {

    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                token = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                /*if(token!=null){
                    Intent mainIntent = new Intent(SplashActivity.this, HomeScreenActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                } else {*/
                    Intent mainIntent = new Intent(SplashActivity.this, LoginSignupActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                //}
            }
        }, 1000);
    }
}
