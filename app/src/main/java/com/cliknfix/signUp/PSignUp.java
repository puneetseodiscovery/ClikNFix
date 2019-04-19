package com.cliknfix.signUp;

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

        BeanModelSignUp beanModelSignUp = new BeanModelSignUp(username,email,age,bloodGroup,address,password,"2");
        iModleSignUpActivity.signUpRestCall(beanModelSignUp);


    }

    @Override
    public void onSignUpResponseFromModel(String statusValue,int otp) {

        iSignUpActivity.onSignUpResponseFromPresenter(statusValue,otp);
    }
}
