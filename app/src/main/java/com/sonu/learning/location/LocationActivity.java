package com.sonu.learning.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sonu.learning.R;

public class LocationActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION = 100;
    Button buttonGetLocation, buttonStopLocation;
    TextView textViewLatitude, textViewLongitude;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private Location mLocation;
    private LocationCallback locationCallback;
    private static final String TAG = LocationActivity.class.getSimpleName();

    private static final int UPDATE_INTERVAL = 5000; // 5 seconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        buttonGetLocation = (Button) findViewById(R.id.buttonGetLocation);
        buttonStopLocation = (Button) findViewById(R.id.buttonStopLocation);

        textViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
        textViewLongitude = (TextView) findViewById(R.id.textViewLongitude);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.d(TAG, "onLocationResult: is available ");
            }

            @Override
            public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
                if (locationAvailability.isLocationAvailable()) {
                    Log.d(TAG, "onLocationAvailability: location available");
                } else {
                    Log.d(TAG, "onLocationAvailability: location not available");
                }
            }
        };

        buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGettingLocation();
            }
        });

        buttonStopLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopLocationRequests();
            }
        });


    }

    private void stopLocationRequests() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private void startGettingLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, getMainLooper());
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
                mLocation = location;
                textViewLatitude.setText("" + mLocation.getLatitude());
                textViewLongitude.setText("" + mLocation.getLongitude());
            }).addOnFailureListener(e -> {

            });

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(LocationActivity.this, "Permission needed", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
            }

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        startGettingLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationRequests();

    }

}