package com.cliknfix.user.contact;

import android.os.Message;

import com.cliknfix.user.responseModels.ContactUsResponseModel;
import com.cliknfix.user.retrofit.APIInterface;
import com.cliknfix.user.retrofit.RetrofitCalls;

public class MContactUsActivity implements IMContactUsActivity {

    IPContactUsActivity ipContactUsActivity;
    String value;

    public MContactUsActivity(PContactUsActivity pContactUsActivity) {
        ipContactUsActivity = pContactUsActivity;
    }

    @Override
    public void contactUs(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.contactUs(token,mHandler);

    }


    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.CONTACT_US_SUCCESS:
                    ContactUsResponseModel contactUsResponseModel = (ContactUsResponseModel) msg.obj;
                    ipContactUsActivity.onContactUsSuccessResponse(contactUsResponseModel);

                    break;
                case APIInterface.CONTACT_US_FAILURE:
                    String message = (String) msg.obj;
                    ipContactUsActivity.onContactUsFailureResponse(message);
                    break;
            }
        }
    };
}
