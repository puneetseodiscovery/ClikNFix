package com.cliknfix.user.retrofit;


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
import com.cliknfix.user.responseModels.PrivacyPolicyResponseModel;
import com.cliknfix.user.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.user.responseModels.SearchTechResponseModel;
import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.responseModels.LoginResponseModel;
import com.cliknfix.user.responseModels.SubmitTechReviewResponseModel;
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
    public static final int LOGOUT_SUCCESS = 7;
    public static final int LOGOUT_FAILED = 9;
    public static final int GETCATEGORYLIST_SUCCESS= 10;
    public static final int GETCATEGORYLIST_FAILED= 11;
    public static final int SEND_OTP_SUCCESS= 12;
    public static final int SEND_OTP_FAILED= 13;
    public static final int USER_PROFILE_SUCCESS= 14;
    public static final int USER_PROFILE_FAILED= 15;
    public static final int FILL_OTP_SUCCESS= 16;
    public static final int FILL_OTP_FAILURE= 17;
    public static final int OTP_NOT_VERIFIED= 18;
    public static final int CONTACT_US_SUCCESS= 19;
    public static final int CONTACT_US_FAILURE= 20;
    public static final int PRIVACY_POLICY_SUCCESSS= 21;
    public static final int PRIVACY_POLICY_FAILURE= 22;
    public static final int CHANGE_PASSWORD_SUCCESS= 23;
    public static final int CHANGE_PASSWORD_FAILED= 24;
    public static final int SAVE_USER_PROFILE_SUCCESS= 25;
    public static final int SAVE_USER_PROFILE_FAILURE= 26;
    public static final int SUBMIT_TECH_REVIEW_SUCCESS= 27;
    public static final int SUBMIT_TECH_REVIEW_FAILURE= 28;
    public static final int ABOUT_US_SUCCESS= 29;
    public static final int ABOUT_US_FAILURE= 30;
    public static final int SEARCH_TECH_SUCCESS= 31;
    public static final int NO_TECH_AVAILABLE= 32;
    public static final int SEARCH_TECH_FAILURE= 33;
    public static final int PAST_JOBS_SUCCESS= 34;
    public static final int PAST_JOBS_NO_DATA= 35;
    public static final int PAST_JOBS_FAILED= 36;
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
    Call<LoginResponseModel> loginUser(
            @Query("email") String email,
            @Query("password") String password,
            @Query("device_token") String device_token
    );

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
            @Query("phone") String phone,
            @Query("user_id") String user_id,
            @Query("resend_otp") String resend_otp
    );

    @POST("/Cliknfixx/api/fillotp")
    Call<OTPResponseModel> fillOTP(
            @Query("phone") String phone,
            @Query("otp") String otp,
            @Query("user_id") String user_id
    );

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/services")
    Call<CategoriesListResponseModel> getCategoriesList(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/profile")
    Call<UserProfileResponseModel> getUserProfile(@Header("token") String token, @Query("user_id") int user_id);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/logout")
    Call<LogoutResponseModel> doLogout(@Query("user_id") int user_id);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/contactUs")
    Call<ContactUsResponseModel> contactUS(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/privacyPolicy")
    Call<PrivacyPolicyResponseModel> privacyPolicy(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/changePassword")
    Call<ChangePasswordResponseModel> changePassword(
            @Query("current_password") String current_password,
            @Query("your_password") String your_password,
            @Query("confirm_password") String confirm_password,
            @Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/editProfile")
    Call<SaveUserProfileResponseModel> saveUserProfile(
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("blood_group") String blood_group,
            @Query("age") String age,
            @Query("address") String address,
            @Query("imgUrl") String imgUrl,
            @Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/addTechnicianReview")
    Call<SubmitTechReviewResponseModel> submitTechReview(
            @Query("rating") String rating,
            @Query("review") String review,
            @Query("technician_id") int technician_id,
            @Header("token") String token);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/aboutUs")
    Call<AboutUsResponseModel> aboutUs(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/sendQuery")
    Call<SearchTechResponseModel> searchTech(
            @Header("token") String token,
            @Query("category_id") int category_id,
            @Query("user_query") String user_query,
            @Query("lat") double lat,
            @Query("lng") double lng);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/checkPastjobs")
    Call<PastJobsResponseModel> getPastJobsList(@Header("token") String token);

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
