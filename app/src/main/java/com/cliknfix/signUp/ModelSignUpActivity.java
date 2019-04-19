package com.cliknfix.signUp;

import android.os.Message;

import com.cliknfix.responseModels.SignUpResponseModel;
import com.cliknfix.retrofit.APIInterface;
import com.cliknfix.retrofit.RetrofitCalls;

public class ModelSignUpActivity implements IModleSignUpActivity {

    IPSignUp ipSignUp;
    int value;

    public ModelSignUpActivity(PSignUp pSignUp) {

        ipSignUp = pSignUp;
    }

    @Override
    public void signUpRestCall(BeanModelSignUp beanModelSignUp) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.signUpUser(beanModelSignUp,mHandler);

    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.SIGNUP_SUCCESS:

                    value = ((SignUpResponseModel) msg.obj).getOtp();
                    String mes = ((SignUpResponseModel) msg.obj).getMessage();
                    ipSignUp.onSignUpResponseFromModel(mes,value);
                    break;

                case APIInterface.SIGNUP_FAILED:
                    value = APIInterface.SIGNUP_FAILED;
                    String mesFailed = String.valueOf(msg.obj);
                    ipSignUp.onSignUpResponseFromModel(mesFailed,value);
                    break;
            }
        }
    };
}
