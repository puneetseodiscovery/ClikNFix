package com.cliknfix.forgotPassword;

import android.os.Message;

import com.cliknfix.responseModels.ForgotPasswordResponseModel;
import com.cliknfix.retrofit.APIInterface;
import com.cliknfix.retrofit.RetrofitCalls;

public class ModelForgotPasswordActivity implements IModelForgotPasswordActivity {

    IPForgotPasswordActivity ipForgotPasswordActivity;
    String value;

    public ModelForgotPasswordActivity(PForgotPasswordActivity pForgotPasswordActivity) {

        ipForgotPasswordActivity = pForgotPasswordActivity;
    }

    @Override
    public void forgotPasswordRestCall(String phoneNumber) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.forgotPassword(phoneNumber,mHandler);

    }


    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.FORGOT_SUCCESS:

                    value = ((ForgotPasswordResponseModel) msg.obj).getMessage();
                    ipForgotPasswordActivity.onForgotPasswordResponse(value);

                    break;

                case APIInterface.FORGOT_FAILED:

                    value = String.valueOf(msg.obj);
                    ipForgotPasswordActivity.onForgotPasswordResponseFailed(value);

                    break;
            }
        }
    };
}
