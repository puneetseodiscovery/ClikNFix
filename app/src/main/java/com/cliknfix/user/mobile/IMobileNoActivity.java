package com.cliknfix.user.mobile;

import com.cliknfix.user.responseModels.MobileNoResponseModel;

public interface IMobileNoActivity {
    void onSendOTPSuccessFromPresenter(MobileNoResponseModel mobileNoResponseModel);
    void onSendOTPFailureFromPresenter(String message);
}
