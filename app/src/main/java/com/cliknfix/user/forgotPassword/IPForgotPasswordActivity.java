package com.cliknfix.user.forgotPassword;

import com.cliknfix.user.responseModels.ForgotPasswordResponseModel;

public interface IPForgotPasswordActivity {
    void resetPassword(String email);
    void onForgotPasswordSuccessResponse(ForgotPasswordResponseModel forgotPasswordResponseModel);
    void onForgotPasswordFailureResponse(String message);
}
