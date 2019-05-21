package com.cliknfix.user.technicianReview;

import android.os.Message;

import com.cliknfix.user.responseModels.SubmitTechReviewResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MTechReviewActivity implements IMTechReviewActivity {

    IPTechReviewActivity ipTechReviewActivity;
    String value;

    public MTechReviewActivity(PTechReviewActivity pTechReviewActivity) {
        ipTechReviewActivity = pTechReviewActivity;
    }

    @Override
    public void submitTechReview(String numStars, String review, int techId, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.submitTechReview(numStars,review,techId,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.SUBMIT_TECH_REVIEW_SUCCESS:
                    SubmitTechReviewResponseModel submitTechReviewResponseModel = (SubmitTechReviewResponseModel) msg.obj;
                    ipTechReviewActivity.submitTechReviewSuccessResponse(submitTechReviewResponseModel);

                    break;
                case APIInterface.SUBMIT_TECH_REVIEW_FAILURE:
                    String message = (String) msg.obj;
                    ipTechReviewActivity.submitTechReviewFailureResponse(message);
                    break;
            }
        }
    };
}
