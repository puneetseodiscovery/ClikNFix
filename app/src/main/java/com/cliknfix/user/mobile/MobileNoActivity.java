package com.cliknfix.user.mobile;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.login.LoginActivity;
import com.cliknfix.user.otp.OtpActivity;
import com.cliknfix.user.responseModels.MobileNoResponseModel;
import com.cliknfix.user.responseModels.SignUpResponseModel;
import com.cliknfix.user.util.Utility;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileNoActivity extends BaseClass implements IMobileNoActivity
{
    @BindView(R.id.tv_mobile_screen_text)
    TextView tvP1;
    @BindView(R.id.tv_country_code)
    TextView tvCC;
    @BindView(R.id.et_mobile_no)
    EditText etMobile;

    IPMobileActivity ipMobileActivity;
    ProgressDialog progressDialog;
    String phone;
    String mobileNo,user_id,name,email;
    String socialMediaLogin;
    public static final int REQUEST_CODE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_no);
        ButterKnife.bind(this);
        ipMobileActivity = new PMobileActivity(this);

        if (checkForPermission()) {
            Log.e("permission","init working");
            init();
        }
    }

    public boolean checkForPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {

            Log.e("permission","request");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                                Manifest.permission.SEND_SMS,
                                Manifest.permission.RECEIVE_SMS,
                                Manifest.permission.READ_SMS},
                        REQUEST_CODE);
            }
            return false;
        } else {
            Log.e("permission","request granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        /*if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                                Manifest.permission.SEND_SMS,
                                Manifest.permission.RECEIVE_SMS,
                                Manifest.permission.READ_SMS},
                        REQUEST_CODE);
            }
        }*/
        switch (requestCode) {
            case REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("permission","granted");
                    init();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale( Manifest.permission.READ_SMS)
                                && shouldShowRequestPermissionRationale( Manifest.permission.SEND_SMS)
                                && shouldShowRequestPermissionRationale( Manifest.permission.RECEIVE_SMS)) {
                            //true if the user has previously denied the request
                            Log.e("permission","true if the user has previously denied the request");
                            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{
                                                Manifest.permission.SEND_SMS,
                                                Manifest.permission.RECEIVE_SMS,
                                                Manifest.permission.READ_SMS},
                                        REQUEST_CODE);
                            }*/
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                            alertDialogBuilder.setMessage("Permission Denied")
                                    .setCancelable(false)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                        requestPermissions(new String[]{
                                                                        Manifest.permission.SEND_SMS,
                                                                        Manifest.permission.RECEIVE_SMS,
                                                                        Manifest.permission.READ_SMS},
                                                                REQUEST_CODE);
                                                    }
                                                }
                                            });

                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();
                        } else {
                            Log.e("permission","false if a user has denied a permission and selected the Don't ask again option");
                            //false if a user has denied a permission and selected the Don't ask again option
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                            alertDialogBuilder.setMessage("Permission Denied")
                                    .setCancelable(false)
                                    .setPositiveButton("APP SETTINGS",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent viewIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    startActivity(viewIntent);
                                                    dialog.cancel();
                                                }
                                            });

                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();
                        }
                    }
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public void init() {
        tvP1.setTypeface(Utility.typeFaceForText(this));
        //user_id = "85";
        //etMobile.setText("9463924817");
        //socialMediaLogin = "0";
        socialMediaLogin = getIntent().getStringExtra("socialMedia");
        if(socialMediaLogin.equalsIgnoreCase("1"))
        {
            user_id = getIntent().getStringExtra("userId");
            email = getIntent().getStringExtra("email");
            name = getIntent().getStringExtra("name");
            Log.e("Mobile user_id", "" + user_id);
            Toast.makeText(this, "userId:" + user_id, Toast.LENGTH_SHORT).show();
            //etMobile.setEnabled(true);
            etMobile.setFocusableInTouchMode(true);
            etMobile.setFocusableInTouchMode(true);
        } else {
            //etMobile.setEnabled(false);
            etMobile.clearFocus();
            etMobile.setFocusableInTouchMode(false);
            mobileNo = getIntent().getStringExtra("phone");
            user_id = getIntent().getStringExtra("userId");
            Log.e("Mobile phone", "" + mobileNo);
            Log.e("Mobile user_id", "" + user_id);
            etMobile.setText(mobileNo);
        }


    }

    public void onNextClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if(etMobile.getText().toString().length()>0) {
                progressDialog = Utility.showLoader(this);
                phone = tvCC.getText() + " " + etMobile.getText().toString().trim().toLowerCase();
                ipMobileActivity.doSignUp(name, email, "", "", "",phone, phone);
            } else {
                etMobile.setError("Enter a valid email.");
                etMobile.requestFocus();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void onSendOTPSuccessFromPresenter(MobileNoResponseModel mobileNoResponseModel) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + mobileNoResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MobileNoActivity.this, OtpActivity.class);
        intent.putExtra("socialMedia",socialMediaLogin);
        intent.putExtra("phone", phone);
        intent.putExtra("userId", user_id);
        startActivity(intent);
    }

    public void onSendOTPFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpResponseSuccessFromPresenter(SignUpResponseModel signUpResponseModel) {
        ipMobileActivity.sendOTP(phone, user_id, "0");
    }

    @Override
    public void onSignUpResponseFailureFromPresenter(String msgg) {
        Toast.makeText(this, "" + msgg, Toast.LENGTH_SHORT).show();
    }
}
