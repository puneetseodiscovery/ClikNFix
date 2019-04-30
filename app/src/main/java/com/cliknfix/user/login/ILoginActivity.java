package com.cliknfix.user.login;


import com.cliknfix.user.responseModels.UserModelLoginResponse;

public interface ILoginActivity {

    void onLoginFailedFromPresenter(String message);
    void onLoginSuccessFromPresenter(UserModelLoginResponse userModelLoginResponse);
}
