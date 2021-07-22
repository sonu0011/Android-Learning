package com.sonu.learning.location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sonu.learning.R;

import static com.sonu.learning.location.Constants.BUNDLE_SAVE_ADDRESS;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mapView;
    private TextView address;
    private static final int LOCATION_PERMISSION = 100;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private Location mLocation;
    private LocationCallback locationCallback;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private AddressResultReceiver resultReceiver;
    private String locationAddress;
    private static final int UPDATE_INTERVAL = 5000; // 5 seconds
    private boolean isAddressRequired;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        address = findViewById(R.id.address);

        mapView = findViewById(R.id.map);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        resultReceiver = new AddressResultReceiver(new Handler());
        Bundle bundle = null;
        String savedAddress = "";
        if (savedInstanceState != null) {
            bundle = savedInstanceState.getBundle(getResources().getString(R.string.google_maps_key));
            if (savedInstanceState.containsKey(BUNDLE_SAVE_ADDRESS)) {
                savedAddress = savedInstanceState.getString(BUNDLE_SAVE_ADDRESS);
            }
        }

        address.setText(savedAddress);
        if (savedAddress != ""){
            address.setTextColor(getResources().getColor(R.color.design_default_color_primary_dark));
        }


        mapView.onCreate(bundle);

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

        startGettingLocation();
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
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                //      .findFragmentById(R.id.map);

                mapView.getMapAsync(this);

            }).addOnFailureListener(e -> {
                Log.d(TAG, "startGettingLocation: on failure");
            });

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(MapsActivity.this, "Permission needed", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
            }

        }


    }

    private class AddressResultReceiver extends ResultReceiver {


        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == Constants.SUCCESS_RESULT) {
                locationAddress = resultData.getString(Constants.RESULT_DATA_KEY);
                address.setText(locationAddress);
                isAddressRequired = false;
                address.setTextColor(getResources().getColor(R.color.design_default_color_primary_dark));
            } else {
                locationAddress = resultData.getString(Constants.RESULT_DATA_KEY);
                address.setText(locationAddress);
                isAddressRequired = false;
                address.setTextColor(Color.RED);
            }
        }
    }

    private void getAddress() {
        if (!Geocoder.isPresent()) {
            Toast.makeText(this, "Geocoder is not preset", Toast.LENGTH_SHORT).show();
        } else {
            startAddressFetchService();
        }
    }

    private void startAddressFetchService() {
        Intent intent = new Intent(this, AddressFetchService.class);
        intent.putExtra(Constants.RECEIVER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLocation);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        startGettingLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        stopLocationRequests();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_SAVE_ADDRESS, locationAddress);
        Log.d(TAG, "onSaveInstanceState: ");
        Bundle bundle = outState.getBundle(getResources().getString(R.string.google_maps_key));
        if (bundle == null) {
            bundle = new Bundle();
            outState.putBundle(getResources().getString(R.string.google_maps_key), bundle);
        }
        mapView.onSaveInstanceState(bundle);
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

        // Add a marker in Sydney and move the camera

        LatLng currentLlocaiton = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(currentLlocaiton).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLlocaiton));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLlocaiton, 15.0f));
    }

    public void getAddress(View view) {
        getAddress();

    }
}