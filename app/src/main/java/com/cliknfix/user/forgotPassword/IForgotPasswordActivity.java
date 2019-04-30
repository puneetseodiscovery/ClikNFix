package com.cliknfix.user.forgotPassword;

import com.cliknfix.user.responseModels.ForgotPasswordResponseModel;

public interface IForgotPasswordActivity {
    void onForgotPasswordSuccessResponseFromPresenter(ForgotPasswordResponseModel forgotPasswordResponseModel);
    void onForgotPasswordFailureResponseFromPresenter(String message);
}
