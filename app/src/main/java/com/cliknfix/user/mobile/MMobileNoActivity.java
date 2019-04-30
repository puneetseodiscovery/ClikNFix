package com.cliknfix.user.mobile;

import android.os.Message;
import android.util.Log;

import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MMobileNoActivity implements IMMobileActivity {

    PMobileActivity pMobileActivity;

    public MMobileNoActivity(PMobileActivity pMobileActivity) {
        this.pMobileActivity = pMobileActivity;
    }

    @Override
    public void sendOTP(String phone) {
        Log.e("Model","Working");
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.sendOTP(phone,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.SEND_OTP_SUCCESS:
                    MobileNoResponseModel mobileNoResponseModel = (MobileNoResponseModel) msg.obj;
                    pMobileActivity.onSendOTPSuccess(mobileNoResponseModel);
                    break;

                case APIInterface.SEND_OTP_FAILED:
                    String message = (String) msg.obj;
                    pMobileActivity.onSendOTPFailure(message);
                    break;
            }
        }
    };
}
