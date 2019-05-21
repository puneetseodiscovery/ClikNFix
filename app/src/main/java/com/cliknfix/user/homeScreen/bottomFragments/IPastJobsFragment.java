package com.cliknfix.user.homeScreen.bottomFragments;

import com.cliknfix.user.responseModels.PastJobsResponseModel;

public interface IPastJobsFragment {
    void getPastJobsListFailureFromPresenter(String message);
    void getPastJobsListSuccessFromPresenter(PastJobsResponseModel pastJobsResponseModel);
    void getPastJobsListNoDataFromPresenter(String msgg);
}
