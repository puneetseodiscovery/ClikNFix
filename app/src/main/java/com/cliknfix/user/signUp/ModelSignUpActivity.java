package com.cliknfix.user.signUp;

import android.os.Message;

import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class ModelSignUpActivity implements IModleSignUpActivity {

    IPSignUp ipSignUp;
    String status;

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
                    String mes = ((SignUpResponseModel) msg.obj).getMessage();
                    SignUpResponseModel signUpResponseModel = (SignUpResponseModel) msg.obj;
                    ipSignUp.onSignUpResponseSuccessFromModel(signUpResponseModel);
                    break;

                case APIInterface.SIGNUP_FAILED:
                    String message = (String) msg.obj;
                    ipSignUp.onSignUpResponseFailureFromModel(message);
                    break;
            }
        }
    };
}
