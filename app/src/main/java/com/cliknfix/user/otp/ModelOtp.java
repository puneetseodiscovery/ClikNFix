package com.cliknfix.user.otp;

import android.os.Message;

import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class ModelOtp implements IMOtp {

    POtp pOtp;

    public ModelOtp(POtp pOtp) {
        this.pOtp = pOtp;
    }

    @Override
    public void fillOTP(String otp,String phone) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.fillOTP(otp,phone,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.FILL_OTP_SUCCESS:
                   /* MobileNoResponseModel mobileNoResponseModel = (MobileNoResponseModel) msg.obj;
                    pMobileActivity.onSendOTPSuccess(mobileNoResponseModel);*/
                    break;

                case APIInterface.FILL_OTP_FAILURE:
                    String message = (String) msg.obj;
                    //pMobileActivity.onSendOTPFailure(message);
                    break;
            }
        }
    };
}
