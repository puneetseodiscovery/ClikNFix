package com.cliknfix.paymentMethods.model;

public class BeanPayment {

    int paymentImg;
    String paymentMethodName;

    public BeanPayment(int paymentImg, String paymentMethodName) {
        this.paymentImg = paymentImg;
        this.paymentMethodName = paymentMethodName;
    }

    public int getPaymentImg() {
        return paymentImg;
    }

    public void setPaymentImg(int paymentImg) {
        this.paymentImg = paymentImg;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

}
