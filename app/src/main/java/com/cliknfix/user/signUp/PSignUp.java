package com.cliknfix.user.signUp;

import com.cliknfix.user.responseModels.SignUpResponseModel;

public class PSignUp implements IPSignUp {

    ISignUpActivity iSignUpActivity;
    IModleSignUpActivity iModleSignUpActivity;

    public PSignUp(SignUpActivity signUpActivity) {

        iSignUpActivity = signUpActivity;
        iModleSignUpActivity = new ModelSignUpActivity(this);

    }

    @Override
    public void doSignUp(String username, String email, String age, String bloodGroup, String address, String password)
    {

        BeanModelSignUp beanModelSignUp = new BeanModelSignUp(username,email,age,bloodGroup,address,password);
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
}
