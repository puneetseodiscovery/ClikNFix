package com.cliknfix.signUp;

public interface IPSignUp {

    void doSignUp(String username, String email, String age, String bloodGroup, String address, String password);
    void onSignUpResponseFromModel(String statusValue, int otp);
}
