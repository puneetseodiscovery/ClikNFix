package com.cliknfix.user.privacyPolicy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.responseModels.PrivacyPolicyResponseModel;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivacyPolicyActivity extends BaseClass implements IPrivacyPolicyActivity{

    IPPrivacyPolicyActivity ipPrivacyPolicyActivity;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_privacy_policy)
    TextView tvPrivacyPolicy;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        ButterKnife.bind(this);
        ipPrivacyPolicyActivity = new PPrivacyPolicyActivity(this);
        init();
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvPrivacyPolicy.setTypeface(Utility.typeFaceForText(this));

        progressDialog = Utility.showLoader(this);
        ipPrivacyPolicyActivity.privacyPolicy(Utility.getToken());
    }

    public void onBackClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("DefaultTab", 1);
        startActivity(intent);
    }

    @Override
    public void privacyPolicySuccessResponseFromPresenter(PrivacyPolicyResponseModel privacyPolicyResponseModel) {
        progressDialog.dismiss();
        tvPrivacyPolicy.setText(Html.fromHtml(privacyPolicyResponseModel.getData().get(0).getPrivacyPolicy()));
    }

    @Override
    public void privacyPolicyFailureResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, ""+ message, Toast.LENGTH_SHORT).show();
    }
}
