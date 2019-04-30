package com.cliknfix.user.login;


import com.cliknfix.user.responseModels.UserModelLoginResponse;

public interface IPLogin {

    void doLogin(String email, String password);
    void onLoginSuccess(UserModelLoginResponse userModelLoginResponse);
    void onLoginFailed(String message);
}
