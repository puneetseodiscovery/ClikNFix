package com.cliknfix.user.aboutUs;

import com.cliknfix.user.responseModels.AboutUsResponseModel;

public interface IPAboutUsActivity {
    void aboutUs(String token);
    void onAboutUsSuccessResponse(AboutUsResponseModel aboutUsResponseModel);
    void onAboutUsFailureResponse(String message);
}
