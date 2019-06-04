package com.cliknfix.user.mobile;

import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.responseModels.SignUpResponseModel;

public interface IMobileNoActivity {
    void onSendOTPSuccessFromPresenter(MobileNoResponseModel mobileNoResponseModel);
    void onSendOTPFailureFromPresenter(String message);
    void onSignUpResponseSuccessFromPresenter(SignUpResponseModel signUpResponseModel);
    void onSignUpResponseFailureFromPresenter(String msgg);
}
