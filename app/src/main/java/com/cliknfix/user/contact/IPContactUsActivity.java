package com.cliknfix.user.contact;

import com.cliknfix.user.responseModels.ContactUsResponseModel;

public interface IPContactUsActivity {
    void contactUs(String token);

    void onContactUsSuccessResponse(ContactUsResponseModel contactUsResponseModel);

    void onContactUsFailureResponse(String message);
}
