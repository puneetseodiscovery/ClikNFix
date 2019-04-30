package com.cliknfix.user.retrofit;


import com.cliknfix.user.login.BeanLogin;
import com.cliknfix.user.responseModels.CategoriesListResponseModel;
import com.cliknfix.user.responseModels.ForgotPasswordResponseModel;
import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.responseModels.OTPResponseModel;
import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.responseModels.UserModelLoginResponse;
import com.cliknfix.user.responseModels.UserProfileResponseModel;
import com.cliknfix.user.signUp.BeanModelSignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAILED = 2;
    public static final int SIGNUP_SUCCESS = 3;
    public static final int SIGNUP_FAILED = 4;
    public static final int FORGOT_SUCCESS = 5;
    public static final int FORGOT_FAILED = 6;
    public static final int VERIFY_SUCCESS = 7;
    public static final int VERIFY_FAILED = 9;
    public static final int GETCATEGORYLIST_SUCCESS= 10;
    public static final int GETCATEGORYLIST_FAILED= 11;
    public static final int SEND_OTP_SUCCESS= 12;
    public static final int SEND_OTP_FAILED= 13;
    public static final int USER_PROFILE_SUCCESS= 14;
    public static final int USER_PROFILE_FAILED= 15;
    public static final int FILL_OTP_SUCCESS= 16;
    public static final int FILL_OTP_FAILURE= 17;
    public static final int SENDPROFILEIMAGE_SUCCESS= 18;
    public static final int SENDPROFILEIMAGE_FAILED= 19;
    public static final int GETUSERPROFILE_SUCCESS= 20;
    public static final int GETUSERPROFILE_FAILED= 21;
    public static final int EDITMOBILE_SUCCESS= 22;
    public static final int EDITMOBILE_FAILED= 23;
    public static final int CHANGEPASSWORD_SUCCESS= 24;
    public static final int CHANGEPASSWORD_FAILED= 25;
    public static final int CHANGEUSERDETAILS_SUCCESS= 26;
    public static final int CHANGEUSERDETAILS_FAILED= 27;
    public static final int SIMPLESEARCH_SEARCH= 28;
    public static final int SIMPLESEARCH_FAILED= 29;
    public static final int SIMPLESEARCHAPI_SUCCESS= 30;
    public static final int SIMPLESEARCHAPI_FAILED= 31;
    public static final int SPADETAIL_SUCCESS= 32;
    public static final int SPADETAIL_FAILED= 33;
    public static final int GETTREATMENTS_SUCCESS= 34;
    public static final int GETTREATMENTS_FAILED= 35;
    public static final int GETTREATMENTDETAIL_SUCCESS= 36;
    public static final int GETTREATMENTDETAIL_FAILED= 37;
    public static final int GETPOPULARLIST_SUCCESS= 38;
    public static final int GETPOPULARLIST_FAILED= 39;
    public static final int GETNEARBYLIST_SUCCESS= 40;
    public static final int GETNEARBYLIST_FAILED= 41;
    public static final int ADDTOBASKET_SUCCESS= 42;
    public static final int ADDTOBASKET_FAILED= 43;




    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/login")
    Call<UserModelLoginResponse> loginUser(@Body BeanLogin user);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/register")
    Call<SignUpResponseModel> signUpUser(@Body BeanModelSignUp user);


    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("/Cliknfixx/api/forgetpassword")
    Call<ForgotPasswordResponseModel> forgotPass(
            @Field("email") String email
    );

    @POST("/Cliknfixx/api/sendotp")
    Call<MobileNoResponseModel> sendOTP(
            @Query("phone") String phone
    );

    @POST("/Cliknfixx/api/fillotp")
    Call<OTPResponseModel> fillOTP(
            @Query("phone") String phone,
            @Query("otp") String otp
    );

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/services")
    Call<CategoriesListResponseModel> getCategoriesList(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/profile")
    Call<UserProfileResponseModel> getUserProfile(@Header("token") int id, @Body String phone);

    /*
    @Headers({"Accept: application/json"})
    @POST("/api/varify_otp")
    Call<OtpVerifyResponseModel> verifyOtp(@Body HashMap verify);

    @Headers({"Accept: application/json"})
    @GET("/api/types")
    Call<FilterTypeResponseModel> getTypesOfFilter(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @GET("/api/services")
    Call<FilterServicesResponseModel> getServicesOfFilter(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @GET("/api/facilities")
    Call<FilterFacilitiesResponseModel> getFacilitiesOfFilter(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @GET("/api/searchBusiness")
    Call<SearchResponseModel> getSearchResult(@Query("X") HashMap hashMap);

    @Headers({"Accept:application/json"})
    @Multipart
    @POST("/api/uploadProfileImage")
    Call<UploadImageResponseModel> postImage(@Header("Authorization") String token, @Part("image") RequestBody fileName, @Part MultipartBody.Part image);

    @Headers({"Accept: application/json"})
    @GET("/api/userProfile")
    Call<GetProfileResponseModel> getUserProfile(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @POST("/api/changePassword")
    Call<ChangePasswordResponseModel> changeUserPassword(@Header("Authorization") String token, @Body HashMap hashMap);

    @Headers({"Accept: application/json"})
    @POST("/api/updateProfile")
    Call<EditProfileResponseModel> changeUserDetails(@Header("Authorization") String token, @Body HashMap hashMap);

    @Headers({"Accept: application/json"})
    @POST("/api/changePhonenumber")
    Call<EditMobileNumberResponseModel> changeMobileNumber(@Header("Authorization") String token, @Body HashMap hashMap);

    @Headers({"Accept: application/json"})
    @GET("/api/searchBusiness")
    Call<SearchResponseModel> getSimpleSearchResult(@Query("X") HashMap hashMap);

    @Headers({"Accept: application/json"})
    @GET("/api/searchBusiness")
    Call<SearchResponseModel> getSimpleSearchResultWithApi(
            @Query("name") String name
    );

    @Headers({"Accept: application/json"})
    @GET("/api/businessDetail")
    Call<SingleSpaDetailResponseModel> getSpaDetail(
            @Query("id") int id
    );

    @Headers({"Accept: application/json"})
    @GET("/api/businessTreatment")
    Call<TreatmentsResponseModel> getTreatments(
            @Query("id") int id
    );

    @Headers({"Accept: application/json"})
    @GET("/api/treatmentDetail")
    Call<TreatmentDetailsResponseModel> getTreatmentDetail(
            @Query("id") int id
    );

    @Headers({"Accept: application/json"})
    @GET("/api/PopularBusiness")
    Call<PopularListResponseModel> getPopularList(
            @Query("lat") String lat,
            @Query("lng") String lng,
            @Query("distance") String distance
    );

    @Headers({"Accept: application/json"})
    @GET("/api/RecentBusiness")
    Call<NearByListResponseModel> getNearByList(
            @Query("lat") String lat,
            @Query("lng") String lng,
            @Query("distance") String distance
    );

    @Headers({"Accept: application/json"})
    @POST("/api/addBasket")
    Call<AddToBasketResponseModel> addToBasket(@Header("Authorization") String token, @Body HashMap hashMap
    ); */

}
