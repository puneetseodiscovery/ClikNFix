package com.cliknfix.user.technicianDetail;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.chat.ChatActivity;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.paymentMethods.PaymentMethodsActivity;
import com.cliknfix.user.trackLocation.TechnicianLocationActivity;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TechnicianDetailActivity extends BaseClass {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_technician_name)
    TextView tvTechName;
    @BindView(R.id.tv_rate_text)
    TextView tvRateText;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_otp_text)
    TextView tvOTPText;
    @BindView(R.id.tv_otp)
    TextView tvOTP;
    @BindView(R.id.btn_track)
    Button btnTrack;
    @BindView(R.id.btn_payment)
    Button btnPayment;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_detail);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvTechName.setTypeface(Utility.typeFaceForBoldText(this));
        tvRateText.setTypeface(Utility.typeFaceForBoldText(this));
        tvRate.setTypeface(Utility.typeFaceForBoldText(this));
        tvOTPText.setTypeface(Utility.typeFaceForBoldText(this));
        tvOTP.setTypeface(Utility.typeFaceForBoldText(this));
        btnTrack.setTypeface(Utility.typeFaceForBoldText(this));
        btnPayment.setTypeface(Utility.typeFaceForBoldText(this));

        Log.e("Tech Name","" + getIntent().getStringExtra("name"));
        tvTechName.setText(getIntent().getStringExtra("name"));
    }

    public void onBackClicked(View view){
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("OnBackPress","Working");
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    public void onPaymentClicked(View view) {
        startActivity(new Intent(this, PaymentMethodsActivity.class));
    }

    public void onMsgIconClicked(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }

    public void onTrackClicked(View view) {
        if(checkLocationPermission())
            startActivity(new Intent(this, TechnicianLocationActivity.class));
        else {
            ActivityCompat.requestPermissions(TechnicianDetailActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }

    }

    public boolean checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(TechnicianDetailActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                }
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)){
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION);
                    if (! showRationale){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setTitle("Change Permissions in Settings");
                        alertDialogBuilder
                                .setMessage("" +
                                        "\nYou need to alow these permission to access location.")
                                .setCancelable(false)
                                .setPositiveButton("SETTINGS", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                                        intent.setData(uri);
                                        startActivityForResult(intent, 1000);     // Comment 3.
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    } else {
                        startActivity(new Intent(this, TechnicianDetailActivity.class));
                    }
                }
                return;
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            startActivity(new Intent(this, TechnicianDetailActivity.class));
        }
    }
}
