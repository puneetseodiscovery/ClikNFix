package com.cliknfix.user.homeScreen.bottomFragments.model;

import android.os.Message;

import com.cliknfix.user.homeScreen.bottomFragments.presenter.PHomeFragment;
import com.cliknfix.user.responseModels.CategoriesListResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MHomeFragment implements IMHomeFragment {

    PHomeFragment pHomeFragment;

    public MHomeFragment(PHomeFragment pHomeFragment) {
        this.pHomeFragment = pHomeFragment;
    }

    @Override
    public void getCategoriesList(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getCategoriesList(token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.GETCATEGORYLIST_SUCCESS:
                    CategoriesListResponseModel categoriesListResponseModel = (CategoriesListResponseModel) msg.obj;
                    pHomeFragment.getCategoriesListSuccessResponse(categoriesListResponseModel);
                    break;

                case APIInterface.GETCATEGORYLIST_FAILED:
                    String message = (String) msg.obj;
                    pHomeFragment.getCategoriesListFailureResponse(message);
                    break;
            }
        }
    };
}
