package com.cliknfix.user.homeScreen.bottomFragments.model;

import android.os.Message;

import com.cliknfix.user.homeScreen.bottomFragments.UserProfileFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.PUserProfileFragment;
import com.cliknfix.user.responseModels.UserProfileResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MUserProfileFragment implements IMUserProfileFragment {

    PUserProfileFragment pUserProfileFragment;

    public MUserProfileFragment(PUserProfileFragment pUserProfileFragment) {
        this.pUserProfileFragment = pUserProfileFragment;
    }

    @Override
    public void getUserProfile(int id, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getUserProfile(id,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.USER_PROFILE_SUCCESS:
                    UserProfileResponseModel userProfileResponseModel = (UserProfileResponseModel) msg.obj;
                    pUserProfileFragment.getUserProfileSuccess(userProfileResponseModel);
                    break;

                case APIInterface.USER_PROFILE_FAILED:
                    String message = (String) msg.obj;
                    pUserProfileFragment.getUserProfileFailure(message);
                    break;
            }
        }
    };
}
