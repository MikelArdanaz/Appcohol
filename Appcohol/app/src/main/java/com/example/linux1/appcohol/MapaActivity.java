package com.example.linux1.appcohol;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapzen.android.lost.api.LocationServices;

public class MapaActivity extends AppCompatActivity {

    /* Elementos del layout */
    private MapView mapa = null;
    private Button bt_volver;

    /* Variables */
    private MapboxMap map = null;
    private LocationServices locationServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_mapa);

        /* Relacionar elementos del layout */
        mapa = (MapView)findViewById(R.id.mv_mapa_mapa);
        bt_volver = (Button) findViewById(R.id.bt_mapa_volver);

        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mapa.onCreate(savedInstanceState);
        mapa.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {

                map = mapboxMap;
                /* Mostrar supermercados */
                    /* Centrar la vista en la posicion actual del usuario */
                        /* Obtener la localizacion actual del usuario */
                    /* Saber que supermercados va a visitar y cuales son los mas cercanos de cada tipo */
                    /* Mostrar los supermercados como marcadores */
                    /* Trazar la ruta mas rapida que pase por todos ellos */
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mapa.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapa.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapa.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapa.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapa.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapa.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapa.onSaveInstanceState(outState);
    }

}