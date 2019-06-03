package com.cliknfix.user.homeScreen.bottomFragments.model;

import android.os.Message;

import com.cliknfix.user.homeScreen.bottomFragments.presenter.IPPastJobsAdapter;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.PPastJobsAdapter;
import com.cliknfix.user.responseModels.TechDetailResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MPastJobsAdapter implements IMPastJobsAdapter {

    IPPastJobsAdapter ipPastJobsAdapter;

    public MPastJobsAdapter(PPastJobsAdapter pPastJobsAdapter) {
        this.ipPastJobsAdapter = pPastJobsAdapter;
    }

    @Override
    public void getTechDetail(int id, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getTechDetail(id,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.TECH_DETAIL_SUCCESS:
                    TechDetailResponseModel techDetailResponseModel = (TechDetailResponseModel) msg.obj;
                    ipPastJobsAdapter.getTechDetailSuccessResponse(techDetailResponseModel);
                    break;
                case APIInterface.TECH_DETAIL_FAILED:
                    String message = (String) msg.obj;
                    ipPastJobsAdapter.getTechDetailFailureResponse(message);
                    break;
            }
        }
    };
}
