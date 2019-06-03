package com.cliknfix.user.paymentMethods.model;

public interface IMStartPaymentActivity {
    void paymentDone(String amount, String token);
}
