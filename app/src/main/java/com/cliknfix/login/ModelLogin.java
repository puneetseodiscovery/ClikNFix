package com.cliknfix.login;

import android.os.Message;

import com.cliknfix.responseModels.UserModelLoginResponse;
import com.cliknfix.retrofit.APIInterface;
import com.cliknfix.retrofit.RetrofitCalls;


public class ModelLogin implements IModelLogin {

    String email,password;
    PLogin pLogin;

    int value;

    public ModelLogin(String email,String password,PLogin pLogin) {

        this.email = email;
        this.password = password;
        this.pLogin = pLogin;
    }

    @Override
    public void loginRestCall() {

        BeanLogin beanLogin = new BeanLogin(email,password);
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.loginUser(beanLogin,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.LOGIN_SUCCESS:

//                    value = ((UserModelLoginResponse) msg.obj).getStatus();
                    UserModelLoginResponse userModelLoginResponse = (UserModelLoginResponse) msg.obj;
                    //pLogin.onLoginResponseFromModel(value);
                    pLogin.onLoginSuccess(userModelLoginResponse);
                    break;

                case APIInterface.LOGIN_FAILED:

                    value = APIInterface.LOGIN_FAILED;
                    //pLogin.onLoginResponseFromModel(value);
                    pLogin.onLoginFailed(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
