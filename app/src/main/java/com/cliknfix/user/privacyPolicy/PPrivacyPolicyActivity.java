package com.cliknfix.user.privacyPolicy;

import com.cliknfix.user.responseModels.PrivacyPolicyResponseModel;

public class PPrivacyPolicyActivity implements IPPrivacyPolicyActivity {

    private IPrivacyPolicyActivity iPrivacyPolicyActivity;
    private IMPrivacyPolicyActivity imPrivacyPolicyActivity;

    public PPrivacyPolicyActivity(PrivacyPolicyActivity privacyPolicyActivity) {
        iPrivacyPolicyActivity = privacyPolicyActivity;
        imPrivacyPolicyActivity = new MPrivacyPolicyActivity(this);
    }

    @Override
    public void privacyPolicy(String token) {
        imPrivacyPolicyActivity.privacyPolicy(token);
    }

    @Override
    public void onPrivacyPolicySuccessResponse(PrivacyPolicyResponseModel privacyPolicyResponseModel) {
        iPrivacyPolicyActivity.privacyPolicySuccessResponseFromPresenter(privacyPolicyResponseModel);
    }

    @Override
    public void onPrivacyPolicyFailureResponse(String message) {
        iPrivacyPolicyActivity.privacyPolicyFailureResponseFromPresenter(message);
    }
}
