package com.cliknfix.user.mobile;

import android.util.Log;

import com.cliknfix.user.responseModels.MobileNoResponseModel;

public class PMobileActivity implements IPMobileActivity {

    IMobileNoActivity iMobileNoActivity;
    IMMobileActivity imMobileActivity;

    public PMobileActivity(MobileNoActivity mobileNoActivity) {
        iMobileNoActivity = mobileNoActivity;
    }

    @Override
    public void sendOTP(String phone) {
        imMobileActivity = new MMobileNoActivity(this);
        imMobileActivity.sendOTP(phone);
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
