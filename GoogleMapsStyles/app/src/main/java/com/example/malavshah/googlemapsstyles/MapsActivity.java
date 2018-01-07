package com.example.malavshah.googlemapsstyles;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        MapStyleOptions style;

        style = MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle_retro);
        mMap.setMapStyle(style);

        LatLng home = new LatLng(40.7534852,-74.0548206);
        LatLng school = new LatLng(40.7447825,-74.0260988);
        LatLng js = new LatLng(40.7321082,-74.0644955);
        mMap.addMarker(new MarkerOptions().position(home).title("My Home").snippet("Address: 33 Nelson Avenue")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.birdlogo2)));
        mMap.addMarker(new MarkerOptions().position(school).title("School").snippet("Stevens Institute of Technology")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.birdlogo2)));
        mMap.addMarker(new MarkerOptions().position(js).title("Journal Square")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.birdlogo2)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 13));

        LatLng park = new LatLng(40.7463031,-74.0604744);
        Marker temp = mMap.addMarker(new MarkerOptions()
                .position(park)
                .title("Leonard Park").icon(BitmapDescriptorFactory.fromResource(R.drawable.birdlogo2)));
        temp.showInfoWindow();

        MapMarkerBounce mm = new MapMarkerBounce();
        mm.onMarkerClick(temp);
    }

}


