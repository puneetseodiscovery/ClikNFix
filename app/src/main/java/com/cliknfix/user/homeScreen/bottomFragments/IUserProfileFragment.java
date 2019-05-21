package com.cliknfix.user.homeScreen.bottomFragments;

import com.cliknfix.user.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.user.responseModels.UserProfileResponseModel;

public interface IUserProfileFragment {
    void getUserProfileSuccessFromPresenter(UserProfileResponseModel userProfileResponseModel);
    void getUserProfileFailureFromPresenter(String message);
    void saveUserProfileSuccessFromPresenter(SaveUserProfileResponseModel model);
    void saveUserProfileFailureFromPresenter(String msgg);
}
