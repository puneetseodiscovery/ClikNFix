package com.cliknfix.paymentMethods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cliknfix.R;
import com.cliknfix.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_visa_card)
    TextView tvVisaCard;
    @BindView(R.id.tv_master_card)
    TextView tvMasterCard;
    @BindView(R.id.tv_card_number_text)
    TextView tvCardNumberText;
    @BindView(R.id.tv_expiry_date_text)
    TextView tvExpiryDateText;
    @BindView(R.id.tv_cvv_text)
    TextView tvCVVText;
    @BindView(R.id.tv_card_holder_name_text)
    TextView tvCardHolderNameText;
    @BindView(R.id.btn_verify_payment)
    Button btnVerifyPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        ButterKnife.bind(this);

        init();
    }

    public void onVerifyPaymentClicked(View view) {
        startActivity(new Intent(this, PaymentSuccessActivity.class));
    }

    public void onBackClicked(View view) {
        startActivity(new Intent(this, PaymentMethodsActivity.class));
    }

    public void init() {
        tvTitle.setText(getIntent().getStringExtra("paymentMethod"));
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvVisaCard.setTypeface(Utility.typeFaceForText(this));
        tvMasterCard.setTypeface(Utility.typeFaceForText(this));
        tvCardNumberText.setTypeface(Utility.typeFaceForText(this));
        tvExpiryDateText.setTypeface(Utility.typeFaceForText(this));
        tvCVVText.setTypeface(Utility.typeFaceForText(this));
        tvCardHolderNameText.setTypeface(Utility.typeFaceForText(this));
        btnVerifyPayment.setTypeface(Utility.typeFaceForBoldText(this));
    }
}
