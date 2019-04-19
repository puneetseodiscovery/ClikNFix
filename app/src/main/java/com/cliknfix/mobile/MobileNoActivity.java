package com.cliknfix.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cliknfix.R;
import com.cliknfix.base.BaseClass;
import com.cliknfix.otp.OtpActivity;
import com.cliknfix.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileNoActivity extends BaseClass
{
    @BindView(R.id.tv_mobile_screen_text)
    TextView tvP1;
    @BindView(R.id.tv_country_code)
    TextView tvCC;
    @BindView(R.id.et_mobile_no)
    EditText etMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_no);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        tvP1.setTypeface(Utility.typeFaceForText(this));
    }

    public void onNextClicked(View view) {
        startActivity(new Intent(this, OtpActivity.class));
    }


}
