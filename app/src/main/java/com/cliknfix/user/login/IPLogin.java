package com.cliknfix.user.login;


import com.cliknfix.user.responseModels.LoginResponseModel;

public interface IPLogin {

    void doLogin(String email, String password,String token);
    void onLoginSuccess(LoginResponseModel userModelLoginResponse);
    void otpNotVerified(LoginResponseModel loginResponseModel);
    void onLoginFailed(String message);
}
