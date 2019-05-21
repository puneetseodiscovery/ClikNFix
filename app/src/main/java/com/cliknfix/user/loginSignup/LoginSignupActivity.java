package com.cliknfix.user.loginSignup;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.login.LoginActivity;
import com.cliknfix.user.mobile.MobileNoActivity;
import com.cliknfix.user.signUp.SignUpActivity;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginSignupActivity extends BaseClass {

    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.tv_welcome_pText)
    TextView tvP1;
    @BindView(R.id.btn_login_main)
    Button btnLogin;
    @BindView(R.id.btn_signup_main)
    Button btnSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvWelcome.setTypeface(Utility.typeFaceForText(this));
        tvP1.setTypeface(Utility.typeFaceForText(this));
        btnLogin.setTypeface(Utility.typeFaceForBoldText(this));
        btnSignUp.setTypeface(Utility.typeFaceForBoldText(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            startActivity(new Intent(this, LoginSignupActivity.class));
        }
    }

    public void onLoginClicked(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onSignupClicked(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void onSkipClicked(View view){
        startActivity(new Intent(this, MobileNoActivity.class));
    }
}
