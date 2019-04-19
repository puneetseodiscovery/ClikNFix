package com.cliknfix.forgotPassword;

public class PForgotPasswordActivity implements IPForgotPasswordActivity {

    private IForgotPasswordActivity iForgotPasswordActivity;
    private IModelForgotPasswordActivity iModelForgotPasswordActivity;

    public PForgotPasswordActivity(ForgotPasswordActivity forgotPasswordActivity) {

        iForgotPasswordActivity = forgotPasswordActivity;
        iModelForgotPasswordActivity = new ModelForgotPasswordActivity(this);
    }

    @Override
    public void sendNumberForgotPass(String phoneNumber) {


        iModelForgotPasswordActivity.forgotPasswordRestCall(phoneNumber);

    }

    @Override
    public void onForgotPasswordResponse(String statusValue) {

        iForgotPasswordActivity.onForgotPasswordResponseFromPresenter(statusValue);
    }

    @Override
    public void onForgotPasswordResponseFailed(String failedMessage) {
        iForgotPasswordActivity.onForgotPasswordResponseFailedFromPresenter(failedMessage);
    }
}
