package com.cliknfix.user.submitProblem;


import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
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

        ivBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvTitle.setText(getArguments().getString("category"));
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        btnSubmit.setTypeface(Utility.typeFaceForBoldText(getContext()));

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                    //Toast.makeText(context, currentLatitude + "WORKS" + currentLongitude, Toast.LENGTH_SHORT).show();
                }
            }
        });

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
