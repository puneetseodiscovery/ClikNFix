package com.cliknfix.user.otp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.login.LoginActivity;
import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.responseModels.OTPResponseModel;
import com.cliknfix.user.util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String val = "";
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    String message;
    String phone,mobileNo;
    String user_id;

    String socialMediaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        ipOtp = new POtp(this);
        /*if (checkForPermission()) {
            init();
        }*/
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

        socialMediaLogin = getIntent().getStringExtra("socialMedia");

        phone = getIntent().getExtras().getString("phone");
        user_id = getIntent().getExtras().getString("userId");
        /*LocalBroadcastManager.getInstance(this).
                registerReceiver(receiver, new IntentFilter("otp"));*/

        setRequestFocus();
    }

    private void setRequestFocus() {
        etOTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etOTP1.getText().toString().length()>0)
                    etOTP2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etOTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etOTP2.getText().toString().length()>0)
                    etOTP3.requestFocus();

                if(etOTP2.getText().toString().length() == 0)
                    etOTP1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etOTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etOTP3.getText().toString().length()>0)
                    etOTP4.requestFocus();

                if(etOTP3.getText().toString().length() == 0)
                    etOTP2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etOTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etOTP4.getText().toString().length() == 0)
                    etOTP3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onSubmitClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if (etOTP1.getText().toString().length()>0 && etOTP2.getText().toString().length()>0
                    && etOTP3.getText().toString().length()>0 && etOTP4.getText().toString().length()>0) {
                String otp = etOTP1.getText().toString().trim().toLowerCase() + etOTP2.getText().toString().trim().toLowerCase()
                        + etOTP3.getText().toString().trim().toLowerCase() + etOTP4.getText().toString().trim().toLowerCase();
                progressDialog = Utility.showLoader(this);
                //mobileNo = "+91 " + phone;
                ipOtp.fillOTP(phone,otp,user_id);
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
        ipOtp.resendOTP(phone, user_id, "1");
    }

    @Override
    public void onFillOTPSuccessFromPresenter(OTPResponseModel otpResponseModel) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + otpResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        if(socialMediaLogin.equalsIgnoreCase("1"))
            startActivity(new Intent(this, HomeScreenActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onFillOTPFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResendOTPSuccessFromPresenter(MobileNoResponseModel mobileNoResponseModel) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + mobileNoResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResendOTPFailureFromPresenter(String msgg) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Resgister","working");
       // LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        BroadcastReceiver br = new SMSBroadcastReceiver();
        registerReceiver(br,new IntentFilter("otp"));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("UnResgister","working");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("onReceive","working");
            if (intent.getAction().equalsIgnoreCase("otp")) {
                message = intent.getStringExtra("message");
                Log.e("Receive OTP Message:",message);
                Log.e("OTP phone","" + phone);
                Log.e("OTP password","" + user_id);
                if(phone!=null && user_id!=null)
                    fillOTP();
            }
        }
    };

    private void fillOTP() {
        Log.e("fillOTP","phone:");
        Pattern pattern = Pattern.compile("(\\d{4})");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            val = matcher.group(0);  // 4 digit number
        }
        Log.e("OTP:",val);

        String [] arr = val.split("");
        if(arr.length == 4) {
            etOTP1.setText(arr[0]);
            etOTP2.setText(arr[1]);
            etOTP3.setText(arr[2]);
            etOTP4.setText(arr[3]);
        }
        Log.e("OTP VAL","" + arr);
    }
}
