package com.cliknfix.user.submitProblem;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.search.SearchActivity;
import com.cliknfix.user.util.Utility;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LOCATION_SERVICE;
import static com.google.android.gms.location.LocationServices.API;
import static com.google.android.gms.location.LocationServices.FusedLocationApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitProblemFragment extends Fragment implements View.OnClickListener
        , GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener
        , LocationListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    RelativeLayout ivBack;
    @BindView(R.id.et_send_msg)
    EditText etSendMsg;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    Context context;

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;

    private static final int LOCATION_PERMISSIONS_REQUEST = 1;

    public SubmitProblemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submit_problem, container, false);
        ButterKnife.bind(this, view);
        this.context = getActivity();
        init();
        return view;
    }

    public void init() {
        ivBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvTitle.setText(getArguments().getString("category"));
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        btnSubmit.setTypeface(Utility.typeFaceForBoldText(getContext()));

        mGoogleApiClient = new GoogleApiClient.Builder((HomeScreenActivity)context)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        if(Utility.isNetworkConnected(context))
            enableGPS();
        else
            Toast.makeText(context, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();
    }

    private void enableGPS() {
        // Check GPS is enabled
        LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "Please enable location services", Toast.LENGTH_SHORT).show();
        } else {
            checkPermissions();
        }
    }


    private void checkPermissions() {
        // Check location permission is granted - if it is, start the service, otherwise request the permission
        int permission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int permission1 = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED && permission1 == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(context, "Lat:" + currentLatitude + "Lng:" + currentLatitude, Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions((HomeScreenActivity)context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.iv_back:
                ((HomeScreenActivity) getActivity()).getSupportFragmentManager().popBackStack(null, ((HomeScreenActivity) getActivity()).getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                break;
            case R.id.btn_submit:
               /* Log.e("current lat","" + currentLatitude);
                Log.e("current lng","" + currentLongitude);
                if(Utility.isNetworkConnected(context)){
                    if(etSendMsg.getText().toString().length()>0) {
                        Log.e("current lat","" + currentLatitude);
                        Log.e("current lng","" + currentLongitude);
                        Intent intent = new Intent(context, SearchActivity.class);
                        intent.putExtra("category", getArguments().getString("category"));
                        intent.putExtra("categoryId", getArguments().getInt("categoryId"));
                        intent.putExtra("userQuery", etSendMsg.getText().toString());
                        intent.putExtra("currentLatitude", currentLatitude);
                        intent.putExtra("currentLongitude", currentLongitude);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "Please mention your problem.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
                }*/
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //Now lets connect to the API
        mGoogleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("onLocationChanged","working");
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e("onConnected","working");
        int permission = ContextCompat.checkSelfPermission(((HomeScreenActivity)context),
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            Location location = FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (location == null) {
                FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

            } else {
                //If everything went fine lets get latitude and longitude
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();
                Log.e("Lat","" + currentLatitude);
                Log.e("Lon","" + currentLongitude);
                Toast.makeText(((HomeScreenActivity)context), currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(((HomeScreenActivity)context), CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }
}
