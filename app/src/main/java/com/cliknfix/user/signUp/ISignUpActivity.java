package com.cliknfix.user.signUp;

import com.cliknfix.user.responseModels.SignUpResponseModel;

public interface ISignUpActivity {

    void onSignUpResponseSuccessFromPresenter(SignUpResponseModel signUpResponseModel);
    void onSignUpResponseFailureFromPresenter(String message);
}
