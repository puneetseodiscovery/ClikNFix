package com.cliknfix.user.mobile;

public interface IMMobileActivity {
    void sendOTP(String phone,String user_id,String resend_otp);
    void doSignUp(String name, String email, String age, String bg, String add, String phone, String pass);
}
