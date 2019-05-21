package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.homeScreen.bottomFragments.IPastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.PastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.IMPastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.MPastJobsFragment;
import com.cliknfix.user.responseModels.PastJobsResponseModel;

public class PPastJobsFragment implements IPPastJobsFragment {

    IPastJobsFragment iPastJobsFragment;
    IMPastJobsFragment imPastJobsFragment;

    public PPastJobsFragment(PastJobsFragment pastJobsFragment) {
        this.iPastJobsFragment = pastJobsFragment;
        this.imPastJobsFragment = new MPastJobsFragment(this);
    }

    @Override
    public void getPastJobsList(String token) {
        imPastJobsFragment.getPastJobsList(token);
    }

    @Override
    public void getPastJobsListFailureResponse(String message) {
        iPastJobsFragment.getPastJobsListFailureFromPresenter(message);
    }

    @Override
    public void getPastJobsListSuccessResponse(PastJobsResponseModel pastJobsResponseModel) {
        iPastJobsFragment.getPastJobsListSuccessFromPresenter(pastJobsResponseModel);
    }

    @Override
    public void getPastJobsListNoDataResponse(String msgg) {
        iPastJobsFragment.getPastJobsListNoDataFromPresenter(msgg);
    }
}
