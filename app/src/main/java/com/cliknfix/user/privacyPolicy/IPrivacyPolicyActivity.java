package com.cliknfix.user.privacyPolicy;

import com.cliknfix.user.responseModels.PrivacyPolicyResponseModel;

public interface IPrivacyPolicyActivity {
    void privacyPolicySuccessResponseFromPresenter(PrivacyPolicyResponseModel privacyPolicyResponseModel);
    void privacyPolicyFailureResponseFromPresenter(String message);
}
