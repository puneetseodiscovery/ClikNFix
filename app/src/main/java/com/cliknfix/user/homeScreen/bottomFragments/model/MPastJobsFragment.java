package com.cliknfix.user.homeScreen.bottomFragments.model;

import android.os.Message;

import com.cliknfix.user.homeScreen.bottomFragments.presenter.IPPastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.PPastJobsFragment;
import com.cliknfix.user.responseModels.PastJobsResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MPastJobsFragment implements IMPastJobsFragment {

    IPPastJobsFragment ipPastJobsFragment;

    public MPastJobsFragment(PPastJobsFragment pPastJobsFragment) {
        this.ipPastJobsFragment = pPastJobsFragment;
    }

    @Override
    public void getPastJobsList(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getPastJobsList(token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.PAST_JOBS_SUCCESS:
                    PastJobsResponseModel pastJobsResponseModel = (PastJobsResponseModel) msg.obj;
                    ipPastJobsFragment.getPastJobsListSuccessResponse(pastJobsResponseModel);
                    break;
                case APIInterface.PAST_JOBS_NO_DATA:
                    String msgg = (String) msg.obj;
                    ipPastJobsFragment.getPastJobsListNoDataResponse(msgg);
                    break;
                case APIInterface.PAST_JOBS_FAILED:
                    String message = (String) msg.obj;
                    ipPastJobsFragment.getPastJobsListFailureResponse(message);
                    break;
            }
        }
    };
}
