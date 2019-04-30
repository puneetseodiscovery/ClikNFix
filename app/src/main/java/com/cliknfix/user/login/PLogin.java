package com.cliknfix.user.login;


import com.cliknfix.user.responseModels.UserModelLoginResponse;

public class PLogin implements IPLogin {

    ILoginActivity iLogin;
    IModelLogin iModelLogin;

    public PLogin(LoginActivity loginActivity) {
        iLogin = loginActivity;
    }

    @Override
    public void doLogin(String email, String password) {
        BeanLogin beanLogin = new BeanLogin(email,password);
        iModelLogin = new ModelLogin(this);
        iModelLogin.loginRestCall(beanLogin);
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
