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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
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
import com.cliknfix.user.chat.ChatActivity;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.paymentMethods.PaymentMethodsActivity;
import com.cliknfix.user.trackLocation.TechnicianLocationActivity;
import com.cliknfix.user.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TechnicianDetailActivity extends BaseClass {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_technician_name)
    TextView tvTechName;
    @BindView(R.id.rating)
    RatingBar ratingBar;
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
    String message,userId,techId,otp,name,rating,labour;

    int totalUsers = 0;
    ArrayList<String> al = new ArrayList<>();

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    String techPhone;

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

        /*if(getIntent().getExtras()!=null) {
            Log.e("message","" + getIntent().getStringExtra("message"));
            Log.e("technician_id","" + getIntent().getStringExtra("technician_id"));
            Log.e("user_id","" + getIntent().getStringExtra("user_id"));
            Log.e("labour_rate","" + getIntent().getStringExtra("labour_rate"));
            message = getIntent().getStringExtra("message");
            technicianId = getIntent().getStringExtra("technician_id");
            userId = getIntent().getStringExtra("user_id");
            labourRate = getIntent().getStringExtra("labour_rate");
            Log.e("Homescreen userId","" + userId);
        }*/
        if(getIntent().getExtras()!=null) {
            name = getIntent().getStringExtra("technician_name");
            rating = getIntent().getStringExtra("rating");
            labour = getIntent().getStringExtra("labour_rate");
            tvTechName.setText(name);
            ratingBar.setNumStars(Integer.parseInt(rating));
            tvRate.setText(labour);
            message = getIntent().getStringExtra("message");
            otp = getOTP(message);
            userId = getIntent().getStringExtra("user_id");
            techId = getIntent().getStringExtra("technician_id");
            techPhone = getIntent().getStringExtra("technician_phone");
            Log.e("data:", "name:" + name + ",rating:" + rating + ",labour:" + labour + ",otp:" + otp + ",userId:" + userId + "techId:" + techId + "phone:" + techPhone);
            tvOTP.setText(otp);

            tvTechName.setText(getIntent().getStringExtra("name"));
        }
        //phone = "7018835041";
    }

    private String getOTP(String message) {
        Log.e("fillOTP","phone:");
        Pattern pattern = Pattern.compile("(\\d{4})");
        Matcher matcher = pattern.matcher(message);
        String val ="";
        if (matcher.find()) {
            val = matcher.group(0);  // 4 digit number
        }
        Log.e("OTP:",val);
        return  val;
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
        Intent intent = new Intent(TechnicianDetailActivity.this,PaymentMethodsActivity.class);
        intent.putExtra("techId",techId);
        intent.putExtra("techName",name);
        intent.putExtra("phone",techPhone);
        intent.putExtra("amount",labour);
        startActivity(intent);
        //startActivity(new Intent(this, PaymentMethodsActivity.class));
    }

    public void onPhoneClicked(View view) {
        String mobileNo = techPhone;
        String uri = "tel:" + mobileNo.trim();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void onMsgIconClicked(View view) {
        String url = "https://cliknfix-1558498832364.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                Log.e("chat response","" + s);
                doOnSuccess(s);
                firebaseChatWith = techId;//String.valueOf(getArguments().getInt("id"));
                startActivity(new Intent(TechnicianDetailActivity.this, ChatActivity.class).putExtra("technicianName",name));
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(TechnicianDetailActivity.this);
        rQueue.add(request);

        firebaseChatWith = techId;
        startActivity(new Intent(TechnicianDetailActivity.this, ChatActivity.class));
    }

    public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while(i.hasNext()){
                key = i.next().toString();

                if(!key.equals(firebaseUsername)) {
                    al.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onTrackClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if (checkLocationPermission()) {
                Intent intent = new Intent(this, TechnicianLocationActivity.class);
                intent.putExtra("techId",techId);
                intent.putExtra("techName",name);
                startActivity(intent);
                //startActivity(new Intent(this,TechnicianLocationActivity.class));
            }
            else {
                ActivityCompat.requestPermissions(TechnicianDetailActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkLocationPermission() {
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
