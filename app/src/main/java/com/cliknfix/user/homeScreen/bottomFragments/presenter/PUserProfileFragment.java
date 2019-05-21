package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.homeScreen.bottomFragments.IUserProfileFragment;
import com.cliknfix.user.homeScreen.bottomFragments.UserProfileFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.IMUserProfileFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.MUserProfileFragment;
import com.cliknfix.user.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.user.responseModels.UserProfileResponseModel;

public class PUserProfileFragment implements IPUserProfileFragment {

    IUserProfileFragment iUserProfileFragment;
    IMUserProfileFragment imUserProfileFragment;

    public PUserProfileFragment(UserProfileFragment userProfileFragment) {
        this.iUserProfileFragment = userProfileFragment;
        this.imUserProfileFragment = new MUserProfileFragment(this);
    }

    @Override
    public void getUserProfile(int id, String token) {
        imUserProfileFragment.getUserProfile(id,token);
    }

    @Override
    public void getUserProfileSuccess(UserProfileResponseModel userProfileResponseModel) {
        iUserProfileFragment.getUserProfileSuccessFromPresenter(userProfileResponseModel);
    }

    @Override
    public void getUserProfileFailure(String message) {
        iUserProfileFragment.getUserProfileFailureFromPresenter(message);
    }

    @Override
    public void saveUserProfile(String name, String phone, String blood_group, String age, String address, String imgUrl, String token) {
        imUserProfileFragment.saveUserProfile(name,phone,blood_group,age,address,imgUrl,token);
    }

    @Override
    public void saveUserProfileSuccess(SaveUserProfileResponseModel model) {
        iUserProfileFragment.saveUserProfileSuccessFromPresenter(model);
    }

    @Override
    public void saveUserProfileFailure(String msgg) {
        iUserProfileFragment.saveUserProfileFailureFromPresenter(msgg);
    }
}
