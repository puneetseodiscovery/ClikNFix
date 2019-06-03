package com.cliknfix.user.signUp;

import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.responseModels.SocialLoginResponseModel;

public interface IPSignUp {

    void doSignUp(String username, String email, String age, String bloodGroup, String address, String phone, String password);
    void onSignUpResponseSuccessFromModel(SignUpResponseModel signUpResponseModel);
    void onSignUpResponseFailureFromModel(String message);
    void doLogin(String email, String username, String deviceToken);
    void onLoginResponseSuccessFromModel(SocialLoginResponseModel socialLoginResponseModel);
    void onLoginResponseFailureFromModel(String msgg);
    void otpNotVerified(SocialLoginResponseModel model);

}
