package com.cliknfix.user.paymentMethods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.technicianReview.TechReviewActivity;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentSuccessActivity extends BaseClass {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_successful_text)
    TextView tvSuccessfulText;
    @BindView(R.id.tv_approve_text)
    TextView tvApproveText;
    @BindView(R.id.tv_transaction_details_text)
    TextView tvTransactionDetailsText;
    @BindView(R.id.btn_continue)
    Button btnContinue;

    String techId,techName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvSuccessfulText.setTypeface(Utility.typeFaceForBoldText(this));
        tvApproveText.setTypeface(Utility.typeFaceForBoldText(this));
        tvTransactionDetailsText.setTypeface(Utility.typeFaceForText(this));
        btnContinue.setTypeface(Utility.typeFaceForBoldText(this));
        techId= getIntent().getStringExtra("techId");
        techName= getIntent().getStringExtra("techName");
    }

    public void onContinueClicked(View view) {
        startActivity(new Intent(this, TechReviewActivity.class).putExtra("techId",techId));
    }

}
