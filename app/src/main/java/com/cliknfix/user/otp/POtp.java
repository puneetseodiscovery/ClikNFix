package com.cliknfix.user.otp;

import com.cliknfix.user.responseModels.OTPResponseModel;

public class POtp implements IPOtp {

    IOtpActivity iOtpActivity;
    IMOtp imOtp;

    public POtp(OtpActivity otpActivity) {
        iOtpActivity = otpActivity;
        imOtp = new ModelOtp(this);
    }

    @Override
    public void fillOTP(String phone,String otp, String user_id) {
        imOtp.fillOTP(phone,otp,user_id);
    }

    @Override
    public void onFillOTPSuccess(OTPResponseModel otpResponseModel) {
        iOtpActivity.onFillOTPSuccessFromPresenter(otpResponseModel);
    }

    @Override
    public void onFillOTPFailure(String message) {
        iOtpActivity.onFillOTPFailureFromPresenter(message);
    }
}
