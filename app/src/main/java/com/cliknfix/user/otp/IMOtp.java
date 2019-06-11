package com.cliknfix.user.otp;

public interface IMOtp {
    void fillOTP(String phone,String otp,String user_id);
    void resendOTP(String phone, String user_id, String resend_otp);
}
