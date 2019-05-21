package com.cliknfix.user.mobile;

import android.os.Message;
import android.util.Log;

import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MMobileNoActivity implements IMMobileActivity {

    IPMobileActivity ipMobileActivity;

    public MMobileNoActivity(PMobileActivity pMobileActivity) {
        this.ipMobileActivity = pMobileActivity;
    }

    @Override
    public void sendOTP(String phone,String user_id,String resend_otp) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.sendOTP(phone,user_id,resend_otp,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.SEND_OTP_SUCCESS:
                    MobileNoResponseModel mobileNoResponseModel = (MobileNoResponseModel) msg.obj;
                    ipMobileActivity.onSendOTPSuccess(mobileNoResponseModel);
                    break;

                case APIInterface.SEND_OTP_FAILED:
                    String message = (String) msg.obj;
                    ipMobileActivity.onSendOTPFailure(message);
                    break;
            }
        }
    };
}
