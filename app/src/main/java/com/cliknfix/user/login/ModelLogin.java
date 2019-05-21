package com.cliknfix.user.login;

import android.os.Message;

import com.cliknfix.user.responseModels.LoginResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;


public class ModelLogin implements IModelLogin {

    String email,password;
    IPLogin ipLogin;

    int value;

    public ModelLogin(PLogin pLogin) {
        this.ipLogin = pLogin;
    }

    @Override
    public void loginRestCall(String email, String password,String device_token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.loginUser(email,password,device_token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.LOGIN_SUCCESS:
                    LoginResponseModel loginResponseModel = (LoginResponseModel) msg.obj;
                    ipLogin.onLoginSuccess(loginResponseModel);
                    break;
                case APIInterface.OTP_NOT_VERIFIED:
                    LoginResponseModel model = (LoginResponseModel) msg.obj;
                    ipLogin.otpNotVerified(model);
                    break;
                case APIInterface.LOGIN_FAILED:
                    String msgg = (String) msg.obj;
                    ipLogin.onLoginFailed(msgg);
                    break;
            }
        }
    };
}
