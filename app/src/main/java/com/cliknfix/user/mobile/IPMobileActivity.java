package com.cliknfix.user.mobile;

import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.responseModels.SignUpResponseModel;

public interface IPMobileActivity {
    void sendOTP(String phone,String user_id,String resend_otp);
    void onSendOTPSuccess(MobileNoResponseModel mobileNoResponseModel);
    void onSendOTPFailure(String message);
    void doSignUp(String name, String email, String s, String s1, String s2, String phone, String phone1);
    void onSignUpResponseSuccessFromModel(SignUpResponseModel signUpResponseModel);
    void onSignUpResponseFailureFromModel(String msgg);
}
