package com.cliknfix.user.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.base.MyApp;
import com.cliknfix.user.forgotPassword.ForgotPasswordActivity;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.mobile.MobileNoActivity;
import com.cliknfix.user.otp.OtpActivity;
import com.cliknfix.user.responseModels.LoginResponseModel;
import com.cliknfix.user.signUp.SignUpActivity;
import com.cliknfix.user.util.PreferenceHandler;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseClass implements ILoginActivity {

    ProgressDialog progressDialog;

    @BindView(R.id.login_text)
    TextView tvLoginText;
    @BindView(R.id.signUp)
    TextView tvSignUpText1;
    @BindView(R.id.signUpClick)
    TextView tvSignUpText2;
    @BindView(R.id.et_email_login)
    EditText etEmail;
    @BindView(R.id.et_password_login)
    EditText etPassword;
    @BindView(R.id.iv_password)
    ImageView ivPassword;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.btn_signin)
    Button btnLogin;

    IPLogin iPresenterLogin;

    Boolean passVisible = false;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        iPresenterLogin = new PLogin(this);
        init();
    }

    public void onLoginClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if (etEmail.getText().toString().length()>0 && etPassword.getText().toString().length()>0 ) {
                if (Utility.validEmail(etEmail.getText().toString().trim())) {
                    progressDialog = Utility.showLoader(this);
                    iPresenterLogin.doLogin(etEmail.getText().toString().trim().toLowerCase(),etPassword.getText().toString().trim(),token);
                } else {
                    etEmail.setError("Enter a valid email.");
                    etEmail.requestFocus();
                }
            } else {
                if (etEmail.getText().toString().length()==0 && etPassword.getText().toString().length()==0)
                {
                    etEmail.setError("Enter email.");
                    etPassword.setError("Enter password");
                    etEmail.requestFocus();
                }
                else if (etPassword.getText().toString().length()==0)
                {
                    etPassword.setError("Enter password");
                    etPassword.requestFocus();
                }
                else if (etEmail.getText().toString().length()==0)
                {
                    etEmail.setError("Enter email.");
                    etEmail.requestFocus();
                }
            }
        } else {
            Toast.makeText(this, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }



    @Override
    public void onLoginSuccessFromPresenter(LoginResponseModel userModelLoginResponse) {
        progressDialog.dismiss();
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    @Override
    public void otpNotVerifiedFromPresenter(LoginResponseModel loginResponseModel) {
        progressDialog.dismiss();
        if(loginResponseModel.getData().get(0).getEmailVerifiedAt() == null){
            Intent intent = new Intent(LoginActivity.this, MobileNoActivity.class);
            intent.putExtra("phone", "" +loginResponseModel.getData().get(0).getPhone().toString().trim());
            intent.putExtra("userId", "" +loginResponseModel.getData().get(0).getId().toString().trim());
            startActivity(intent);
        } else if(loginResponseModel.getData().get(0).getEmailVerifiedAt().equals("1")) {
            Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
            intent.putExtra("phone", "" +loginResponseModel.getData().get(0).getPhone().toString().trim());
            intent.putExtra("userId", "" +loginResponseModel.getData().get(0).getId().toString().trim());
            startActivity(intent);
        }
    }

    @Override
    public void onLoginFailedFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }


    public void init()
    {
        etEmail.setTypeface(Utility.typeFaceForText(this));
        etPassword.setTypeface(Utility.typeFaceForText(this));
        tvLoginText.setTypeface(Utility.typeFaceForBoldText(this));
        btnLogin.setTypeface(Utility.typeFaceForBoldText(this));
        tvSignUpText1.setTypeface(Utility.typeFaceForBoldText(this));
        tvSignUpText2.setTypeface(Utility.typeFaceForBoldText(this));

        ivPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passVisible)
                {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    etPassword.setSelection(etPassword.length());
                    passVisible = true;
                }
                else
                {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etPassword.setSelection(etPassword.length());
                    passVisible = false;
                }
            }
        });

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    if (etEmail.getText().toString().length()>0) {
                        if (!Utility.validEmail(etEmail.getText().toString().trim()))
                            etEmail.setError("Invalid email");
                    } else {
                        etEmail.setError(null);
                    }
                }
            }
        });
        Log.e("init","working");
        startService(new Intent(this, LoginFirebaseMessagingService.class));
        token = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
        Log.e("token:",token);
        Toast.makeText(this, "Token:" + token, Toast.LENGTH_SHORT).show();
    }

    public void onSignUpClicked(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void onForgotPasswordClicked(View view) {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }
}
