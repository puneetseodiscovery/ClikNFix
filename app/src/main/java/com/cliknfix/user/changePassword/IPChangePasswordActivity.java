package com.cliknfix.user.changePassword;

import com.cliknfix.user.responseModels.ChangePasswordResponseModel;

public interface IPChangePasswordActivity {
    void changePassword(String oldPass, String newPass, String confirmPass, String token);
    void onChangePasswordSuccessResponse(ChangePasswordResponseModel changePasswordResponseModel);
    void onChangePasswordFailureResponse(String message);
}
