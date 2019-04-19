package com.cliknfix.login;


import com.cliknfix.responseModels.UserModelLoginResponse;

public interface ILoginActivity {

    void onLoginResponseFromPresenter(int statusValue);
    void onLoginFailedFromPresenter(String message);
    void onLoginSuccessFromPresenter(UserModelLoginResponse userModelLoginResponse);
}
