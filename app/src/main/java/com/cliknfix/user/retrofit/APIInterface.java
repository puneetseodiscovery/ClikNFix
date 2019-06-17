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

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    public static final int SOCIAL_LOGIN_SUCCESS = 37;
    public static final int SOCIAL_LOGIN_FAILED = 38;
    public static final int SOCIAL_OTP_NOT_VERIFIED = 39;
    public static final int PAYMENT_DONE_SUCCESS= 40;
    public static final int PAYMENT_DONE_FAILED= 41;
    public static final int TECH_DETAIL_SUCCESS= 42;
    public static final int TECH_DETAIL_FAILED= 43;
    public static final int RESEND_OTP_SUCCESS= 44;
    public static final int RESEND_OTP_FAILED= 45;
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
            //@Query("user_image") String user_image,
            @Header("token") String token);

    /*@Multipart
    @POST("/Cliknfixx/api/editProfile")
    Call<SaveUserProfileResponseModel> saveUserProfile(
            @Part("name") String name,
            @Part("phone") String phone,
            @Part("blood_group") String blood_group,
            @Part("age") String age,
            @Part("address") String address,
            @Part MultipartBody.Part user_image,
            @Header("token") String token);*/

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

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/socialLogin")
    Call<SocialLoginResponseModel> doLogin(
            @Query("email") String email,
            @Query("name") String username,
            @Query("device_token") String device_token
    );

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/payment")
    Call<PaymentDoneResponseModel> paymentDone(
            @Query("total_earning") String total_earning,
            @Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/showTechnicianProfile")
    Call<TechDetailResponseModel> getTechDetail(
            @Query("technician_id") int id,
            @Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/register")
    Call<SignUpResponseModel> doSignUp(
            @Query("name") String name,
            @Query("email") String email,
            @Query("age") String age,
            @Query("bg") String bg,
            @Query("add") String add,
            @Query("phone") String phone,
            @Query("pass") String pass);


}
