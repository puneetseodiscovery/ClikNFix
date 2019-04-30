package com.cliknfix.user.forgotPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.login.LoginActivity;
import com.cliknfix.user.responseModels.ForgotPasswordResponseModel;
import com.cliknfix.user.util.Utility;

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

    public void onBackClicked(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void onResetPasswordClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if (etEmail.getText().toString().length()>0) {
                if (Utility.validEmail(etEmail.getText().toString().trim())) {
                    progressDialog = Utility.showLoader(this);
                    ipForgotPasswordActivity.resetPassword(etEmail.getText().toString().trim().toLowerCase());
                } else {
                    etEmail.setError("Enter a valid email.");
                    etEmail.requestFocus();
                }
            } else {
                etEmail.setError("Enter email.");
                etEmail.requestFocus();
            }

        } else {
            Toast.makeText(this, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onForgotPasswordSuccessResponseFromPresenter(ForgotPasswordResponseModel forgotPasswordResponseModel) {
        progressDialog.dismiss();
        Log.e("Email Sent","Success");
        Toast.makeText(this, "" + forgotPasswordResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void onForgotPasswordFailureResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Log.e("Email Not Sent","Failure");
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }
}
