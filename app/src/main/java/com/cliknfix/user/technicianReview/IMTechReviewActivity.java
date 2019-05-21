package com.cliknfix.user.technicianReview;

public interface IMTechReviewActivity {
    void submitTechReview(String numStars, String review, int techId, String token);
}
