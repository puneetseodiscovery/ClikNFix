package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.responseModels.UserProfileResponseModel;

public interface IPUserProfileFragment {

    void getUserProfile(int id, String token);
    void getUserProfileSuccess(UserProfileResponseModel userProfileResponseModel);
    void getUserProfileFailure(String message);

}
