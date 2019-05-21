package com.cliknfix.user.contact;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.responseModels.ContactUsResponseModel;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactUsActivity extends BaseClass implements IContactUsActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_email_text)
    TextView tvEmailText;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.et_write_msg)
    EditText etWriteMsg;
    @BindView(R.id.btn_send)
    Button btnSend;

    IPContactUsActivity ipContactUsActivity;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);
        ipContactUsActivity = new PContactUsActivity(this);
        init();
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvEmailText.setTypeface(Utility.typeFaceForText(this));
        tvEmail.setTypeface(Utility.typeFaceForText(this));
        etWriteMsg.setTypeface(Utility.typeFaceForText(this));
        btnSend.setTypeface(Utility.typeFaceForBoldText(this));

        progressDialog= Utility.showLoader(this);
        ipContactUsActivity.contactUs(Utility.getToken());
    }

    public void onBackClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("DefaultTab", 1);
        startActivity(intent);
    }

    @Override
    public void contactUsFailureResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void contactUsSuccessResponseFromPresenter(ContactUsResponseModel contactUsResponseModel) {
        progressDialog.dismiss();
        tvEmail.setText(contactUsResponseModel.getData().get(0).getEmail());
    }
}
