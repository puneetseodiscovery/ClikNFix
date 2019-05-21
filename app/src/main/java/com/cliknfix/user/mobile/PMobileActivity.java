package com.cliknfix.user.mobile;

import android.util.Log;

import com.cliknfix.user.responseModels.MobileNoResponseModel;

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
}
