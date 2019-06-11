package com.cliknfix.user.otp;

import com.cliknfix.user.responseModels.MobileNoResponseModel;
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

    @Override
    public void resendOTP(String phone, String user_id, String resend_otp) {
        imOtp.resendOTP(phone,user_id,resend_otp);
    }

    @Override
    public void onResendOTPSuccess(MobileNoResponseModel mobileNoResponseModel) {
        iOtpActivity.onResendOTPSuccessFromPresenter(mobileNoResponseModel);
    }

    @Override
    public void onResendOTPFailure(String msgg) {
        iOtpActivity.onResendOTPFailureFromPresenter(msgg);
    }
}
