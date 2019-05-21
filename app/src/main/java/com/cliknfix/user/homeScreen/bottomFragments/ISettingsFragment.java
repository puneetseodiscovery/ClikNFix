package com.cliknfix.user.homeScreen.bottomFragments;

import com.cliknfix.user.responseModels.LogoutResponseModel;

public interface ISettingsFragment {
    void logoutSuccessFromPresenter(LogoutResponseModel logoutResponseModel);
    void logoutFailureFromPresenter(String message);
}
