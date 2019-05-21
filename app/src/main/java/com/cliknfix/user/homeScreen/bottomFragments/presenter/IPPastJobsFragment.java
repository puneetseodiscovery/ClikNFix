package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.responseModels.PastJobsResponseModel;

public interface IPPastJobsFragment {
    void getPastJobsList(String token);
    void getPastJobsListFailureResponse(String message);
    void getPastJobsListSuccessResponse(PastJobsResponseModel pastJobsResponseModel);
    void getPastJobsListNoDataResponse(String msgg);
}
