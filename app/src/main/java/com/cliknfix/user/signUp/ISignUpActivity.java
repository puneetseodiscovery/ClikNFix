package com.cliknfix.user.signUp;

import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.responseModels.SocialLoginResponseModel;

public interface ISignUpActivity {

    void onSignUpResponseSuccessFromPresenter(SignUpResponseModel signUpResponseModel);
    void onSignUpResponseFailureFromPresenter(String message);
    void onLoginSuccessFromPresenter(SocialLoginResponseModel socialLoginResponseModel);
    void onLoginFailedFromPresenter(String msgg);
    void otpNotVerifiedFromPresenter(SocialLoginResponseModel model);
}
