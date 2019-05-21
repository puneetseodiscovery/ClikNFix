package com.cliknfix.user.aboutUs;

import com.cliknfix.user.responseModels.AboutUsResponseModel;

public interface IAboutUsActivity {
    void aboutUsSuccessResponseFromPresenter(AboutUsResponseModel aboutUsResponseModel);
    void aboutUsFailureResponseFromPresenter(String message);
}
