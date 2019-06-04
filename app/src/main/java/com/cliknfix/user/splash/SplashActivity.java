package com.cliknfix.user.splash;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;


public class SplashActivity extends BaseClass {

    String session;
    String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
                // do your task.
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
                    }
                }, 1000);
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                super.onDenied(context, deniedPermissions);
                finishAffinity();
            }
        });
    }
}
