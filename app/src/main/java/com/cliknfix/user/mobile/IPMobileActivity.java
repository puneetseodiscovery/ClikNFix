package com.cliknfix.user.mobile;

import com.cliknfix.user.responseModels.MobileNoResponseModel;

public interface IPMobileActivity {
    void sendOTP(String phone);
    void onSendOTPSuccess(MobileNoResponseModel mobileNoResponseModel);
    void onSendOTPFailure(String message);
}
