package com.cliknfix.forgotPassword;

public interface IForgotPasswordActivity {

    void onForgotPasswordResponseFromPresenter(String statusValue);
    void onForgotPasswordResponseFailedFromPresenter(String failedMessage);
}
