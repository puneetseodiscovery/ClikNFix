package com.cliknfix.user.technicianReview;

import com.cliknfix.user.responseModels.SubmitTechReviewResponseModel;

public interface ITechReviewActivity {
    void submitTechReviewSuccessResponseFromPresenter(SubmitTechReviewResponseModel submitTechReviewResponseModel);
    void submitTechReviewFailureResponseFromPresenter(String message);
}
