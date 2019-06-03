package com.cliknfix.user.paymentMethods.presenter;

import com.cliknfix.user.responseModels.PaymentDoneResponseModel;

public interface IPStartPaymentActivity {
    void paymentDone(String amount, String token);
    void paymentDoneSuccess(PaymentDoneResponseModel paymentDoneResponseModel);
    void paymentDoneFailed(String msgg);
}
