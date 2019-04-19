package com.cliknfix.signUp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.R;
import com.cliknfix.base.BaseClass;
import com.cliknfix.login.LoginActivity;
import com.cliknfix.retrofit.APIInterface;
import com.cliknfix.util.Utility;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseClass implements ISignUpActivity {

    @BindView(R.id.tv_signup)
    TextView tvSignUp;
    @BindView(R.id.et_signup_username)
    EditText etUsername;
    @BindView(R.id.et_signup_email)
    EditText etEmail;
    @BindView(R.id.et_age_signup)
    EditText etAge;
    @BindView(R.id.et_bld_grp_signup)
    EditText etBldGrp;
    @BindView(R.id.et_address_signup)
    EditText etAddress;
    @BindView(R.id.et_password_signup)
    EditText etPassword;
    @BindView(R.id.et_cnfrm_password_signup)
    EditText etCnfrmPass;
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
    GoogleSignInClient mGoogleSignInClient;
    int RC_GP_SIGNIN=1;
    private static final String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeFacebookClick();
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        ipSignUp = new PSignUp(this);
        init();
        beforeGoogleSignIn();
        printHashKey();
    }

    private void beforeFacebookClick() {
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(SignUpActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSignUpButtonClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if (etUsername.getText().toString().length()>0 && etEmail.getText().toString().length()>0 && etAge.getText().toString().length()>0
                    && etBldGrp.getText().toString().length()>0 && etAddress.getText().toString().length()>0 && etPassword.getText().toString().length()>0) {
                if (Utility.validEmail(etEmail.getText().toString().trim())) {
                    if (etPassword.getText().toString().equals(etCnfrmPass.getText().toString())) {
                        if (etPassword.getText().toString().length()>=6) {
                            if (isValidPassword(etPassword.getText().toString().trim())) {
                                progressDialog = Utility.showLoader(this);
                                ipSignUp.doSignUp(etUsername.getText().toString().trim(), etEmail.getText().toString().trim().toLowerCase(), etAge.getText().toString().trim(), etBldGrp.getText().toString().trim(), etAddress.getText().toString().trim(), etPassword.getText().toString().trim());
                            } else {
                                etPassword.setError("Alphanumeric password needed.");
                                etPassword.requestFocus();
                            }
                        } else {
                            etPassword.setError("Minimum 6 characters.");
                            etPassword.requestFocus();
                        }
                    } else {
                        etCnfrmPass.setError("Password not matched.");
                        etCnfrmPass.requestFocus();
                    }
                } else {
                    etEmail.setError("Enter an  valid email.");
                    etEmail.requestFocus();
                }
            } else {
                if (etUsername.getText().toString().length()==0 && etEmail.getText().toString().length()==0 && etAge.getText().toString().length()==0
                        && etBldGrp.getText().toString().length()==0 && etAddress.getText().toString().length()==0 && etPassword.getText().toString().length()==0
                        && etCnfrmPass.getText().toString().length()==0) {
                    etUsername.setError("Enter full name");
                    etUsername.requestFocus();
                    etEmail.setError("Enter email");
                    etAge.setError("Enter mobile number");
                    etBldGrp.setError("Enter password");
                    etAddress.setError("Confirm password");
                    etPassword.setError("Select gender");
                    etCnfrmPass.setError("Enter age");
                } else if (etUsername.getText().toString().length()==0) {
                    etUsername.setError("Enter Username");
                    etUsername.requestFocus();
                } else if (etEmail.getText().toString().length()==0) {
                    etEmail.setError("Enter email");
                    etEmail.requestFocus();
                } else if (etAge.getText().toString().length()==0) {
                    etAge.setError("Enter age");
                    etAge.requestFocus();
                } else if (etBldGrp.getText().toString().length()==0) {
                    etBldGrp.setError("Enter blood group");
                    etBldGrp.requestFocus();
                } else if (etAddress.getText().toString().length()==0) {
                    etAddress.setError("Enter address");
                    etAddress.requestFocus();
                } else if (etPassword.getText().toString().length()==0) {
                    etPassword.setError("Enter password");
                    etPassword.requestFocus();
                } else if (etCnfrmPass.getText().toString().length()==0) {
                    etCnfrmPass.setError("Enter confirm password");
                    etCnfrmPass.requestFocus();
                }
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void onFacebookClicked(View view) {
        if(Utility.isNetworkConnected(this))
            LoginManager.getInstance().logInWithReadPermissions(SignUpActivity.this, Arrays.asList("public_profile", "user_friends"));
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
        if(Utility.isNetworkConnected(this)) {
           /* LISessionManager.getInstance(getApplicationContext()).init(thisActivity, buildScope(), new AuthListener() {
                @Override
                public void onAuthSuccess() {
                    // Authentication was successful.  You can now do
                    // other calls with the SDK.
                }

                @Override
                public void onAuthError(LIAuthError error) {
                    // Handle authentication errors
                }
            }, true);*/
        } else
            Toast.makeText(this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();
    }

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
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }

    @Override
    public void onSignUpResponseFromPresenter(String statusValue,int otp) {
        progressDialog.dismiss();

        if (otp!= APIInterface.SIGNUP_FAILED) {

            //startActivity(new Intent(this, OtpActivity.class).putExtra("otp", otp).putExtra("mobileNumber",mobileNumber.getText().toString()));
            finish();
        }
        else
        {
                Toast.makeText(this, ""+statusValue, Toast.LENGTH_SHORT).show();
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

        //gender.setOnClickListener(this);
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus

                    if (etEmail.getText().toString().length()>0) {
                        if (!Utility.validEmail(etEmail.getText().toString().trim()))
                            etEmail.setError("Invalid email");
                        etEmail.requestFocus();
                    } else {
                        etEmail.setError(null);
                    }
                }
            }
        });
    }

    public static boolean isValidPassword(final String password) {
        String pattern= "^[a-zA-Z0-9]@*$";
        return password.matches(pattern);

    }

    public void onLoginButtonClicked(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void printHashKey() {
        try {
            PackageInfo info =     getPackageManager().getPackageInfo("com.cliknfix",     PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign= Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("MY KEY HASH:", sign);
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    private void beforeGoogleSignIn(){
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(SignUpActivity.this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(SignUpActivity.this);
    }
}
