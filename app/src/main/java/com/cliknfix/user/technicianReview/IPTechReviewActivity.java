package com.cliknfix.user.technicianReview;

import com.cliknfix.user.responseModels.SubmitTechReviewResponseModel;

public interface IPTechReviewActivity {
    void submitTechReview(String numStars, String review, int techId, String token);
    void submitTechReviewSuccessResponse(SubmitTechReviewResponseModel submitTechReviewResponseModel);
    void submitTechReviewFailureResponse(String message);
}
