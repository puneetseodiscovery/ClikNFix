package com.cliknfix.user.forgotPassword;

import android.util.Log;

import com.cliknfix.user.responseModels.ForgotPasswordResponseModel;

public class PForgotPasswordActivity implements IPForgotPasswordActivity {

    private IForgotPasswordActivity iForgotPasswordActivity;
    private IModelForgotPasswordActivity iModelForgotPasswordActivity;

    public PForgotPasswordActivity(ForgotPasswordActivity forgotPasswordActivity) {

        iForgotPasswordActivity = forgotPasswordActivity;
        iModelForgotPasswordActivity = new ModelForgotPasswordActivity(this);
    }

    @Override
    public void resetPassword (String email) {
        iModelForgotPasswordActivity.forgotPasswordRestCall(email);
    }

    @Override
    public void onForgotPasswordSuccessResponse(ForgotPasswordResponseModel forgotPasswordResponseModel) {
        Log.e("onForgotSuccessResponse","Success");
        iForgotPasswordActivity.onForgotPasswordSuccessResponseFromPresenter(forgotPasswordResponseModel);
    }

    @Override
    public void onForgotPasswordFailureResponse(String message) {
        Log.e("onForgotFailureResponse","Success");
        iForgotPasswordActivity.onForgotPasswordFailureResponseFromPresenter(message);
    }

}
