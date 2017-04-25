package com.example.rjt.faster;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button saveButton;
    Double latitude, longitude;
    int index;
    FirebaseDatabase firebaseDatabase, fdb;
    DatabaseReference dbref, databaseReference;
    ArrayList<Marker> markerArrayList = new ArrayList<Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.w("Message : ", "Inside Mapactivity on Create");


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        putmarkers(mMap);
        saveButton = (Button) findViewById(R.id.button2);
        fdb = FirebaseDatabase.getInstance();
        databaseReference = fdb.getReference("clicks");
        LatLng cali = new LatLng(38, -121); //
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cali)); //Move camera over Callifornia
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {

                mMap.moveCamera(CameraUpdateFactory.zoomBy(15));
                LatLng position = marker.getPosition();


                latitude = position.latitude;
                longitude = position.longitude;

                index = getindexid(latitude, longitude);
                Log.w("u have touched ", markerArrayList.get(index).getName());
                saveButton.setVisibility(View.VISIBLE);


                return false;
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new updateClicks().execute("");
            }
        });


    }
    public void putmarkers(GoogleMap map) {

        final GoogleMap imap = map;

        firebaseDatabase = FirebaseDatabase.getInstance();
        dbref = firebaseDatabase.getReference("markers");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Marker marker = dataSnapshot1.getValue(Marker.class);

                    markerArrayList.add(marker);
                    Collections.shuffle(markerArrayList); //for shuffling the marker data
                    Log.w("markerclass data", marker.getName());

                }
                for (int i = 0; i < 10; i++) {

                    Double lati = Double.parseDouble(markerArrayList.get(i).getLatitude());
                    Double lngi = Double.parseDouble(markerArrayList.get(i).getLongitude());
                    imap.addMarker(new MarkerOptions().position(new LatLng(lati, lngi)).title(markerArrayList.get(i).getName()));
                    Log.w("Markers on Map   : ", i + ":  " + markerArrayList.get(i).getName());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public int getindexid(Double latitude, Double longitude) {

        String lat = latitude.toString();
        String logi= longitude.toString();
        Log.w("latitude in getindexid", lat);
        for (int i = 0; i < markerArrayList.size(); i++) {
            Log.w("for looping for  : " +i, markerArrayList.get(i).getLatitude());
            if (markerArrayList.get(i).getLatitude().equals(lat) && markerArrayList.get(i).getLongitude().equals(logi) ) {
                return i;
            }
        }
        return -1;
    }

    private class updateClicks extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            //Toast.makeText(getApplicationContext(), "we r at" + markerArrayList.get(index).getName(), Toast.LENGTH_LONG).show();
            fdb=FirebaseDatabase.getInstance();
            databaseReference= fdb.getReference("clicks").child(markerArrayList.get(index).getName());
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s= dataSnapshot.child("clickCounts").getValue().toString();
                    Integer count= Integer.valueOf(s);
                    int cnt= count+1;
                    databaseReference.child("clickCounts").setValue(cnt);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return null;
        }

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(MapsActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Thank you for using Fast ER" , Toast.LENGTH_LONG).show();
            Intent intent= new Intent(MapsActivity.this, MapsActivity.class);
            startActivity(intent);
            finish();
        }
        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}





