package com.cliknfix.user.otp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.login.LoginActivity;
import com.cliknfix.user.responseModels.OTPResponseModel;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpActivity extends BaseClass implements IOtpActivity {
    @BindView(R.id.tv_vpno)
    TextView tvVP;
    @BindView(R.id.tv_send_otp)
    TextView tvSend;
    @BindView(R.id.tv_otp_country_code)
    TextView tvCC;
    @BindView(R.id.tv_otp_phoneno)
    TextView tvPhone;
    @BindView(R.id.et_otp1)
    EditText etOTP1;
    @BindView(R.id.et_otp2)
    EditText etOTP2;
    @BindView(R.id.et_otp3)
    EditText etOTP3;
    @BindView(R.id.et_otp4)
    EditText etOTP4;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_resend_otp)
    TextView tvResendOTP;

    ProgressDialog progressDialog;
    IPOtp ipOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        ipOtp = new POtp(this);
        init();
    }

    public void init() {
        tvVP.setTypeface(Utility.typeFaceForText(this));
        tvSend.setTypeface(Utility.typeFaceForText(this));
        tvCC.setTypeface(Utility.typeFaceForText(this));
        tvPhone.setTypeface(Utility.typeFaceForText(this));
        btnSubmit.setTypeface(Utility.typeFaceForBoldText(this));
        tvResendOTP.setTypeface(Utility.typeFaceForText(this));

        etOTP1.setSelection(0);
        etOTP2.setSelection(0);
        etOTP3.setSelection(0);
        etOTP4.setSelection(0);
    }

    public void onSubmitClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if (etOTP1.getText().toString().length()>0 && etOTP2.getText().toString().length()>0
                    && etOTP3.getText().toString().length()>0 && etOTP4.getText().toString().length()>0) {
                String otp = etOTP1.getText().toString().trim().toLowerCase() + etOTP2.getText().toString().trim().toLowerCase()
                        + etOTP3.getText().toString().trim().toLowerCase() + etOTP4.getText().toString().trim().toLowerCase();
                progressDialog = Utility.showLoader(this);
                ipOtp.fillOTP(otp,getIntent().getExtras().getString("phone"));
            } else {
                etOTP4.setError("Enter OTP Number");
                etOTP4.requestFocus();
            }
        } else {
            Toast.makeText(this, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void onResendOtpClicked(View view) {
    }

    @Override
    public void onFillOTPSuccessFromPresenter(OTPResponseModel otpResponseModel) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + otpResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onFillOTPFailureFromPresenter(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }
}
