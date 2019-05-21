package com.cliknfix.user.changePassword;

import android.os.Message;

import com.cliknfix.user.responseModels.ChangePasswordResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MChangePasswordActivity implements IMChangePasswordActivity {

    IPChangePasswordActivity ipChangePasswordActivity;
    String value;

    public MChangePasswordActivity(PChangePasswordActivity pChangePasswordActivity) {
        ipChangePasswordActivity = pChangePasswordActivity;
    }

    @Override
    public void changePassword(String oldPass, String newPass, String confirmPass, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.changePassword(oldPass,newPass,confirmPass,token,mHandler);

    }


    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.CHANGE_PASSWORD_SUCCESS:
                    ChangePasswordResponseModel changePasswordResponseModel = (ChangePasswordResponseModel) msg.obj;
                    ipChangePasswordActivity.onChangePasswordSuccessResponse(changePasswordResponseModel);

                    break;
                case APIInterface.CHANGE_PASSWORD_FAILED:
                    String message = (String) msg.obj;
                    ipChangePasswordActivity.onChangePasswordFailureResponse(message);
                    break;
            }
        }
    };
}
