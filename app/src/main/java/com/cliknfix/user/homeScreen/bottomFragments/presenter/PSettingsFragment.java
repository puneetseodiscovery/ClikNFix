package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.homeScreen.bottomFragments.ISettingsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.SettingsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.IMSettingsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.MSettingsFragment;
import com.cliknfix.user.responseModels.LogoutResponseModel;

public class PSettingsFragment implements IPSettingsFragment {

    ISettingsFragment iSettingsFragment;
    IMSettingsFragment imSettingsFragment;

    public PSettingsFragment(SettingsFragment settingsFragment) {
        this.iSettingsFragment = settingsFragment;
        this.imSettingsFragment = new MSettingsFragment(this);
    }

    @Override
    public void doLogout(int user_id) {
        imSettingsFragment.doLogout(user_id);
    }

    @Override
    public void logoutSuccess(LogoutResponseModel logoutResponseModel) {
        iSettingsFragment.logoutSuccessFromPresenter(logoutResponseModel);
    }

    @Override
    public void logoutFailure(String message) {
        iSettingsFragment.logoutFailureFromPresenter(message);
    }
}
