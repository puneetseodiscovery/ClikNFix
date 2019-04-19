package com.cliknfix.login;


import com.cliknfix.responseModels.UserModelLoginResponse;

public interface IPLogin {

    void doLogin(String email, String password);
    void onLoginResponseFromModel(int statusValue);
    void onLoginSuccess(UserModelLoginResponse userModelLoginResponse);
    void onLoginFailed(String message);
}
