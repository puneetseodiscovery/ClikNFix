package com.cliknfix.user.forgotPassword;

import com.cliknfix.user.responseModels.ForgotPasswordResponseModel;

public class PForgotPasswordActivity implements IPForgotPasswordActivity {

    private IForgotPasswordActivity iForgotPasswordActivity;
    private IMForgotPasswordActivity iModelForgotPasswordActivity;

    public PForgotPasswordActivity(ForgotPasswordActivity forgotPasswordActivity) {
        iForgotPasswordActivity = forgotPasswordActivity;
        iModelForgotPasswordActivity = new MForgotPasswordActivity(this);
    }

    @Override
    public void resetPassword (String email) {
        iModelForgotPasswordActivity.forgotPasswordRestCall(email);
    }

    @Override
    public void onForgotPasswordSuccessResponse(ForgotPasswordResponseModel forgotPasswordResponseModel) {
        iForgotPasswordActivity.onForgotPasswordSuccessResponseFromPresenter(forgotPasswordResponseModel);
    }

    @Override
    public void onForgotPasswordFailureResponse(String message) {
        iForgotPasswordActivity.onForgotPasswordFailureResponseFromPresenter(message);
    }

}
