package com.cliknfix.user.paymentMethods.model;

import android.os.Message;

import com.cliknfix.user.paymentMethods.presenter.IPStartPaymentActivity;
import com.cliknfix.user.paymentMethods.presenter.PStartPaymentActivity;
import com.cliknfix.user.responseModels.PaymentDoneResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MStartPaymentActivity implements IMStartPaymentActivity {

    IPStartPaymentActivity ipStartPaymentActivity;

    public MStartPaymentActivity(PStartPaymentActivity pStartPaymentActivity) {
        this.ipStartPaymentActivity = pStartPaymentActivity;
    }

    @Override
    public void paymentDone(String amount, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.paymentDone(amount,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.PAYMENT_DONE_SUCCESS:
                    PaymentDoneResponseModel paymentDoneResponseModel = (PaymentDoneResponseModel) msg.obj;
                    ipStartPaymentActivity.paymentDoneSuccess(paymentDoneResponseModel);
                    break;
                case APIInterface.PAYMENT_DONE_FAILED:
                    String msgg = (String) msg.obj;
                    ipStartPaymentActivity.paymentDoneFailed(msgg);
                    break;
            }
        }
    };
}
