package com.cliknfix.technicianDetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cliknfix.R;
import com.cliknfix.base.BaseClass;
import com.cliknfix.chat.ChatActivity;
import com.cliknfix.homeScreen.HomeScreenActivity;
import com.cliknfix.paymentMethods.PaymentMethodsActivity;
import com.cliknfix.trackLocation.TechnicianLocationActivity;
import com.cliknfix.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TechnicianDetailActivity extends BaseClass {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_technician_name)
    TextView tvTechName;
    @BindView(R.id.tv_rate_text)
    TextView tvRateText;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_otp_text)
    TextView tvOTPText;
    @BindView(R.id.tv_otp)
    TextView tvOTP;
    @BindView(R.id.btn_track)
    Button btnTrack;
    @BindView(R.id.btn_payment)
    Button btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_detail);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvTechName.setTypeface(Utility.typeFaceForBoldText(this));
        tvRateText.setTypeface(Utility.typeFaceForBoldText(this));
        tvRate.setTypeface(Utility.typeFaceForBoldText(this));
        tvOTPText.setTypeface(Utility.typeFaceForBoldText(this));
        tvOTP.setTypeface(Utility.typeFaceForBoldText(this));
        btnTrack.setTypeface(Utility.typeFaceForBoldText(this));
        btnPayment.setTypeface(Utility.typeFaceForBoldText(this));
    }

    public void onBackClicked(View view){
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("OnBackPress","Working");
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    public void onPaymentClicked(View view) {
        startActivity(new Intent(this, PaymentMethodsActivity.class));
    }

    public void onMsgIconClicked(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }

    public void onTrackClicked(View view) {
        startActivity(new Intent(this, TechnicianLocationActivity.class));
    }




}
