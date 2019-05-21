package com.cliknfix.user.mobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.login.LoginActivity;
import com.cliknfix.user.otp.OtpActivity;
import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.util.Utility;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileNoActivity extends BaseClass implements IMobileNoActivity
{
    @BindView(R.id.tv_mobile_screen_text)
    TextView tvP1;
    @BindView(R.id.tv_country_code)
    TextView tvCC;
    @BindView(R.id.tv_mobile_no)
    TextView tvMobile;

    IPMobileActivity ipMobileActivity;
    ProgressDialog progressDialog;
    String phone;
    String mobileNo,user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_no);
        ButterKnife.bind(this);
        ipMobileActivity = new PMobileActivity(this);
        init();
    }

    public void init() {
        tvP1.setTypeface(Utility.typeFaceForText(this));
        //tvMobile.setText("9463924817");
        mobileNo = getIntent().getStringExtra("phone");
        user_id = getIntent().getStringExtra("userId");
        Log.e("Mobile phone","" + mobileNo);
        Log.e("Mobile password","" + user_id);
        tvMobile.setText(mobileNo);
    }

    public void onNextClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            progressDialog = Utility.showLoader(this);
            phone = tvCC.getText()+ " " + tvMobile.getText().toString().trim().toLowerCase();
            ipMobileActivity.sendOTP(phone,user_id,"0");
        }
    }

    public void onSendOTPSuccessFromPresenter(MobileNoResponseModel mobileNoResponseModel) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + mobileNoResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MobileNoActivity.this, OtpActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("userId", user_id);
        startActivity(intent);
    }

    public void onSendOTPFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, OtpActivity.class));
    }
}
