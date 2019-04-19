package com.cliknfix.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cliknfix.R;
import com.cliknfix.base.BaseClass;
import com.cliknfix.homeScreen.HomeScreenActivity;
import com.cliknfix.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpActivity extends BaseClass
{
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
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
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    public void onResendOtpClicked(View view) {
    }

}
