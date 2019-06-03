package com.cliknfix.user.homeScreen.bottomFragments.presenter;

import com.cliknfix.user.homeScreen.bottomFragments.adapter.IPastJobsAdapter;
import com.cliknfix.user.homeScreen.bottomFragments.adapter.PastJobsAdapter;
import com.cliknfix.user.homeScreen.bottomFragments.model.IMPastJobsAdapter;
import com.cliknfix.user.homeScreen.bottomFragments.model.MPastJobsAdapter;
import com.cliknfix.user.responseModels.TechDetailResponseModel;

public class PPastJobsAdapter implements IPPastJobsAdapter {

    IPastJobsAdapter iPastJobsAdapter;
    IMPastJobsAdapter imPastJobsAdapter;

    public PPastJobsAdapter(PastJobsAdapter pastJobsAdapter) {
        this.iPastJobsAdapter = pastJobsAdapter;
        this.imPastJobsAdapter = new MPastJobsAdapter(this);
    }

    @Override
    public void getTechDetail(int id, String token) {
        imPastJobsAdapter.getTechDetail(id,token);
    }

    @Override
    public void getTechDetailSuccessResponse(TechDetailResponseModel techDetailResponseModel) {
        iPastJobsAdapter.getTechDetailSuccessFromPresenter(techDetailResponseModel);
    }

    @Override
    public void getTechDetailFailureResponse(String message) {
        iPastJobsAdapter.getTechDetailFailureFromPresenter(message);
    }
}
