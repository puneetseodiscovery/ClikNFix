package com.cliknfix.user.mobile;

import android.util.Log;

import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.responseModels.SignUpResponseModel;

public class PMobileActivity implements IPMobileActivity {

    IMobileNoActivity iMobileNoActivity;
    IMMobileActivity imMobileActivity;

    public PMobileActivity(MobileNoActivity mobileNoActivity) {
        iMobileNoActivity = mobileNoActivity;
        imMobileActivity = new MMobileNoActivity(this);
    }

    @Override
    public void sendOTP(String phone,String user_id,String resend_otp) {
        imMobileActivity.sendOTP(phone,user_id,resend_otp);
    }

    @Override
    public void onSendOTPSuccess(MobileNoResponseModel mobileNoResponseModel) {
        iMobileNoActivity.onSendOTPSuccessFromPresenter(mobileNoResponseModel);
    }

    @Override
    public void onSendOTPFailure(String message) {
        iMobileNoActivity.onSendOTPFailureFromPresenter(message);
    }

    @Override
    public void doSignUp(String name, String email, String age, String bg, String add, String phone, String pass) {
        imMobileActivity.doSignUp(name,email,age,bg,add,phone,pass);
    }

    @Override
    public void onSignUpResponseSuccessFromModel(SignUpResponseModel signUpResponseModel) {
        iMobileNoActivity.onSignUpResponseSuccessFromPresenter(signUpResponseModel);
    }

    @Override
    public void onSignUpResponseFailureFromModel(String msgg) {
        iMobileNoActivity.onSignUpResponseFailureFromPresenter(msgg);
    }
}
