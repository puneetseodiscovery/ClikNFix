package com.cliknfix.user.changePassword;

import com.cliknfix.user.responseModels.ChangePasswordResponseModel;

public class PChangePasswordActivity implements IPChangePasswordActivity {

    private IChangePasswordActivity iChangePasswordActivity;
    private IMChangePasswordActivity imChangePasswordActivity;

    public PChangePasswordActivity(ChangePasswordActivity changePasswordActivity) {
        iChangePasswordActivity = changePasswordActivity;
        imChangePasswordActivity = new MChangePasswordActivity(this);
    }

    @Override
    public void changePassword(String oldPass, String newPass, String confirmPass, String token) {
        imChangePasswordActivity.changePassword(oldPass,newPass,confirmPass,token);
    }

    @Override
    public void onChangePasswordSuccessResponse(ChangePasswordResponseModel changePasswordResponseModel) {
        iChangePasswordActivity.onChangePasswordSuccessResponseFromPresenter(changePasswordResponseModel);
    }

    @Override
    public void onChangePasswordFailureResponse(String message) {
        iChangePasswordActivity.onChangePasswordFailureResponseFromPresenter(message);
    }
}
