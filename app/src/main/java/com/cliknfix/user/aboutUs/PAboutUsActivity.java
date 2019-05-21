package com.cliknfix.user.aboutUs;

import com.cliknfix.user.responseModels.AboutUsResponseModel;

public class PAboutUsActivity implements IPAboutUsActivity {

    private IAboutUsActivity iAboutUsActivity;
    private IMAboutUsActivity imAboutUsActivity;


    public PAboutUsActivity(AboutUsActivity aboutUsActivity) {
        iAboutUsActivity = aboutUsActivity;
        imAboutUsActivity = new MAboutUsActivity(this);
    }

    @Override
    public void aboutUs(String token) {
        imAboutUsActivity.aboutUs(token);
    }

    @Override
    public void onAboutUsSuccessResponse(AboutUsResponseModel aboutUsResponseModel) {
        iAboutUsActivity.aboutUsSuccessResponseFromPresenter(aboutUsResponseModel);
    }

    @Override
    public void onAboutUsFailureResponse(String message) {
        iAboutUsActivity.aboutUsFailureResponseFromPresenter(message);
    }
}
