package com.cliknfix.user.signUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.login.LoginActivity;
import com.cliknfix.user.mobile.MobileNoActivity;
import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.responseModels.SocialLoginResponseModel;
import com.cliknfix.user.util.Utility;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseClass implements ISignUpActivity {

    String[] country = {
            "Select Blood Group",
            "A+",
            "AB+",
            "O+",
            "B+",
            "A-",
            "AB-",
            "O-",
            "B-"};

    @BindView(R.id.tv_signup)
    TextView tvSignUp;
    @BindView(R.id.et_signup_username)
    EditText etUsername;
    @BindView(R.id.et_signup_email)
    EditText etEmail;
    @BindView(R.id.et_age_signup)
    EditText etAge;
    @BindView(R.id.sp_bld_grp_signup)
    Spinner spBldGrp;
    @BindView(R.id.et_address_signup)
    EditText etAddress;
    @BindView(R.id.et_phone_signup)
    EditText etPhone;
    @BindView(R.id.et_password_signup)
    EditText etPassword;
    @BindView(R.id.et_cnfrm_password_signup)
    EditText etCnfrmPass;
    @BindView(R.id.cb_terms_signup)
    CheckBox cbTerms;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.tv_or)
    TextView tvOr;
    @BindView(R.id.iv_fb)
    ImageView ivFB;
    @BindView(R.id.iv_gplus)
    ImageView ivGP;
    @BindView(R.id.iv_linked)
    ImageView ivLinkedIn;
    @BindView(R.id.btn_login)
    Button btnLogin;

    IPSignUp ipSignUp;

    ProgressDialog progressDialog;

    CallbackManager callbackManager;
    //GoogleSignInClient mGoogleSignInClient;
    int RC_GP_SIGNIN=1;
    private static final String EMAIL = "email";
    boolean socialLogin;
    String email,username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        ipSignUp = new PSignUp(this);
        init();
        //printHashKey();
        beforeFacebookClick();
        beforeGoogleSignIn();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        Toast.makeText(this, ""+isLoggedIn, Toast.LENGTH_SHORT).show();
        Log.e("device Token signup","" + deviceToken);
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        /*GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Toast.makeText(this, "account:"+account.getDisplayName(), Toast.LENGTH_SHORT).show();*/
    }

    private void registerUserToFirebase(final String userId, final String password) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://cliknfix-1558498832364.firebaseio.com/users.json";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                Firebase reference = new Firebase("https://cliknfix-1558498832364.firebaseio.com/users");

                if(s.equals("null")) {
                    reference.child(userId).child("password").setValue(password);
                    Toast.makeText(SignUpActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        JSONObject obj = new JSONObject(s);

                        if (!obj.has(userId)) {
                            reference.child(userId).child("password").setValue(password);
                            Log.e("firebase","registration successful");
                            //Toast.makeText(SignUpActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                        } else {
                            Log.e("firebase","username already exists");
                            //Toast.makeText(SignUpActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                pd.dismiss();
            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError );
                pd.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(SignUpActivity.this);
        rQueue.add(request);
    }

    private void beforeFacebookClick() {
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    public void onSignUpButtonClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if (etUsername.getText().toString().length()>0 && etEmail.getText().toString().length()>0 && etAge.getText().toString().length()>0
                    && etAddress.getText().toString().length()>0 && spBldGrp.getSelectedItem() != "Select Blood Group..." && etPhone.getText().toString().length()>0
                    && etPassword.getText().toString().length()>0 && etCnfrmPass.getText().toString().length()>0) {
                if (Utility.validEmail(etEmail.getText().toString().trim())) {
                    if(Utility.validMobile(etPhone.getText().toString().trim())) {
                        if (etPassword.getText().toString().equals(etCnfrmPass.getText().toString())) {
                            if(new Integer(etAge.getText().toString()).intValue() <=150){
                                if (etPassword.getText().toString().length()>=6) {
                                    if (Utility.isValidPassword(etPassword.getText().toString().trim())) {
                                        if(cbTerms.isChecked()) {
                                            progressDialog = Utility.showLoader(this);
                                            ipSignUp.doSignUp(etUsername.getText().toString().trim(), etEmail.getText().toString().trim().toLowerCase(), etAge.getText().toString().trim(), spBldGrp.getSelectedItem().toString(), etAddress.getText().toString().trim(),etPhone.getText().toString().trim(), etPassword.getText().toString().trim());
                                        } else
                                            Toast.makeText(this, "Please accept the Terms and Conditions.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        etPassword.setError("Alphanumeric password needed.Length should be (6-13) ith no spaces");
                                        etPassword.requestFocus();
                                    }
                                } else {
                                    etPassword.setError("Minimum 6 characters.");
                                    etPassword.requestFocus();
                                }
                            } else {
                                etAge.setError("Maximum age limit should be 150.");
                                etAge.requestFocus();
                            }
                        } else {
                            etCnfrmPass.setError("Password not matched.");
                            etCnfrmPass.requestFocus();
                        }
                    } else {
                        etPhone.setError("Enter an  valid phone.");
                        etPhone.requestFocus();
                    }
                } else {
                    etEmail.setError("Enter an  valid email.");
                    etEmail.requestFocus();
                }
            } else {
                if (etUsername.getText().toString().length()==0 && etEmail.getText().toString().length()==0 && etAge.getText().toString().length()==0
                        &&  etAddress.getText().toString().length()==0 && spBldGrp.getSelectedItem() == "Select Blood Group..." && etPhone.getText().toString().length()==0
                        && etPassword.getText().toString().length()==0 && etCnfrmPass.getText().toString().length()==0)
                {
                    etUsername.setError("Enter username.");
                    etEmail.setError("Enter email.");
                    etAge.setError("Enter age.");
                    etAddress.setError("Enter address.");
                    validationForSpinner();
                    etPhone.setError("Enter phone");
                    etPassword.setError("Enter password");
                    etCnfrmPass.setError("Enter confirm password");
                    etUsername.requestFocus();
                } else {
                    if (etCnfrmPass.getText().toString().length()==0) {
                        etCnfrmPass.setError("Enter confirm password");
                        etCnfrmPass.requestFocus();
                    }
                    if (etPassword.getText().toString().length()==0) {
                        etPassword.setError("Enter password");
                        etPassword.requestFocus();
                    }
                    if (etPhone.getText().toString().length()==0) {
                        etPhone.setError("Enter phone");
                        etPhone.requestFocus();
                    } else {
                        if (!Utility.validMobile(etPhone.getText().toString().trim())) {
                            etEmail.setError("Invalid email");
                            etEmail.requestFocus();
                        }
                    }
                    if (etAddress.getText().toString().length()==0) {
                        etAddress.setError("Enter address");
                        etAddress.requestFocus();
                    }
                    if (spBldGrp.getSelectedItem() == "Select Blood Group...") {
                        validationForSpinner();
                    }
                    if (etAge.getText().toString().length()==0) {
                        etAge.setError("Enter age");
                        etAge.requestFocus();
                    }
                    if (etEmail.getText().toString().length()==0) {
                        etEmail.setError("Enter email");
                        etEmail.requestFocus();
                    } else {
                        if (!Utility.validEmail(etEmail.getText().toString().trim())) {
                            etEmail.setError("Invalid email");
                            etEmail.requestFocus();
                        }
                    }
                    if(etUsername.getText().toString().length()==0){
                        etUsername.setError("Enter username");
                        etUsername.requestFocus();
                    }
                }
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void onFacebookClicked(View view) {
        if(Utility.isNetworkConnected(this)) {
            LoginManager.getInstance().logInWithReadPermissions(SignUpActivity.this, Arrays.asList("public_profile","email"));
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // App code
                    Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                            Log.v("Main", response.toString());
                            try {
                                //registerUserToFirebase();
                                email= jsonObject.getString("email");
                                username= jsonObject.getString("name");
                                Log.e("obj","" + jsonObject);
                                Log.e("email","" + jsonObject.getString("email"));
                                Log.e("name","" +jsonObject.getString("name"));
                                progressDialog = Utility.showLoader(SignUpActivity.this);
                                ipSignUp.doLogin(email,username,deviceToken);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "name,email");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                @Override
                public void onCancel() {
                    // App code
                    Toast.makeText(SignUpActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException exception) {
                    // App code
                    Toast.makeText(SignUpActivity.this, "Error:" + exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
            Toast.makeText(this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();

    }

    public void onGooglePlusClicked(View view) {
        if(Utility.isNetworkConnected(this)) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_GP_SIGNIN);
        } else
            Toast.makeText(this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();
    }

    public void onLinkedInClicked(View view) {
        LoginManager.getInstance().logOut();
    }

//    private static Scope buildScope() {
//        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_GP_SIGNIN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);
        }
        //LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            //registerUserToFirebase();
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            email= account.getEmail();
            username= account.getDisplayName();
            Log.e("google signin","" + account);
            Log.e("name","" + account.getDisplayName());
            Log.e("email","" + account.getEmail());
            progressDialog = Utility.showLoader(SignUpActivity.this);
            ipSignUp.doLogin(email,username,deviceToken);
            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }



    public void onBackLayoutClicked(View view) {
        onBackPressed();
    }

    public void init() {
        tvSignUp.setTypeface(Utility.typeFaceForBoldText(this));
        btnSignup.setTypeface(Utility.typeFaceForBoldText(this));
        tvOr.setTypeface(Utility.typeFaceForText(this));
        btnLogin.setTypeface(Utility.typeFaceForBoldText(this));
        loadSpinner();
    }

    public void loadSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBldGrp.setAdapter(aa);
        spBldGrp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void validationForSpinner() {
        TextView errorText = (TextView)spBldGrp.getSelectedView();
        errorText.setError("Select Value");
        errorText.setTextColor(getResources().getColor(R.color.edittextHintColor));//just to highlight that this is an error
        errorText.setText("Select Blood Group");//changes the selected item text to this
    }


    public static boolean isValidPassword(final String password) {
        String pattern= "^[a-zA-Z0-9]@*$";
        return password.matches(pattern);

    }

    public void onLoginButtonClicked(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void printHashKey() {
        Log.e("printHashKey", "working");
        try {
            PackageInfo info =     getPackageManager().getPackageInfo("com.cliknfix.user",     PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                 String sign= Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("MY KEY HASH:", sign);
                Log.d("++++++","++ hash key ++"+sign);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("error1:", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.e("error2:", e.getMessage());
        }
    }

    private void beforeGoogleSignIn(){
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(SignUpActivity.this, gso);*/
    }

    @Override
    public void onSignUpResponseSuccessFromPresenter(SignUpResponseModel signUpResponseModel) {
        String userId = signUpResponseModel.getData().get(0).getId().toString().trim();
        String password = signUpResponseModel.getData().get(0).getPassword().toString().trim();
        registerUserToFirebase(userId,password);
        progressDialog.dismiss();
        Toast.makeText(this, "User Successfully Registered.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUpActivity.this, MobileNoActivity.class);
        intent.putExtra("socialMedia","0");
        intent.putExtra("phone", "" +signUpResponseModel.getData().get(0).getPhone().toString());
        intent.putExtra("userId", "" +signUpResponseModel.getData().get(0).getId().toString().trim());
        startActivity(intent);
    }

    @Override
    public void onSignUpResponseFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccessFromPresenter(SocialLoginResponseModel socialLoginResponseModel) {
        progressDialog.dismiss();
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    @Override
    public void onLoginFailedFromPresenter(String msgg) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + msgg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void otpNotVerifiedFromPresenter(SocialLoginResponseModel model) {
        progressDialog.dismiss();
        Log.e("model:","" + model.getData().get(0).getEmailVerifiedAt());
        Toast.makeText(this, "model:"+model, Toast.LENGTH_SHORT).show();
        if(model.getData().get(0).getEmailVerifiedAt() == null || model.getData().get(0).getEmailVerifiedAt().equals("")){
            Intent intent = new Intent(SignUpActivity.this, MobileNoActivity.class);
            intent.putExtra("socialMedia","1");
            intent.putExtra("phone", "" + model.getData().get(0).getPhone());
            intent.putExtra("userId", "" + model.getData().get(0).getId().toString());
            startActivity(intent);
        } else if(model.getData().get(0).getEmailVerifiedAt().equals("1")) {
            Intent intent = new Intent(SignUpActivity.this, MobileNoActivity.class);
            intent.putExtra("socialMedia","1");
            intent.putExtra("userId",model.getData().get(0).getId().toString());
            startActivity(intent);
        }
    }
}
