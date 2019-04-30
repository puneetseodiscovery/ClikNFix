package com.cliknfix.user.otp;

import com.cliknfix.user.responseModels.OTPResponseModel;

public interface IPOtp {

    void fillOTP(String otp, String phone);
    void onFillOTPSuccess(OTPResponseModel otpResponseModel);
    void onFillOTPFailure(String message);
}
