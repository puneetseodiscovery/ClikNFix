package com.cliknfix.user.otp;

import com.cliknfix.user.responseModels.OTPResponseModel;

public class POtp implements IPOtp {

    IOtpActivity iOtpActivity;
    IMOtp imOtp;

    public POtp(OtpActivity otpActivity) {
        iOtpActivity = otpActivity;
    }

    @Override
    public void fillOTP(String otp, String phone) {
        imOtp = new ModelOtp(this);
        imOtp.fillOTP(otp,phone);
    }

    @Override
    public void onFillOTPSuccess(OTPResponseModel otpResponseModel) {

    }

    @Override
    public void onFillOTPFailure(String message) {

    }
}
