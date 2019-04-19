package com.cliknfix.login;


import com.cliknfix.responseModels.UserModelLoginResponse;

public class PLogin implements IPLogin {

    ILoginActivity iLogin;
    IModelLogin iModelLogin;

    public PLogin(LoginActivity loginActivity) {
        iLogin = loginActivity;
    }

    @Override
    public void doLogin(String email, String password) {
            iModelLogin = new ModelLogin(email,password,this);
            iModelLogin.loginRestCall();
            }

    @Override
    public void onLoginResponseFromModel(int statusValue) {

        iLogin.onLoginResponseFromPresenter(statusValue);
    }

    @Override
    public void onLoginSuccess(UserModelLoginResponse userModelLoginResponse) {

        iLogin.onLoginSuccessFromPresenter(userModelLoginResponse);
    }

    @Override
    public void onLoginFailed(String message) {

        iLogin.onLoginFailedFromPresenter(message);
    }
}
