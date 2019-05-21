package com.cliknfix.user.technicianDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastTechDetailActivity extends BaseClass {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_username_text)
    TextView tvUserNameText;
    @BindView(R.id.tv_username)
    TextView tvUserName;
    @BindView(R.id.tv_email_text)
    TextView tvEmailText;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_phone_text)
    TextView tvPhoneText;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_job_type_text)
    TextView tvJobTypeText;
    @BindView(R.id.tv_job_type)
    TextView tvJobType;
    @BindView(R.id.tv_job_date_text)
    TextView tvJobDateText;
    @BindView(R.id.tv_job_date)
    TextView tvJobDate;
    @BindView(R.id.tv_payable_amt_text)
    TextView tvPayAmtText;
    @BindView(R.id.tv_payable_amt)
    TextView tvPayAmt;
    @BindView(R.id.tv_address_text)
    TextView tvAddressText;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_tech_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvUserNameText.setTypeface(Utility.typeFaceForText(this));
        tvUserName.setTypeface(Utility.typeFaceForText(this));
        tvEmailText.setTypeface(Utility.typeFaceForText(this));
        tvEmail.setTypeface(Utility.typeFaceForText(this));
        tvPhoneText.setTypeface(Utility.typeFaceForText(this));
        tvPhone.setTypeface(Utility.typeFaceForText(this));
        tvJobTypeText.setTypeface(Utility.typeFaceForText(this));
        tvJobType.setTypeface(Utility.typeFaceForText(this));
        tvJobDateText.setTypeface(Utility.typeFaceForText(this));
        tvJobDate.setTypeface(Utility.typeFaceForText(this));
        tvPayAmtText.setTypeface(Utility.typeFaceForText(this));
        tvPayAmt.setTypeface(Utility.typeFaceForText(this));
        tvAddressText.setTypeface(Utility.typeFaceForText(this));
        tvAddress.setTypeface(Utility.typeFaceForText(this));
    }

    public void onBackClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("DefaultTab", 2);
        startActivity(intent);
    }


}
