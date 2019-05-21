package com.cliknfix.user.changePassword;

import com.cliknfix.user.responseModels.ChangePasswordResponseModel;

public interface IChangePasswordActivity {
    void onChangePasswordSuccessResponseFromPresenter(ChangePasswordResponseModel changePasswordResponseModel);
    void onChangePasswordFailureResponseFromPresenter(String message);
}
