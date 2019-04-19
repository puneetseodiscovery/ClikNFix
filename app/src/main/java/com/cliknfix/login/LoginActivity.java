package com.cliknfix.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.cliknfix.R;
import com.cliknfix.base.BaseClass;
import com.cliknfix.base.MyApp;
import com.cliknfix.forgotPassword.ForgotPasswordActivity;
import com.cliknfix.homeScreen.HomeScreenActivity;
import com.cliknfix.mobile.MobileNoActivity;
import com.cliknfix.responseModels.UserModelLoginResponse;
import com.cliknfix.signUp.SignUpActivity;
import com.cliknfix.util.PreferenceHandler;
import com.cliknfix.util.Utility;

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
    //int treatmentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        iPresenterLogin = new PLogin(this);
        init();
    }

    public void onLoginClicked(View view) {
        startActivity(new Intent(this, MobileNoActivity.class));
        /*if (Utility.isNetworkConnected(this)) {
            if (etEmail.getText().toString().length()>0 && etPassword.getText().toString().length()>0 ) {


                if (Utility.validEmail(etEmail.getText().toString().trim())) {

                    progressDialog = Utility.showLoader(this);
                    iPresenterLogin.doLogin(etEmail.getText().toString().trim().toLowerCase(),etPassword.getText().toString().trim());

                } else {
                    etEmail.setError("Enter a valid email.");
                    etEmail.requestFocus();
                }
            }
            else 
            {
                if (etEmail.getText().toString().length()==0 && etPassword.getText().toString().length()==0)
                {
                    etEmail.setError("Enter a valid email.");
                    etEmail.setError("Enter password");
                    etEmail.requestFocus();
                }
                else if (etPassword.getText().toString().length()==0)
                {
                    etPassword.setError("Enter password");
                    etPassword.requestFocus();
                }
                else if (etEmail.getText().toString().length()==0)
                {
                    etEmail.setError("Enter a valid email.");
                    etEmail.requestFocus();
                }
                //Toast.makeText(this, "Enter correct login details.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }*/


    }



    @Override
    public void onLoginResponseFromPresenter(int statusValue) {

        Toast.makeText(this, ""+statusValue, Toast.LENGTH_SHORT).show();
       // startActivity(new Intent(this,HomeActivity.class));
    }

    @Override
    public void onLoginSuccessFromPresenter(UserModelLoginResponse userModelLoginResponse) {
        progressDialog.dismiss();
        new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_USER_NAME, userModelLoginResponse.getData().getName());
       /* if (treatmentActivity==0)
        {
            //startActivity(new Intent(this,HomeActivity.class));
            finish();
        }else
        {
            onBackPressed();
            finish();
        }*/
    }

    @Override
    public void onLoginFailedFromPresenter(String message) {

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }


    public void init()
    {
        etEmail.setTypeface(Utility.typeFaceForText(this));
        etPassword.setTypeface(Utility.typeFaceForText(this));
        tvLoginText.setTypeface(Utility.typeFaceForBoldText(this));
        btnLogin.setTypeface(Utility.typeFaceForBoldText(this));
        tvSignUpText1.setTypeface(Utility.typeFaceForBoldText(this));
        tvSignUpText2.setTypeface(Utility.typeFaceForBoldText(this));

        Intent intent = getIntent();

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
                        etEmail.requestFocus();
                    } else {
                        etEmail.setError(null);
                    }
                }
            }
        });
    }

    public void onSignUpClicked(View view) {

        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void onForgotPasswordClicked(View view) {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }
}
