package com.cliknfix.user.changePassword;

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
import com.cliknfix.user.login.LoginActivity;
import com.cliknfix.user.responseModels.ChangePasswordResponseModel;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends BaseClass implements IChangePasswordActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_ptext)
    TextView tvPText;
    @BindView(R.id.et_old_pass)
    EditText etOldPass;
    @BindView(R.id.et_new_pass)
    EditText etNewPass;
    @BindView(R.id.et_confirm_pass)
    EditText etConfirmPass;
    @BindView(R.id.btn_update_pass)
    Button btnUpdate;

    IPChangePasswordActivity ipChangePasswordActivity;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        init();
        ipChangePasswordActivity = new PChangePasswordActivity(this);
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvPText.setTypeface(Utility.typeFaceForText(this));
        etOldPass.setTypeface(Utility.typeFaceForText(this));
        etNewPass.setTypeface(Utility.typeFaceForText(this));
        etConfirmPass.setTypeface(Utility.typeFaceForText(this));
        btnUpdate.setTypeface(Utility.typeFaceForBoldText(this));
    }

    public void onResetPasswordClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if (etOldPass.getText().toString().length()>0 && etNewPass.getText().toString().length()>0
                    && etConfirmPass.getText().toString().length()>0) {
                if(!etOldPass.getText().toString().equalsIgnoreCase(etNewPass.getText().toString())) {
                    if(etNewPass.getText().toString().equalsIgnoreCase(etConfirmPass.getText().toString())){
                        progressDialog = Utility.showLoader(this);
                        ipChangePasswordActivity.changePassword(etOldPass.getText().toString().trim(), etNewPass.getText().toString().trim(),
                                etConfirmPass.getText().toString().trim(), Utility.getToken());
                    } else {
                        Toast.makeText(this, "New password and Confirm password should be same.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Old password and New password is same.", Toast.LENGTH_SHORT).show();
                }
            }else {
                if (etConfirmPass.getText().toString().length()==0) {
                    etConfirmPass.setError("Enter confirm password");
                    etConfirmPass.requestFocus();
                }
                if (etNewPass.getText().toString().length()==0) {
                    etNewPass.setError("Enter new password");
                    etNewPass.requestFocus();
                }
                if (etOldPass.getText().toString().length()==0) {
                    etOldPass.setError("Enter current password");
                    etOldPass.requestFocus();
                }

            }
        } else {
            Toast.makeText(this, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }

    }


    public void onBackClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("DefaultTab", 1);
        startActivity(intent);
    }

    @Override
    public void onChangePasswordSuccessResponseFromPresenter(ChangePasswordResponseModel changePasswordResponseModel) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + changePasswordResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onChangePasswordFailureResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }
}
