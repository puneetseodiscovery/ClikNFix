package com.cliknfix.user.submitProblem;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LOCATION_SERVICE;
import static com.google.android.gms.location.LocationServices.API;
import static com.google.android.gms.location.LocationServices.FusedLocationApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitProblemFragmentt extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    RelativeLayout ivBack;
    @BindView(R.id.et_send_msg)
    EditText etSendMsg;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    Context context;

    private double currentLatitude;
    private double currentLongitude;

    private FusedLocationProviderClient fusedLocationClient;

    private static final int LOCATION_PERMISSIONS_REQUEST = 1;

    public SubmitProblemFragmentt() {
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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        if (Utility.isNetworkConnected(context)) {
            if (checkPermissions()) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions((HomeScreenActivity)context,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                            LOCATION_PERMISSIONS_REQUEST);
                }
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener((HomeScreenActivity)context, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    // Logic to handle location object
                                    currentLatitude = location.getLatitude();
                                    currentLongitude = location.getLongitude();
                                    Toast.makeText(context, currentLatitude + "WORKS" + currentLongitude, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        } else {
            Toast.makeText(context, getResources().getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show();
        }
        ivBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvTitle.setText(getArguments().getString("category"));
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        btnSubmit.setTypeface(Utility.typeFaceForBoldText(getContext()));

        /*if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions((HomeScreenActivity)context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSIONS_REQUEST);
        }
        fusedLocationClient.getLastLocation()
        .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    // Logic to handle location object
                    currentLatitude = location.getLatitude();
                    currentLongitude = location.getLongitude();
                    Toast.makeText(context, currentLatitude + "WORKS" + currentLongitude, Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }


    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((HomeScreenActivity)context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSIONS_REQUEST);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.

                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions((HomeScreenActivity)context,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                                LOCATION_PERMISSIONS_REQUEST);
                    }
                    fusedLocationClient.getLastLocation()
                            .addOnSuccessListener((HomeScreenActivity)context, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    // Got last known location. In some rare situations this can be null.
                                    if (location != null) {
                                        // Logic to handle location object
                                        currentLatitude = location.getLatitude();
                                        currentLongitude = location.getLongitude();
                                        Toast.makeText(context, currentLatitude + "WORKS" + currentLongitude, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        // user also CHECKED "never ask again"
                        // you can either enable some fall back,
                        // disable features of your app
                        // or open another dialog explaining
                        // again the permission and directing to
                        // the app setting
                        ActivityCompat.requestPermissions((HomeScreenActivity)context,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                                LOCATION_PERMISSIONS_REQUEST);
                    }
                    else {
                        // user did NOT check "never ask again"
                        // this is a good place to explain the user
                        // why you need the permission and ask if he wants
                        // to accept it (the rationale)
                        // No explanation needed, we can request the permission.
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setMessage(getResources().getString(R.string.permission_denied))
                                .setCancelable(false)
                                .setPositiveButton(getResources().getString(R.string.settings),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                                startActivity(viewIntent);
                                                dialog.cancel();
                                            }
                                        });

                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();
                    }

                        /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setMessage(getResources().getString(R.string.permission_denied))
                                .setCancelable(false)
                                .setPositiveButton(getResources().getString(R.string.settings),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                                startActivity(viewIntent);
                                                dialog.cancel();
                                            }
                                        });

                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();*/

                }
            }

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
                }
                break;
        }
    }
}
