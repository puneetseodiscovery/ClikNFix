package com.cliknfix.user.signUp;

import com.cliknfix.user.responseModels.SignUpResponseModel;

public interface IPSignUp {

    void doSignUp(String username, String email, String age, String bloodGroup, String address, String phone, String password);
    void onSignUpResponseSuccessFromModel(SignUpResponseModel signUpResponseModel);
    void onSignUpResponseFailureFromModel(String message);
}
