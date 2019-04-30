package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.homeScreen.bottomFragments.UserProfileFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.IMUserProfileFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.MUserProfileFragment;
import com.cliknfix.user.responseModels.UserProfileResponseModel;

public class PUserProfileFragment implements IPUserProfileFragment {

    UserProfileFragment userProfileFragment;
    IMUserProfileFragment imHomeFragment;

    public PUserProfileFragment(UserProfileFragment userProfileFragment) {
        this.userProfileFragment = userProfileFragment;
        this.imHomeFragment = new MUserProfileFragment(this);
    }

    @Override
    public void getUserProfile(int id, String token) {
        imHomeFragment.getUserProfile(id,token);
    }

    @Override
    public void getUserProfileSuccess(UserProfileResponseModel userProfileResponseModel) {
        userProfileFragment.getUserProfileSuccessFromPresenter(userProfileResponseModel);
    }

    @Override
    public void getUserProfileFailure(String message) {
        userProfileFragment.getUserProfileFailureFromPresenter(message);
    }
}
