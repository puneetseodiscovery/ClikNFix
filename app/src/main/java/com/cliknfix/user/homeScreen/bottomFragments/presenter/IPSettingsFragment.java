package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.responseModels.LogoutResponseModel;

public interface IPSettingsFragment {
    void doLogout(int user_id);
    void logoutSuccess(LogoutResponseModel logoutResponseModel);
    void logoutFailure(String message);
}
