package com.cliknfix.user.contact;

import com.cliknfix.user.responseModels.ContactUsResponseModel;

public interface IContactUsActivity {
    void contactUsFailureResponseFromPresenter(String message);
    void contactUsSuccessResponseFromPresenter(ContactUsResponseModel contactUsResponseModel);
}
