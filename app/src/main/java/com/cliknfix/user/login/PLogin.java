package com.cliknfix.user.login;


import com.cliknfix.user.responseModels.LoginResponseModel;

public class PLogin implements IPLogin {

    ILoginActivity iLogin;
    IModelLogin iModelLogin;

    public PLogin(LoginActivity loginActivity) {
        iLogin = loginActivity;
        iModelLogin = new ModelLogin(this);
    }

    @Override
    public void doLogin(String email, String password,String device_token) {
        //BeanLogin beanLogin = new BeanLogin(email,password,device_token);
        iModelLogin.loginRestCall(email,password,device_token);
    }

    @Override
    public void onLoginSuccess(LoginResponseModel userModelLoginResponse) {
        iLogin.onLoginSuccessFromPresenter(userModelLoginResponse);
    }

    @Override
    public void otpNotVerified(LoginResponseModel loginResponseModel) {
        iLogin.otpNotVerifiedFromPresenter(loginResponseModel);
    }

    @Override
    public void onLoginFailed(String message) {
        iLogin.onLoginFailedFromPresenter(message);
    }
}
