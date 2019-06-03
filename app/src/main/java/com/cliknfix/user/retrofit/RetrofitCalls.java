package com.cliknfix.user.retrofit;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


import com.cliknfix.user.base.MyApp;
import com.cliknfix.user.login.BeanLogin;
import com.cliknfix.user.responseModels.AboutUsResponseModel;
import com.cliknfix.user.responseModels.CategoriesListResponseModel;
import com.cliknfix.user.responseModels.ChangePasswordResponseModel;
import com.cliknfix.user.responseModels.ContactUsResponseModel;
import com.cliknfix.user.responseModels.ForgotPasswordResponseModel;
import com.cliknfix.user.responseModels.LogoutResponseModel;
import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.responseModels.OTPResponseModel;
import com.cliknfix.user.responseModels.PastJobsResponseModel;
import com.cliknfix.user.responseModels.PaymentDoneResponseModel;
import com.cliknfix.user.responseModels.PrivacyPolicyResponseModel;
import com.cliknfix.user.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.user.responseModels.SearchTechResponseModel;
import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.responseModels.LoginResponseModel;
import com.cliknfix.user.responseModels.SocialLoginResponseModel;
import com.cliknfix.user.responseModels.SubmitTechReviewResponseModel;
import com.cliknfix.user.responseModels.TechDetailResponseModel;
import com.cliknfix.user.responseModels.UserProfileResponseModel;
import com.cliknfix.user.signUp.BeanModelSignUp;
import com.cliknfix.user.util.PreferenceHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCalls {

    private APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void loginUser(String email, String password,String device_token, final Handler mHandler) {
        final Message message = new Message();
        Call<LoginResponseModel> call = apiInterface.loginUser(email,password,device_token);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    if (response.body() != null) {
                        Log.e("Status().code","" + response.code());
                        if (response.body().getStatus().equalsIgnoreCase("200")) {
                            message.what = apiInterface.LOGIN_SUCCESS;
                            message.obj = response.body();
                            String token = response.body().getData().get(0).getRememberToken();
                            int id = response.body().getData().get(0).getId();
                            Log.d("+++++++++", "++ access token++" + token);
                            Log.d("+++++++++", "++ id++" + id);
                            new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, token);
                            new PreferenceHandler().writeInteger(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_USER_ID, id);
                            String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                            int userId = new PreferenceHandler().readInteger(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_USER_ID, 0);
                            Log.d("+++++++++", "++ access token read++" + mLoginToken);
                            Log.d("+++++++++", "++ id read++" + userId);
                            mHandler.sendMessage(message);
                        } else if(response.body().getStatus().equalsIgnoreCase("401")){
                            message.what = apiInterface.OTP_NOT_VERIFIED;
                            message.obj = response.body();
                            mHandler.sendMessage(message);
                        }else {
                            message.what = apiInterface.LOGIN_FAILED;
                            message.obj = response.body().getMessage();
                            mHandler.sendMessage(message);
                        }
                    }

                }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.e("Status().equals(200)","SUCCESS");
                message.what = apiInterface.LOGIN_FAILED;
                message.obj = t.getMessage();
                Log.e("Error msg","" + t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void signUpUser(BeanModelSignUp beanModelSignUp, final Handler mHandler) {
        final Message message = new Message();
        Call<SignUpResponseModel> call = apiInterface.signUpUser(beanModelSignUp);
        call.enqueue(new Callback<SignUpResponseModel>() {
            @Override
            public void onResponse(Call<SignUpResponseModel> call, Response<SignUpResponseModel> response) {

                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.SIGNUP_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SIGNUP_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponseModel> call, Throwable t) {
                message.what = apiInterface.SIGNUP_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    public void forgotPassword(String email, final Handler mHandler) {
        final Message message = new Message();
        Call<ForgotPasswordResponseModel> call = apiInterface.forgotPass(email);
        call.enqueue(new Callback<ForgotPasswordResponseModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponseModel> call, Response<ForgotPasswordResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.FORGOT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.FORGOT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponseModel> call, Throwable t) {
                message.what = apiInterface.FORGOT_FAILED;
                message.obj = t.getMessage();
                Log.d("Error msg:" ,"" +t.getMessage());
                mHandler.sendMessage(message);

            }
        });
    }

    public void getCategoriesList(String token, final Handler mHandler)
    {
        final Message message = new Message();
        Call<CategoriesListResponseModel> call = apiInterface.getCategoriesList(token.trim());
        call.enqueue(new Callback<CategoriesListResponseModel>() {
            @Override
            public void onResponse(Call<CategoriesListResponseModel> call, Response<CategoriesListResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.GETCATEGORYLIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETCATEGORYLIST_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<CategoriesListResponseModel> call, Throwable t) {
                message.what = apiInterface.GETCATEGORYLIST_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void sendOTP(String phone,String user_id,String resend_otp, final Handler mHandler) {
        final Message message = new Message();
        Call<MobileNoResponseModel> call = apiInterface.sendOTP(phone,user_id,resend_otp);
        call.enqueue(new Callback<MobileNoResponseModel>() {
            @Override
            public void onResponse(Call<MobileNoResponseModel> call, Response<MobileNoResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        Log.e("response1:","" + response.body().getStatus());
                        message.what = apiInterface.SEND_OTP_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        Log.e("response2:","" + response.body().getStatus());
                        message.what = apiInterface.SEND_OTP_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<MobileNoResponseModel> call, Throwable t) {
                message.what = apiInterface.SEND_OTP_FAILED;
                message.obj = t.getMessage();
                Log.e("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void fillOTP(String phone,String otp,String user_id, final Handler mHandler) {
        final Message message = new Message();
        Call<OTPResponseModel> call = apiInterface.fillOTP(phone,otp,user_id);
        call.enqueue(new Callback<OTPResponseModel>() {
            @Override
            public void onResponse(Call<OTPResponseModel> call, Response<OTPResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.FILL_OTP_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.FILL_OTP_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<OTPResponseModel> call, Throwable t) {
                message.what = apiInterface.FILL_OTP_FAILURE;
                message.obj = t.getMessage();
                Log.e("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void getUserProfile(int user_id, String token, final Handler mHandler) {
        final Message message = new Message();
        Call<UserProfileResponseModel> call = apiInterface.getUserProfile(token,user_id);
        call.enqueue(new Callback<UserProfileResponseModel>() {
            @Override
            public void onResponse(Call<UserProfileResponseModel> call, Response<UserProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.USER_PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.USER_PROFILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<UserProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.USER_PROFILE_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void doLogout(int user_id, final Handler mHandler) {
        final Message message = new Message();
        Call<LogoutResponseModel> call = apiInterface.doLogout(user_id);
        call.enqueue(new Callback<LogoutResponseModel>() {
            @Override
            public void onResponse(Call<LogoutResponseModel> call, Response<LogoutResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.LOGOUT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.LOGOUT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<LogoutResponseModel> call, Throwable t) {
                message.what = apiInterface.GETCATEGORYLIST_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });

    }

    public void contactUs(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<ContactUsResponseModel> call = apiInterface.contactUS(token);
        call.enqueue(new Callback<ContactUsResponseModel>() {
            @Override
            public void onResponse(Call<ContactUsResponseModel> call, Response<ContactUsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.CONTACT_US_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.CONTACT_US_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<ContactUsResponseModel> call, Throwable t) {
                message.what = apiInterface.CONTACT_US_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void privacyPolicy(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<PrivacyPolicyResponseModel> call = apiInterface.privacyPolicy(token);
        call.enqueue(new Callback<PrivacyPolicyResponseModel>() {
            @Override
            public void onResponse(Call<PrivacyPolicyResponseModel> call, Response<PrivacyPolicyResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.PRIVACY_POLICY_SUCCESSS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PRIVACY_POLICY_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<PrivacyPolicyResponseModel> call, Throwable t) {
                message.what = apiInterface.PRIVACY_POLICY_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });

    }

    public void changePassword(String current_password, String your_password, String confirm_password, String token, final Handler mHandler) {
        final Message message = new Message();
        Call<ChangePasswordResponseModel> call = apiInterface.changePassword(current_password,your_password,confirm_password,token);
        call.enqueue(new Callback<ChangePasswordResponseModel>() {
            @Override
            public void onResponse(Call<ChangePasswordResponseModel> call, Response<ChangePasswordResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.CHANGE_PASSWORD_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.CHANGE_PASSWORD_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<ChangePasswordResponseModel> call, Throwable t) {
                message.what = apiInterface.CHANGE_PASSWORD_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void saveUserProfile(String name, String phone, String blood_group, String age, String address, String imgUrl, String token, final Handler mHandler) {
        final Message message = new Message();
        Call<SaveUserProfileResponseModel> call = apiInterface.saveUserProfile(name,phone,blood_group,age,address,imgUrl,token);
        call.enqueue(new Callback<SaveUserProfileResponseModel>() {
            @Override
            public void onResponse(Call<SaveUserProfileResponseModel> call, Response<SaveUserProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.SAVE_USER_PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SAVE_USER_PROFILE_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<SaveUserProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.SAVE_USER_PROFILE_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void submitTechReview(String rating, String review, int technician_id, String token, final Handler mHandler) {
        final Message message = new Message();
        Call<SubmitTechReviewResponseModel> call = apiInterface.submitTechReview(rating,review,technician_id,token);
        call.enqueue(new Callback<SubmitTechReviewResponseModel>() {
            @Override
            public void onResponse(Call<SubmitTechReviewResponseModel> call, Response<SubmitTechReviewResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.SUBMIT_TECH_REVIEW_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SUBMIT_TECH_REVIEW_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<SubmitTechReviewResponseModel> call, Throwable t) {
                message.what = apiInterface.SUBMIT_TECH_REVIEW_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void aboutUs(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<AboutUsResponseModel> call = apiInterface.aboutUs(token);
        call.enqueue(new Callback<AboutUsResponseModel>() {
            @Override
            public void onResponse(Call<AboutUsResponseModel> call, Response<AboutUsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.ABOUT_US_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.ABOUT_US_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<AboutUsResponseModel> call, Throwable t) {
                message.what = apiInterface.ABOUT_US_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }


    public void searchTech(int category_id, String user_query, double lat, double lng, String token, final Handler mHandler) {
        Log.e("category_id","" + category_id);
        Log.e("user_query",user_query);
        Log.e("lat","" + lat);
        Log.e("lng","" + lng);
        Log.e("token",token);
        final Message message = new Message();
        Call<SearchTechResponseModel> call = apiInterface.searchTech(token,category_id,user_query,lat,lng);
        call.enqueue(new Callback<SearchTechResponseModel>() {
            @Override
            public void onResponse(Call<SearchTechResponseModel> call, Response<SearchTechResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.SEARCH_TECH_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else if(response.body().getStatus().equals("401")){
                        message.what = apiInterface.NO_TECH_AVAILABLE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }else {
                        message.what = apiInterface.SEARCH_TECH_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<SearchTechResponseModel> call, Throwable t) {
                message.what = apiInterface.SEARCH_TECH_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void getPastJobsList(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<PastJobsResponseModel> call = apiInterface.getPastJobsList(token);
        call.enqueue(new Callback<PastJobsResponseModel>() {
            @Override
            public void onResponse(Call<PastJobsResponseModel> call, Response<PastJobsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.PAST_JOBS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else if (response.body().getStatus().equals("401")) {
                        message.what = apiInterface.PAST_JOBS_NO_DATA;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PAST_JOBS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<PastJobsResponseModel> call, Throwable t) {
                message.what = apiInterface.GETCATEGORYLIST_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }


    public void doLogin(String email, String name, String deviceToken, final Handler mHandler) {
        final Message message = new Message();
        Call<SocialLoginResponseModel> call = apiInterface.doLogin(email,name,deviceToken);
        call.enqueue(new Callback<SocialLoginResponseModel>() {
            @Override
            public void onResponse(Call<SocialLoginResponseModel> call, Response<SocialLoginResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("200")) {
                        message.what = apiInterface.SOCIAL_LOGIN_SUCCESS;
                        message.obj = response.body();
                        String token = response.body().getData().get(0).getRememberToken();
                        int id = response.body().getData().get(0).getId();
                        Log.d("+++++++++", "++ access token++" + token);
                        Log.d("+++++++++", "++ id++" + id);
                        new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, token);
                        new PreferenceHandler().writeInteger(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_USER_ID, id);
                        String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                        int userId = new PreferenceHandler().readInteger(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_USER_ID, 0);
                        Log.d("+++++++++", "++ access token read++" + mLoginToken);
                        Log.d("+++++++++", "++ id read++" + userId);
                        mHandler.sendMessage(message);
                    } else if(response.body().getStatus().equalsIgnoreCase("401")){
                        message.what = apiInterface.SOCIAL_OTP_NOT_VERIFIED;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    }else {
                        message.what = apiInterface.SOCIAL_LOGIN_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }

            }

            @Override
            public void onFailure(Call<SocialLoginResponseModel> call, Throwable t) {
                Log.e("Status().equals(200)","SUCCESS");
                message.what = apiInterface.SOCIAL_LOGIN_FAILED;
                message.obj = t.getMessage();
                Log.e("Error msg","" + t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void paymentDone(String total_earning, String token, final Handler mHandler) {
        final Message message = new Message();
        Call<PaymentDoneResponseModel> call = apiInterface.paymentDone(total_earning,token);
        call.enqueue(new Callback<PaymentDoneResponseModel>() {
            @Override
            public void onResponse(Call<PaymentDoneResponseModel> call, Response<PaymentDoneResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("200")) {
                        message.what = apiInterface.PAYMENT_DONE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PAYMENT_DONE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }

            }

            @Override
            public void onFailure(Call<PaymentDoneResponseModel> call, Throwable t) {
                message.what = apiInterface.PAYMENT_DONE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    public void getTechDetail(int id, String token, final Handler mHandler) {
        final Message message = new Message();
        Call<TechDetailResponseModel> call = apiInterface.getTechDetail(id,token);
        call.enqueue(new Callback<TechDetailResponseModel>() {
            @Override
            public void onResponse(Call<TechDetailResponseModel> call, Response<TechDetailResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("200")) {
                        message.what = apiInterface.TECH_DETAIL_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.TECH_DETAIL_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }

            }

            @Override
            public void onFailure(Call<TechDetailResponseModel> call, Throwable t) {
                message.what = apiInterface.TECH_DETAIL_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }
}
