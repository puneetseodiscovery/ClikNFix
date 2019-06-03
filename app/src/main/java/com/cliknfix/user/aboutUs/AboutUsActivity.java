package com.cliknfix.user.aboutUs;

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
import com.cliknfix.user.responseModels.AboutUsResponseModel;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseClass implements IAboutUsActivity {

    IPAboutUsActivity ipAboutUsActivity;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        ipAboutUsActivity = new PAboutUsActivity(this);
        init();
    }

    public void onBackClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("DefaultTab", 1);
        startActivity(intent);
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvAboutUs.setTypeface(Utility.typeFaceForText(this));

        progressDialog = Utility.showLoader(this);
        ipAboutUsActivity.aboutUs(Utility.getToken());
    }

    @Override
    public void aboutUsSuccessResponseFromPresenter(AboutUsResponseModel aboutUsResponseModel) {
        progressDialog.dismiss();
        tvAboutUs.setText(Html.fromHtml(aboutUsResponseModel.getData().get(0).getAboutUs()));
    }

    @Override
    public void aboutUsFailureResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, ""+ message, Toast.LENGTH_SHORT).show();
    }
}
