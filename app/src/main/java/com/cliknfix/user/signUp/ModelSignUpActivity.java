package com.cliknfix.user.signUp;

import android.os.Message;

import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.responseModels.SocialLoginResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class ModelSignUpActivity implements IModleSignUpActivity {

    IPSignUp ipSignUp;

    public ModelSignUpActivity(PSignUp pSignUp) {

        ipSignUp = pSignUp;
    }

    @Override
    public void signUpRestCall(BeanModelSignUp beanModelSignUp) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.signUpUser(beanModelSignUp,mHandler);
    }

    @Override
    public void doLogin(String email, String username, String deviceToken) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.doLogin(email,username,deviceToken,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.SIGNUP_SUCCESS:
                    SignUpResponseModel signUpResponseModel = (SignUpResponseModel) msg.obj;
                    ipSignUp.onSignUpResponseSuccessFromModel(signUpResponseModel);
                    break;
                case APIInterface.SIGNUP_FAILED:
                    String message = (String) msg.obj;
                    ipSignUp.onSignUpResponseFailureFromModel(message);
                    break;
                case APIInterface.SOCIAL_LOGIN_SUCCESS:
                    SocialLoginResponseModel socialLoginResponseModel = (SocialLoginResponseModel) msg.obj;
                    ipSignUp.onLoginResponseSuccessFromModel(socialLoginResponseModel);
                    break;
                case APIInterface.SOCIAL_OTP_NOT_VERIFIED:
                    SocialLoginResponseModel socialLoginResponseModel1 = (SocialLoginResponseModel) msg.obj;
                    ipSignUp.otpNotVerified(socialLoginResponseModel1);
                    break;
                case APIInterface.SOCIAL_LOGIN_FAILED:
                    String msgg = (String) msg.obj;
                    ipSignUp.onLoginResponseFailureFromModel(msgg);
                    break;

            }
        }
    };
}
