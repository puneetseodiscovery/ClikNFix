package com.cliknfix.user.homeScreen.bottomFragments.adapter;

import com.cliknfix.user.responseModels.TechDetailResponseModel;

public interface IPastJobsAdapter {
    void getTechDetailSuccessFromPresenter(TechDetailResponseModel techDetailResponseModel);
    void getTechDetailFailureFromPresenter(String message);
}
