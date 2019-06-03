package com.cliknfix.user.paymentMethods.presenter;

import com.cliknfix.user.paymentMethods.IStartPaymentActivity;
import com.cliknfix.user.paymentMethods.StartPaymentActivity;
import com.cliknfix.user.paymentMethods.model.IMStartPaymentActivity;
import com.cliknfix.user.paymentMethods.model.MStartPaymentActivity;
import com.cliknfix.user.responseModels.PaymentDoneResponseModel;

public class PStartPaymentActivity implements IPStartPaymentActivity {

    IStartPaymentActivity iStartPaymentActivity;
    IMStartPaymentActivity imStartPaymentActivity;

    public PStartPaymentActivity(StartPaymentActivity startPaymentActivity) {
        iStartPaymentActivity = startPaymentActivity;
        imStartPaymentActivity = new MStartPaymentActivity(this);
    }

    @Override
    public void paymentDone(String amount, String token) {
        imStartPaymentActivity.paymentDone(amount,token);
    }

    @Override
    public void paymentDoneSuccess(PaymentDoneResponseModel paymentDoneResponseModel) {
        iStartPaymentActivity.paymentDoneSuccessFromPresenter(paymentDoneResponseModel);
    }

    @Override
    public void paymentDoneFailed(String msgg) {
        iStartPaymentActivity.paymentDoneFailedFromPresenter(msgg);
    }
}
