package com.cliknfix.user.homeScreen.bottomFragments.model;

import android.os.Message;

import com.cliknfix.user.homeScreen.bottomFragments.UserProfileFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.IPUserProfileFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.PUserProfileFragment;
import com.cliknfix.user.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.user.responseModels.UserProfileResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

import okhttp3.MultipartBody;

public class MUserProfileFragment implements IMUserProfileFragment {

    IPUserProfileFragment ipUserProfileFragment;

    public MUserProfileFragment(PUserProfileFragment pUserProfileFragment) {
        this.ipUserProfileFragment = pUserProfileFragment;
    }

    @Override
    public void getUserProfile(int id, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getUserProfile(id,token,mHandler);
    }

    @Override
    public void saveUserProfile(String name, String phone, String blood_group, String age, String address, String image, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.saveUserProfile(name,phone,blood_group,age,address,image,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.USER_PROFILE_SUCCESS:
                    UserProfileResponseModel userProfileResponseModel = (UserProfileResponseModel) msg.obj;
                    ipUserProfileFragment.getUserProfileSuccess(userProfileResponseModel);
                    break;

                case APIInterface.USER_PROFILE_FAILED:
                    String message = (String) msg.obj;
                    ipUserProfileFragment.getUserProfileFailure(message);
                    break;
                case APIInterface.SAVE_USER_PROFILE_SUCCESS:
                    SaveUserProfileResponseModel model = (SaveUserProfileResponseModel) msg.obj;
                    ipUserProfileFragment.saveUserProfileSuccess(model);
                    break;

                case APIInterface.SAVE_USER_PROFILE_FAILURE:
                    String msgg = (String) msg.obj;
                    ipUserProfileFragment.saveUserProfileFailure(msgg);
                    break;
            }
        }
    };
}
