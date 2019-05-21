package com.cliknfix.user.homeScreen.bottomFragments.model;

public interface IMUserProfileFragment {
    public void getUserProfile(int id, String token);
    void saveUserProfile(String name, String phone, String blood_group, String age, String address, String imgUrl, String token);
}
