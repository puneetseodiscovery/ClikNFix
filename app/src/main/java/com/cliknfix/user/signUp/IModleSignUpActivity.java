package com.cliknfix.user.signUp;

public interface IModleSignUpActivity {
    void signUpRestCall(BeanModelSignUp beanModelSignUp);
    void doLogin(String email, String username, String deviceToken);
}
