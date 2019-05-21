package com.cliknfix.user.technicianReview;

import com.cliknfix.user.responseModels.SubmitTechReviewResponseModel;

public class PTechReviewActivity implements IPTechReviewActivity {

    private ITechReviewActivity iTechReviewActivity;
    private IMTechReviewActivity imTechReviewActivity;

    public PTechReviewActivity(TechReviewActivity techReviewActivity) {
        iTechReviewActivity = techReviewActivity;
        imTechReviewActivity = new MTechReviewActivity(this);
    }

    @Override
    public void submitTechReview(String numStars, String review, int techId, String token) {
        imTechReviewActivity.submitTechReview(numStars,review,techId,token);
    }

    @Override
    public void submitTechReviewSuccessResponse(SubmitTechReviewResponseModel submitTechReviewResponseModel) {
        iTechReviewActivity.submitTechReviewSuccessResponseFromPresenter(submitTechReviewResponseModel);
    }

    @Override
    public void submitTechReviewFailureResponse(String message) {
        iTechReviewActivity.submitTechReviewFailureResponseFromPresenter(message);
    }
}
