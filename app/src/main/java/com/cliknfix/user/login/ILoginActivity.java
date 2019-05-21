package com.cliknfix.user.login;


import com.cliknfix.user.responseModels.LoginResponseModel;

public interface ILoginActivity {

    void onLoginFailedFromPresenter(String message);
    void onLoginSuccessFromPresenter(LoginResponseModel loginResponseModel);
    void otpNotVerifiedFromPresenter(LoginResponseModel loginResponseModel);
}
