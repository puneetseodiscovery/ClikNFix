package com.cliknfix.user.changePassword;

public interface IMChangePasswordActivity {
    void changePassword(String oldPass, String newPass, String confirmPass, String token);
}
