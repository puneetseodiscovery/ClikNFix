package com.cliknfix.user.privacyPolicy;

import com.cliknfix.user.responseModels.PrivacyPolicyResponseModel;

public interface IPPrivacyPolicyActivity {
    void privacyPolicy(String token);
    void onPrivacyPolicySuccessResponse(PrivacyPolicyResponseModel privacyPolicyResponseModel);
    void onPrivacyPolicyFailureResponse(String message);
}
