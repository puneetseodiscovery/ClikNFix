package com.cliknfix.user.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.TestActivity;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.base.MyApp;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.loginSignup.LoginSignupActivity;
import com.cliknfix.user.util.PreferenceHandler;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class SplashActivity extends BaseClass {

    String session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                session = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                //Toast.makeText(SplashActivity.this, "Session:"+session, Toast.LENGTH_SHORT).show();
                if(session.length()>0){
                    Intent mainIntent = new Intent(SplashActivity.this, HomeScreenActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                } else {
                    Intent mainIntent = new Intent(SplashActivity.this, LoginSignupActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
//                Intent mainIntent = new Intent(SplashActivity.this, LoginSignupActivity.class);
//                SplashActivity.this.startActivity(mainIntent);
//                SplashActivity.this.finish();
            }
        }, 1000);
    }
}
