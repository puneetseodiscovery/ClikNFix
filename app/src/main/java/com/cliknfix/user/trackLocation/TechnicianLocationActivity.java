package com.cliknfix.user.trackLocation;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.technicianDetail.TechnicianDetailActivity;
import com.cliknfix.user.util.Utility;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TechnicianLocationActivity extends FragmentActivity implements OnMapReadyCallback {


    private static final String TAG = TechnicianLocationActivity.class.getSimpleName();
    private HashMap<String, Marker> mMarkers = new HashMap<>();
    private GoogleMap mMap;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;

    private double userLat,techLat;
    private double userLong,techLong;

    private FusedLocationProviderClient fusedLocationClient;

    private static final int LOCATION_PERMISSIONS_REQUEST = 1;
    LatLng locationUser,locationTech;
    Context context;
    String techId,techName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_location);
        ButterKnife.bind(this);
        init();
        context = TechnicianLocationActivity.this;
        techId = getIntent().getStringExtra("techId");
        techName = getIntent().getStringExtra("techName");
        /*techId = "99";
        techName = "Sapna";*/
    }

    private void init() {
        llLocation.setVisibility(View.VISIBLE);
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvDistance.setTypeface(Utility.typeFaceForText(this));
        tvTime.setTypeface(Utility.typeFaceForText(this));
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSIONS_REQUEST);
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            userLat = location.getLatitude();
                            userLong = location.getLongitude();
                            /*userLat = 30.7333;
                            userLong = 76.7794;*/
                            Toast.makeText(TechnicianLocationActivity.this, userLat + "WORKS" + userLong, Toast.LENGTH_SHORT).show();

                            //subscribeToUpdates();

                        }
                    }
                });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void drawPolyLine(LatLng tech) {
        LatLng user = new LatLng(userLat,userLong);
        //mMap.addMarker(new MarkerOptions().position(barcelona).title(userLat + "," + userLong));
        mMap.addMarker(new MarkerOptions().position(user).title("You"));

        /*LatLng madrid = new LatLng(techLat,techLong);
        mMap.addMarker(new MarkerOptions().position(madrid).title(techLat + "," + techLong));*/

        List<LatLng> path = new ArrayList();

        String userLoc = userLat + "," + userLong;
        String techLoc = techLat + "," + techLong;

        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDKpkPtlJLZC0KR-p-cvb4QXG_5JtXFL40")
                .build();
        //DirectionsApiRequest req = DirectionsApi.getDirections(context, "41.385064,2.173403", "40.416775,-3.70379");
        DirectionsApiRequest req = DirectionsApi.getDirections(context, userLoc, techLoc);
        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs !=null) {
                    Log.e("legs length","" + route.legs.length);
                    //for(int i=0; i<route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[0];
                        Log.e("distance","" + leg.distance);
                        Log.e("time","" + leg.duration);
                        String distance,time;
                        distance = String.valueOf(leg.distance);
                        time = String.valueOf(leg.duration) + " to reach";
                        tvDistance.setText(distance);
                        tvTime.setText(time);
                        if (leg.steps != null) {
                            for (int j=0; j<leg.steps.length;j++){
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length >0) {
                                    for (int k=0; k<step.steps.length;k++){
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    //}
                    /*Info distanceInfo = route.getLegList().get(0).getDistance();
                    Info durationInfo = route.getLegList().get(0).getDuration();*/
                    //route.legs
                }
            }
        } catch(Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }
        mMap.setTrafficEnabled(true);

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLACK).width(10);
            mMap.addPolyline(opts);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tech, 10));


    }

    public void onBackClicked(View view){
        startActivity(new Intent(this, TechnicianDetailActivity.class));
    }

    public void onCrossClicked(View view){
        llLocation.setVisibility(View.GONE);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(10);
        //mMap.setMaxZoomPreference(30);
        //loginToFirebase();
        subscribeToUpdates();
    }



    private void loginToFirebase() {
        String email = getString(R.string.firebase_email);
        String password = getString(R.string.firebase_password);
        // Authenticate with Firebase and subscribe to updates
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
                email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    subscribeToUpdates();
                    Log.d(TAG, "firebase auth success");
                } else {
                    Log.d(TAG, "firebase auth failed");
                }
            }
        });
    }

    private void subscribeToUpdates() {
        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference(getString(R.string.firebase_path));
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(getString(R.string.firebase_path));
        ref.child(techId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.getValue() != null) {
                        try {
                            Log.e("TAG", "" + dataSnapshot.getValue()); // your name values you will get here
                            LatLng tech = setMarker(dataSnapshot);
                            drawPolyLine(tech);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("TAG", " it's null.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* Log.e("ref","" + ref);
        Log.e("ref","" + ref.getRoot());
        Log.e("ref","" + ref.getRoot().child("99").getKey());*/
    }

    private LatLng setMarker(DataSnapshot dataSnapshot) {
        // When a location update is received, put or update
        // its value in mMarkers, which contains all the markers
        // for locations received, so that we can build the
        // boundaries required to show them all on the map at once
        //String key = dataSnapshot.getKey();

        //String name = "Name";
        //String key = "99";
        HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
        techLat = Double.parseDouble(value.get("latitude").toString());
        techLong = Double.parseDouble(value.get("longitude").toString());
        /*techLat = 30.975254;
        techLong = 76.527328;*/

        LatLng tech = new LatLng(techLat,techLong);
        //mMap.addMarker(new MarkerOptions().position(madrid).title(techLat + "," + techLong));
        mMap.addMarker(new MarkerOptions().position(tech).title(techName));
        return tech;
        /*
        locationTech = new LatLng(techLat, techLong);
        if (!mMarkers.containsKey(key)) {
            mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(techLat + "," + techLong).position(locationTech)));
        } else {
            mMarkers.get(key).setPosition(locationTech);
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : mMarkers.values()) {
            builder.include(marker.getPosition());
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));*/
    }

    private String getEndLocationTitle(DirectionsResult results){
        return  "Time :"+ results.routes[0].legs[0].duration.humanReadable + " Distance :" + results.routes[0].legs[0].distance.humanReadable;
    }
}
