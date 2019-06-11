package com.cliknfix.user.otp;

import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.responseModels.OTPResponseModel;

public interface IOtpActivity {
    void onFillOTPSuccessFromPresenter(OTPResponseModel otpResponseModel);
    void onFillOTPFailureFromPresenter(String message);
    void onResendOTPSuccessFromPresenter(MobileNoResponseModel mobileNoResponseModel);
    void onResendOTPFailureFromPresenter(String msgg);
}
