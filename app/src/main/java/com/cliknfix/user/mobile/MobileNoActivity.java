package com.cliknfix.user.mobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
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
    @BindView(R.id.et_mobile_no)
    EditText etMobile;

    IPMobileActivity ipMobileActivity;
    ProgressDialog progressDialog;
    String phone;

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
    }

    public void onNextClicked(View view) {
        //startActivity(new Intent(this, OtpActivity.class));
        if (Utility.isNetworkConnected(this)) {
            if (etMobile.getText().toString().length()>0){
                if (Utility.validMobile(etMobile.getText().toString().trim())){
                    progressDialog = Utility.showLoader(this);
                     phone = tvCC.getText()+ " " + etMobile.getText().toString().trim().toLowerCase();
                    ipMobileActivity.sendOTP(phone);
                } else {
                    etMobile.setError("Enter  valid Mobile Number");
                    etMobile.requestFocus();
                }
            } else {
                etMobile.setError("Enter Mobile Number");
                etMobile.requestFocus();
            }
        }
    }

    public void onSendOTPSuccessFromPresenter(MobileNoResponseModel mobileNoResponseModel) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + mobileNoResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MobileNoActivity.this, OtpActivity.class);
        //intent.putExtra("otp", mobileNoResponseModel.getData().get0());
        intent.putExtra("phone", phone);
        startActivity(intent);
    }

    public void onSendOTPFailureFromPresenter(String message) {

    }
}
