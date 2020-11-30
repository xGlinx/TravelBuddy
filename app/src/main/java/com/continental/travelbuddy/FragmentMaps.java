package com.continental.travelbuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentMaps extends SupportMapFragment implements OnMapReadyCallback {
    double lat,lon;
    public  FragmentMaps (){

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View rootView= super.onCreateView(layoutInflater, viewGroup, bundle);
        if (getArguments()!=null){
            this.lat=getArguments().getDouble("lat");
            this.lon=getArguments().getDouble("lon");

        }
        getMapAsync(this);

        return rootView;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng=new LatLng(lat,lon);
        float zoom=17;

        LatLng Elcarmen = new LatLng(-12.068001, -75.210012);
        googleMap.addMarker(new MarkerOptions().position(Elcarmen).title("Plaza De La Constitución"));

        LatLng DanielCarrion = new LatLng(-12.070713, -75.208430);
        googleMap.addMarker(new MarkerOptions().position(DanielCarrion).title("Parque Huamanmarca"));

        LatLng ClinicaCoset = new LatLng(-12.049230, -75.197299);
        googleMap.addMarker(new MarkerOptions().position(ClinicaCoset).title("Parque De La Identidad"));

        LatLng HosUniversal = new LatLng(-12.062928, -75.196466);
        googleMap.addMarker(new MarkerOptions().position(HosUniversal).title("Cerrito de la Libertad"));

        LatLng CliRebagliategi = new LatLng(-12.054723, -75.183842);
        googleMap.addMarker(new MarkerOptions().position(CliRebagliategi).title("Torre Torre"));

        LatLng HospRamPriale = new LatLng(-12.117467, -75.205698);
        googleMap.addMarker(new MarkerOptions().position(HospRamPriale).title("Centro Arqueológico de Wuarihuilca"));

        LatLng HospNeoPla = new LatLng(-12.056086, -75.212896);
        googleMap.addMarker(new MarkerOptions().position(HospNeoPla).title("Parque de los Sombreros"));

        LatLng Esscap3 = new LatLng(-12.079316, -75.198190);
        googleMap.addMarker(new MarkerOptions().position(Esscap3).title("Museo Yalpana Yasi "));

        LatLng CliCam = new LatLng(-12.005838, -75.193265);
        googleMap.addMarker(new MarkerOptions().position(CliCam).title("Parque De Los Mates Burilados "));

        LatLng Sis = new LatLng(-11.988310, -75.199073);
        googleMap.addMarker(new MarkerOptions().position(Sis).title("Bosque Dorado"));


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(latLng));
        //googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marcador)).anchor(0.0f,1.0f).position(latLng));
        UiSettings settings=googleMap.getUiSettings();
        settings.setZoomControlsEnabled(true);


    }
}
