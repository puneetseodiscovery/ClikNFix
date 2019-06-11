package com.cliknfix.user.otp;

import android.os.Message;

import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.responseModels.OTPResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class ModelOtp implements IMOtp {

    IPOtp ipOtp;

    public ModelOtp(POtp pOtp) {
        this.ipOtp = pOtp;
    }

    @Override
    public void fillOTP(String phone,String otp,String user_id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.fillOTP(phone,otp,user_id,mHandler);
    }

    @Override
    public void resendOTP(String phone, String user_id, String resend_otp) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.resendOTP(phone,user_id,resend_otp,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.FILL_OTP_SUCCESS:
                    OTPResponseModel otpResponseModel = (OTPResponseModel) msg.obj;
                    ipOtp.onFillOTPSuccess(otpResponseModel);
                    break;

                case APIInterface.FILL_OTP_FAILURE:
                    String message = (String) msg.obj;
                    ipOtp.onFillOTPFailure(message);
                    break;

                case APIInterface.RESEND_OTP_SUCCESS:
                    MobileNoResponseModel mobileNoResponseModel = (MobileNoResponseModel) msg.obj;
                    ipOtp.onResendOTPSuccess(mobileNoResponseModel);
                    break;

                case APIInterface.RESEND_OTP_FAILED:
                    String msgg = (String) msg.obj;
                    ipOtp.onResendOTPFailure(msgg);
                    break;
            }
        }
    };
}
