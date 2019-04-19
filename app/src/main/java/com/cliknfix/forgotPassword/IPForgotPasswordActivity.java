package com.cliknfix.forgotPassword;

public interface IPForgotPasswordActivity {

    void sendNumberForgotPass(String phoneNumber);
    void onForgotPasswordResponse(String statusValue);
    void onForgotPasswordResponseFailed(String failedMessage);
}
