package com.cliknfix.user.search;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.responseModels.SearchTechResponseModel;
import com.cliknfix.user.technicianDetail.TechnicianDetailActivity;
import com.cliknfix.user.util.Utility;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.gms.location.LocationServices.*;

public class SearchActivity extends BaseClass implements ISearchActivity
        {

    @BindView(R.id.ll_circular_progress)
    LinearLayout linearLayout;
    CircularDotsLoader progressDialog;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_send_msg_text)
    TextView tvSendMsgText;
    int categoryId;
    String userQuery;
    double currentLatitude,currentLongitude;

    IPSearchActivity ipSearchActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        ipSearchActivity = new PSearchActivity(this);
        init();
    }

    private void init() {
        linearLayout.addView(Utility.circularLoader(this));
        TextView tvLoading = new TextView(this);
        tvLoading.setText("Please wait");
        tvLoading.setTextSize(15);
        tvLoading.setTextColor(getResources().getColor(R.color.blackColor));
        tvLoading.setGravity(Gravity.CENTER);
        tvLoading.setTypeface(Utility.typeFaceForText(this));
        linearLayout.addView(tvLoading);
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvSendMsgText.setTypeface(Utility.typeFaceForText(this));

        categoryId = getIntent().getIntExtra("categoryId", 0);
        userQuery = getIntent().getStringExtra("userQuery");
        currentLatitude = getIntent().getDoubleExtra("currentLatitude",0.0);
        currentLongitude = getIntent().getDoubleExtra("currentLongitude",0.0);
        Log.e("current lat","" + currentLatitude);
        Log.e("current lng","" + currentLongitude);
        ipSearchActivity.searchTech(categoryId,userQuery,currentLatitude,currentLongitude,Utility.getToken());
    }



    /*public void startNewActivity(){
        startActivity(new Intent(this, TechnicianDetailActivity.class));
    }*/

    @Override
    public void searchTechSuccessResponseFromPresenter(SearchTechResponseModel searchTechResponseModel) {
        Log.e("data","" + searchTechResponseModel);
        if(searchTechResponseModel.getMessage().equalsIgnoreCase("Searching......")){

        } else {
            /* Intent intent = new Intent(SearchActivity.this,TechnicianDetailActivity.class);
        intent.putExtra("name", searchTechResponseModel.getData().get(0).getName());
        startActivity(intent);*/
        }
    }

    @Override
    public void noTechFoundResponseFromPresenter(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SearchActivity.this,NoTechFoundActivity.class);
        intent.putExtra("message", message);
        startActivity(intent);
        //startActivity(new Intent(this, NoTechFoundActivity.class));
    }

    @Override
    public void searchTechFailureResponseFromPresenter(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeScreenActivity.class));
    }
}
