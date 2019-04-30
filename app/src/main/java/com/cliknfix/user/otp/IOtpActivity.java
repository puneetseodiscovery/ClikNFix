package com.cliknfix.user.otp;

import com.cliknfix.user.responseModels.OTPResponseModel;

public interface IOtpActivity {
    void onFillOTPSuccessFromPresenter(OTPResponseModel otpResponseModel);
    void onFillOTPFailureFromPresenter(String message);
}
