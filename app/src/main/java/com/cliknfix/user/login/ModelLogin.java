package com.cliknfix.user.login;

import android.os.Message;

import com.cliknfix.user.responseModels.UserModelLoginResponse;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;


public class ModelLogin implements IModelLogin {

    String email,password;
    PLogin pLogin;

    int value;

    public ModelLogin(PLogin pLogin) {
        this.pLogin = pLogin;
    }

    @Override
    public void loginRestCall(BeanLogin beanLogin) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.loginUser(beanLogin,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.LOGIN_SUCCESS:
                    UserModelLoginResponse userModelLoginResponse = (UserModelLoginResponse) msg.obj;
                    pLogin.onLoginSuccess(userModelLoginResponse);
                    break;

                case APIInterface.LOGIN_FAILED:
                    String message = (String) msg.obj;
                    pLogin.onLoginFailed(message);
                    break;
            }
        }
    };
}
