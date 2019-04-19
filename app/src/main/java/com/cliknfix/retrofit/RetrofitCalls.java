package com.cliknfix.retrofit;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.cliknfix.base.MyApp;
import com.cliknfix.login.BeanLogin;
import com.cliknfix.responseModels.ForgotPasswordResponseModel;
import com.cliknfix.responseModels.SignUpResponseModel;
import com.cliknfix.responseModels.UserModelLoginResponse;
import com.cliknfix.signUp.BeanModelSignUp;
import com.cliknfix.util.PreferenceHandler;
import com.cliknfix.util.Utility;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCalls {

    private APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void loginUser(BeanLogin jUserModel, final Handler mHandler) {
        final Message message = new Message();
        Call<UserModelLoginResponse> call = apiInterface.loginUser(jUserModel);
        call.enqueue(new Callback<UserModelLoginResponse>() {
            @Override
            public void onResponse(Call<UserModelLoginResponse> call, Response<UserModelLoginResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGIN_SUCCESS;
                        message.obj = response.body();
                        Headers headerData = response.headers();
                        String token = headerData.get("access_token");
                        Log.d("+++++++++", "++ access token++" + token);
                        new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "Bearer" + " " + token);
                        String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                        Log.d("+++++++++", "++ access token read++" + mLoginToken);
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.LOGIN_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }

            }

            @Override
            public void onFailure(Call<UserModelLoginResponse> call, Throwable t) {
                message.what = apiInterface.LOGIN_FAILED;
                message.obj = t.getMessage();
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
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SIGNUP_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SIGNUP_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }


//                if (response.isSuccessful())
//                {
//                    Log.d("+++++++","++ code ++"+response.code());
//                    if (response.code()==200)
//                    {
//                        message.what = apiInterface.SIGNUP_SUCCESS;
//                        message.obj = response.body();
//                        mHandler.sendMessage(message);
//                    }
//                }
//                else
//                {
//                    message.what = apiInterface.SIGNUP_FAILED;
//                    message.obj = Utility.message(response).substring(Utility.message(response).lastIndexOf(":") + 2, Utility.message(response).length() - 2);
//                    mHandler.sendMessage(message);
//                }
            }

            @Override
            public void onFailure(Call<SignUpResponseModel> call, Throwable t) {
                message.what = apiInterface.SIGNUP_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    public void forgotPassword(String phoneNumber, final Handler mHandler) {
        final Message message = new Message();
        Call<ForgotPasswordResponseModel> call = apiInterface.forgotPass(phoneNumber);
        call.enqueue(new Callback<ForgotPasswordResponseModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponseModel> call, Response<ForgotPasswordResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        message.what = apiInterface.FORGOT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    }
                } else {
                    message.what = apiInterface.FORGOT_FAILED;
                    String ss = Utility.message(response);
                    message.obj = ss.substring(ss.lastIndexOf(":") + 1, ss.length() - 1);
                    Log.d("+++++++", "++++++" + ss.substring(ss.lastIndexOf(":") + 1, ss.length() - 1));
                    mHandler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponseModel> call, Throwable t) {

                message.what = apiInterface.FORGOT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);

            }
        });
    }

   /* public void verifyOtp(HashMap hashMap, final Handler mHandler) {
        final Message message = new Message();
        Call<OtpVerifyResponseModel> call = apiInterface.verifyOtp(hashMap);
        call.enqueue(new Callback<OtpVerifyResponseModel>() {
            @Override
            public void onResponse(Call<OtpVerifyResponseModel> call, Response<OtpVerifyResponseModel> response) {

                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        message.what = apiInterface.VERIFY_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    }
                } else {
                    message.what = apiInterface.VERIFY_FAILED;
                    message.obj = Utility.message(response);
                    mHandler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<OtpVerifyResponseModel> call, Throwable t) {
                message.what = apiInterface.VERIFY_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/

    /*public void getAllTypesForFilter(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<FilterTypeResponseModel> call = apiInterface.getTypesOfFilter(token);
        call.enqueue(new Callback<FilterTypeResponseModel>() {
            @Override
            public void onResponse(Call<FilterTypeResponseModel> call, Response<FilterTypeResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GETTYPEFILTER_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETTYPEFILTER_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<FilterTypeResponseModel> call, Throwable t) {
                message.what = apiInterface.GETTYPEFILTER_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/

   /* public void getAllServicesForFilter(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<FilterServicesResponseModel> call = apiInterface.getServicesOfFilter(token);
        call.enqueue(new Callback<FilterServicesResponseModel>() {
            @Override
            public void onResponse(Call<FilterServicesResponseModel> call, Response<FilterServicesResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GETSERVICESFILTER_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETSERVICESFILTER_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<FilterServicesResponseModel> call, Throwable t) {
                message.what = apiInterface.GETSERVICESFILTER_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/

   /* public void getAllFacilitiesForFilter(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<FilterFacilitiesResponseModel> call = apiInterface.getFacilitiesOfFilter(token);
        call.enqueue(new Callback<FilterFacilitiesResponseModel>() {
            @Override
            public void onResponse(Call<FilterFacilitiesResponseModel> call, Response<FilterFacilitiesResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GETFACILITIESFILTER_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETFACILITIESFILTER_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<FilterFacilitiesResponseModel> call, Throwable t) {
                message.what = apiInterface.GETFACILITIESFILTER_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/

    /*public void getSearchResult(HashMap hashMap, final Handler mHandler) {
        final Message message = new Message();
        Call<SearchResponseModel> call = apiInterface.getSearchResult(hashMap);
        call.enqueue(new Callback<SearchResponseModel>() {
            @Override
            public void onResponse(Call<SearchResponseModel> call, Response<SearchResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GETSEARCHRESULT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETSEARCHRESULT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResponseModel> call, Throwable t) {
                message.what = apiInterface.GETSEARCHRESULT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/


   /* public void sendImageToServer(MultipartBody.Part body, String token, final Handler mHandler) {
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "image");
        String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
        Log.d("+++++++++", "++ access token read++" + mLoginToken);
        final Message message = new Message();
        Call<UploadImageResponseModel> call = apiInterface.postImage(mLoginToken,name, body);
        call.enqueue(new Callback<UploadImageResponseModel>() {
            @Override
            public void onResponse(Call<UploadImageResponseModel> call, Response<UploadImageResponseModel> response) {
                Log.d("++++++", "++ response edit profile image ++" + response);

                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SENDPROFILEIMAGE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SENDPROFILEIMAGE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
                else
                {
                    message.what = apiInterface.SENDPROFILEIMAGE_FAILED;
                    message.obj = response.message();
                    mHandler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<UploadImageResponseModel> call, Throwable t) {
                Log.d("++++++", "++ response edit profile image ++" + t.getMessage());

                message.what = apiInterface.SENDPROFILEIMAGE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/

   /* public void getUserProfile(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<GetProfileResponseModel> call = apiInterface.getUserProfile(token);
        call.enqueue(new Callback<GetProfileResponseModel>() {
            @Override
            public void onResponse(Call<GetProfileResponseModel> call, Response<GetProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GETUSERPROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETUSERPROFILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.GETUSERPROFILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/


   /* public void changePassword(String token, HashMap hashMap, final Handler mHandler)
    {
        final Message message = new Message();
        Call<ChangePasswordResponseModel> call = apiInterface.changeUserPassword(token,hashMap);
        call.enqueue(new Callback<ChangePasswordResponseModel>() {
            @Override
            public void onResponse(Call<ChangePasswordResponseModel> call, Response<ChangePasswordResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.CHANGEPASSWORD_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.CHANGEPASSWORD_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }

            }

            @Override
            public void onFailure(Call<ChangePasswordResponseModel> call, Throwable t) {
                message.what = apiInterface.CHANGEPASSWORD_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/


   /* public void updateUserProfileDetails(String token,HashMap hashMap,final Handler mHandler)
    {
        final Message message = new Message();
        Call<EditProfileResponseModel> call = apiInterface.changeUserDetails(token,hashMap);
        call.enqueue(new Callback<EditProfileResponseModel>() {
            @Override
            public void onResponse(Call<EditProfileResponseModel> call, Response<EditProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.CHANGEUSERDETAILS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.CHANGEUSERDETAILS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<EditProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.CHANGEUSERDETAILS_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/

   /* public void editUserMobileNumber(String token,HashMap hashMap,final Handler mHandler)
    {
        final Message message = new Message();
        Call<EditMobileNumberResponseModel> call = apiInterface.changeMobileNumber(token,hashMap);
        call.enqueue(new Callback<EditMobileNumberResponseModel>() {
            @Override
            public void onResponse(Call<EditMobileNumberResponseModel> call, Response<EditMobileNumberResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.EDITMOBILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.EDITMOBILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<EditMobileNumberResponseModel> call, Throwable t) {
                message.what = apiInterface.EDITMOBILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/

    /*public void simpleSearchResultRestCall(HashMap hashMap,final Handler mHandler)
    {
        final Message message = new Message();
        Call<SearchResponseModel> call = apiInterface.getSimpleSearchResult(hashMap);
        call.enqueue(new Callback<SearchResponseModel>() {
            @Override
            public void onResponse(Call<SearchResponseModel> call, Response<SearchResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SIMPLESEARCH_SEARCH;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SIMPLESEARCH_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResponseModel> call, Throwable t) {
                message.what = apiInterface.SIMPLESEARCH_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    public void simpleSearchResultRestCallWithApi(String hashMap,final Handler mHandler)
    {
        final Message message = new Message();
        Call<SearchResponseModel> call = apiInterface.getSimpleSearchResultWithApi(hashMap);
        call.enqueue(new Callback<SearchResponseModel>() {
            @Override
            public void onResponse(Call<SearchResponseModel> call, Response<SearchResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SIMPLESEARCHAPI_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SIMPLESEARCHAPI_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResponseModel> call, Throwable t) {
                message.what = apiInterface.SIMPLESEARCHAPI_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }*/

    /*public void getSpaDetailRestCall(int id,final Handler mHandler)
    {
        final Message message = new Message();
        Call<SingleSpaDetailResponseModel> call = apiInterface.getSpaDetail(id);
        call.enqueue(new Callback<SingleSpaDetailResponseModel>() {
            @Override
            public void onResponse(Call<SingleSpaDetailResponseModel> call, Response<SingleSpaDetailResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SPADETAIL_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SPADETAIL_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleSpaDetailResponseModel> call, Throwable t) {
                message.what = apiInterface.SPADETAIL_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }*/

   /* public void getTreatmentsRestCall(int id,final Handler mHandler)
    {
        final Message message = new Message();
        Call<TreatmentsResponseModel> call = apiInterface.getTreatments(id);
        call.enqueue(new Callback<TreatmentsResponseModel>() {
            @Override
            public void onResponse(Call<TreatmentsResponseModel> call, Response<TreatmentsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GETTREATMENTS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETTREATMENTS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<TreatmentsResponseModel> call, Throwable t) {
                message.what = apiInterface.GETTREATMENTS_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }*/

    /*public void getTreatmentDetailRestCall(int treatmentId,final Handler mHandler)
    {
        final Message message = new Message();
        Call<TreatmentDetailsResponseModel> call = apiInterface.getTreatmentDetail(treatmentId);
        call.enqueue(new Callback<TreatmentDetailsResponseModel>() {
            @Override
            public void onResponse(Call<TreatmentDetailsResponseModel> call, Response<TreatmentDetailsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GETTREATMENTDETAIL_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETTREATMENTDETAIL_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<TreatmentDetailsResponseModel> call, Throwable t) {
                message.what = apiInterface.GETTREATMENTDETAIL_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });

    }*/

 /*   public void getPopularItemList(String lat,String lng,String distance,final Handler mHandler)
    {
        final Message message = new Message();
        Call<PopularListResponseModel> call = apiInterface.getPopularList(lat, lng, distance);
        call.enqueue(new Callback<PopularListResponseModel>() {
            @Override
            public void onResponse(Call<PopularListResponseModel> call, Response<PopularListResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GETPOPULARLIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETPOPULARLIST_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<PopularListResponseModel> call, Throwable t) {
                message.what = apiInterface.GETPOPULARLIST_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }*/

    /*public void getNearByItemList(String lat,String lng,String distance,final Handler mHandler)
    {
        final Message message = new Message();
        Call<NearByListResponseModel> call = apiInterface.getNearByList(lat,lng,distance);
        call.enqueue(new Callback<NearByListResponseModel>() {
            @Override
            public void onResponse(Call<NearByListResponseModel> call, Response<NearByListResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.GETNEARBYLIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETNEARBYLIST_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<NearByListResponseModel> call, Throwable t) {
                message.what = apiInterface.GETNEARBYLIST_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }*/

    /*public void addToBasketRestCall(String token,HashMap hashMap,final Handler mHandler)
    {
        final Message message = new Message();
        Call<AddToBasketResponseModel> call = apiInterface.addToBasket(token,hashMap);
        call.enqueue(new Callback<AddToBasketResponseModel>() {
            @Override
            public void onResponse(Call<AddToBasketResponseModel> call, Response<AddToBasketResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.ADDTOBASKET_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.ADDTOBASKET_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }

            }

            @Override
            public void onFailure(Call<AddToBasketResponseModel> call, Throwable t) {
                message.what = apiInterface.ADDTOBASKET_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }*/

}
