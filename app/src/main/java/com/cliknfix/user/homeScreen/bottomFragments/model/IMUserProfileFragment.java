package com.cliknfix.user.homeScreen.bottomFragments.model;

import okhttp3.MultipartBody;

public interface IMUserProfileFragment {
    public void getUserProfile(int id, String token);
    void saveUserProfile(String name, String phone, String blood_group, String age, String address, String image, String token);
}
