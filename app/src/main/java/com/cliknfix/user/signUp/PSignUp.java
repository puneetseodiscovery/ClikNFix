package com.cliknfix.user.signUp;

import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.responseModels.SocialLoginResponseModel;

public class PSignUp implements IPSignUp {

    ISignUpActivity iSignUpActivity;
    IModleSignUpActivity iModleSignUpActivity;

    public PSignUp(SignUpActivity signUpActivity) {

        iSignUpActivity = signUpActivity;
        iModleSignUpActivity = new ModelSignUpActivity(this);

    }

    @Override
    public void doSignUp(String username, String email, String age, String bloodGroup, String address, String phone, String password)
    {
        BeanModelSignUp beanModelSignUp = new BeanModelSignUp(username,email,age,bloodGroup,address,phone,password);
        iModleSignUpActivity.signUpRestCall(beanModelSignUp);
    }

    @Override
    public void onSignUpResponseSuccessFromModel(SignUpResponseModel signUpResponseModel) {
        iSignUpActivity.onSignUpResponseSuccessFromPresenter(signUpResponseModel);
    }

    @Override
    public void onSignUpResponseFailureFromModel(String message) {
        iSignUpActivity.onSignUpResponseFailureFromPresenter(message);
    }

    @Override
    public void doLogin(String email, String username, String deviceToken) {
        iModleSignUpActivity.doLogin(email,username,deviceToken);
    }

    @Override
    public void onLoginResponseSuccessFromModel(SocialLoginResponseModel socialLoginResponseModel) {
        iSignUpActivity.onLoginSuccessFromPresenter(socialLoginResponseModel);
    }

    @Override
    public void onLoginResponseFailureFromModel(String msgg) {
        iSignUpActivity.onLoginFailedFromPresenter(msgg);
    }

    @Override
    public void otpNotVerified(SocialLoginResponseModel model) {
        iSignUpActivity.otpNotVerifiedFromPresenter(model);
    }
}
