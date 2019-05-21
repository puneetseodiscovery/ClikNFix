package com.cliknfix.user.search;

import android.os.Message;

import com.cliknfix.user.responseModels.SearchTechResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MSearchActivity implements IMSearchActivity {

    IPSearchActivity ipSearchActivity;

    public MSearchActivity(PSearchActivity pSearchActivity) {
        ipSearchActivity = pSearchActivity;
    }

    @Override
    public void searchTech(int categoryId, String problem, double latitude, double longitude, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.searchTech(categoryId,problem,latitude,longitude,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.SEARCH_TECH_SUCCESS:
                    SearchTechResponseModel searchTechResponseModel = (SearchTechResponseModel) msg.obj;
                    ipSearchActivity.onSearchTechSuccessResponse(searchTechResponseModel);
                    break;
                case APIInterface.NO_TECH_AVAILABLE:
                    String message = (String) msg.obj;
                    ipSearchActivity.noTechFoundResponse(message);
                    break;
                case APIInterface.SEARCH_TECH_FAILURE:
                    String msgg = (String) msg.obj;
                    ipSearchActivity.onSearchTechFailureResponse(msgg);
                    break;
            }
        }
    };
}
