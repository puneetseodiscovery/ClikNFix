package com.cliknfix.user.otp;

import com.cliknfix.user.responseModels.OTPResponseModel;

public interface IPOtp {

    void fillOTP(String phone,String otp,String user_id);
    void onFillOTPSuccess(OTPResponseModel otpResponseModel);
    void onFillOTPFailure(String message);
}
