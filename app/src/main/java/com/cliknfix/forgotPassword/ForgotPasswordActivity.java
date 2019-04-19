package com.cliknfix.forgotPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.R;
import com.cliknfix.base.BaseClass;
import com.cliknfix.login.LoginActivity;
import com.cliknfix.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends BaseClass implements IForgotPasswordActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_forgot_pass)
    TextView tvForgotPass;
    @BindView(R.id.tv_ptext)
    TextView tvP1;
    @BindView(R.id.et_email_forgot_pass)
    EditText etEmail;
    @BindView(R.id.btn_reset_pass)
    Button btnReset;

    IPForgotPasswordActivity ipForgotPasswordActivity;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        init();
        ipForgotPasswordActivity = new PForgotPasswordActivity(this);
    }

    public  void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvForgotPass.setTypeface(Utility.typeFaceForBoldText(this));
        etEmail.setTypeface(Utility.typeFaceForText(this));
        tvP1.setTypeface(Utility.typeFaceForText(this));
        btnReset.setTypeface(Utility.typeFaceForBoldText(this));
    }

    @Override
    public void onForgotPasswordResponseFromPresenter(String statusValue) {

        progressDialog.dismiss();
        Toast.makeText(this, ""+statusValue, Toast.LENGTH_SHORT).show();
        //phoneNumber.setText("");
        new CountDownTimer(2000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                cancel();
            }
        }.start();

    }

    @Override
    public void onForgotPasswordResponseFailedFromPresenter(String failedMessage) {
        Toast.makeText(this, ""+failedMessage, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    public void onBackClicked(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void onResetPasswordClicked(View view) {

    }

}
