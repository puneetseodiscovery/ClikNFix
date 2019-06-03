package com.cliknfix.user.paymentMethods;

import com.cliknfix.user.responseModels.PaymentDoneResponseModel;

public interface IStartPaymentActivity {
    void paymentDoneSuccessFromPresenter(PaymentDoneResponseModel paymentDoneResponseModel);
    void paymentDoneFailedFromPresenter(String msgg);
}
