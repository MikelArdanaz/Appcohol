package com.example.linux1.appcohol;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationSource;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.services.android.telemetry.location.LocationEngine;
import com.mapbox.services.android.telemetry.location.LocationEngineListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsManager;

import java.util.List;

import static com.example.linux1.appcohol.Constantes.COMPONENTES_SUPERMERCADOS;
import static java.lang.Math.abs;

public class MapaActivity extends AppCompatActivity implements PermissionsListener {

    private MapView mapView;
    private MapboxMap map;
    private FloatingActionButton floatingActionButton;
    private Button bt_volver;

    private LocationEngine locationEngine;
    private LocationEngineListener locationEngineListener;
    private PermissionsManager permissionsManager;

    private int Dia;
    private int Eroski;
    private int Mercadona;
    private int ElCorteIngles;
    private int Carrefour;
    private int Hipercor;
    private int Caprabo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.access_token));

        setContentView(R.layout.activity_mapa);

        locationEngine = LocationSource.getLocationEngine(this);
        locationEngine.activate();

        mapView = (MapView) findViewById(R.id.mv_mapa_mapa);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                map = mapboxMap;
            }
        });

        floatingActionButton = (FloatingActionButton) findViewById(R.id.bt_mapa_localizacion);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    toggleGps(!map.isMyLocationEnabled());
                }
            }
        });

        bt_volver = (Button) findViewById(R.id.bt_mapa_volver);
        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Dia = getIntent().getIntExtra("Dia", Dia);
        Eroski = getIntent().getIntExtra("Eroski", Eroski);
        Mercadona = getIntent().getIntExtra("Mercadona", Mercadona);
        ElCorteIngles = getIntent().getIntExtra("El Corte Inglés", ElCorteIngles);
        Carrefour = getIntent().getIntExtra("Carrefour", Carrefour);
        Hipercor = getIntent().getIntExtra("Hipercor", Hipercor);
        Caprabo = getIntent().getIntExtra("Caprabo", Caprabo);

        // DEBUG para comprobar que supermercados estan elegidos
        System.out.println(Dia + "|" + Eroski + "|" + Mercadona + "|" + ElCorteIngles + "|" + Carrefour + "|" +
                Hipercor + "|" + Caprabo);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

        if (locationEngineListener != null) {
            locationEngine.removeLocationEngineListener(locationEngineListener);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void toggleGps(boolean enableGps) {
        if (enableGps) {

            permissionsManager = new PermissionsManager(this);
            if (!PermissionsManager.areLocationPermissionsGranted(this)) {
                permissionsManager.requestLocationPermissions(this);
            } else {
                enableLocation(true);
            }
        } else {
            enableLocation(false);
        }
    }

    private void enableLocation(boolean enabled) {
        if (enabled) {

            Location lastLocation = locationEngine.getLastLocation(); //Esto es un waring, no afecta
            if (lastLocation != null) {
                marcarSupermercados( lastLocation );
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation), 16));
            }

            locationEngineListener = new LocationEngineListener() {
                @Override
                public void onConnected() {

                }

                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        marcarSupermercados( location );
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location), 16));
                        locationEngine.removeLocationEngineListener(this);
                    }
                }
            };
            locationEngine.addLocationEngineListener(locationEngineListener);
            floatingActionButton.setImageResource(R.drawable.ic_location_disabled_24dp);
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_my_location_24dp);
        }

        map.setMyLocationEnabled(enabled);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "Es necesario dar permisos de localizacion.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocation(true);
        } else {
            Toast.makeText(this, "No has dado permisos de locacalizacion.",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void marcarSupermercados( Location localizacion ){

        double latitud = localizacion.getLatitude();
        double longitud = localizacion.getLongitude();

        if( Dia == 1 ) {

            double latitud_mas_cerano = 0;
            double longitud_mas_cercano = 0;
            double error = 999;

            for ( ComponenteSupermercados supermercado : COMPONENTES_SUPERMERCADOS ) {

                if ( supermercado.getSupermercado().equalsIgnoreCase("Dia") ) {
                    double nuevo_error;
                    nuevo_error = (abs(latitud - supermercado.getLatitud()) + abs(longitud - supermercado.getLongitud()));
                    if (nuevo_error < error) {
                        error = nuevo_error;
                        longitud_mas_cercano = supermercado.getLongitud();
                        latitud_mas_cerano = supermercado.getLatitud();
                    }
                }

            }

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud_mas_cerano, longitud_mas_cercano))
                    .title("Dia"));

        }

        if( Eroski == 1 ) {

            double latitud_mas_cerano = 0;
            double longitud_mas_cercano = 0;
            double error = 999;

            for ( ComponenteSupermercados supermercado : COMPONENTES_SUPERMERCADOS ) {

                if ( supermercado.getSupermercado().equalsIgnoreCase("Eroski") ) {
                    double nuevo_error;
                    nuevo_error = (abs(latitud - supermercado.getLatitud()) + abs(longitud - supermercado.getLongitud()));
                    if (nuevo_error < error) {
                        error = nuevo_error;
                        longitud_mas_cercano = supermercado.getLongitud();
                        latitud_mas_cerano = supermercado.getLatitud();
                    }
                }

            }

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud_mas_cerano, longitud_mas_cercano))
                    .title("Eroski"));

        }

        if( Mercadona == 1 ) {

            double latitud_mas_cerano = 0;
            double longitud_mas_cercano = 0;
            double error = 999;

            for ( ComponenteSupermercados supermercado : COMPONENTES_SUPERMERCADOS ) {

                if ( supermercado.getSupermercado().equalsIgnoreCase("Mercadona") ) {
                    double nuevo_error;
                    nuevo_error = (abs(latitud - supermercado.getLatitud()) + abs(longitud - supermercado.getLongitud()));
                    if (nuevo_error < error) {
                        error = nuevo_error;
                        longitud_mas_cercano = supermercado.getLongitud();
                        latitud_mas_cerano = supermercado.getLatitud();
                    }
                }

            }

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud_mas_cerano, longitud_mas_cercano))
                    .title("Mercadona"));

        }

        if( ElCorteIngles == 1 ) {

            double latitud_mas_cerano = 0;
            double longitud_mas_cercano = 0;
            double error = 999;

            for ( ComponenteSupermercados supermercado : COMPONENTES_SUPERMERCADOS ) {

                if ( supermercado.getSupermercado().equalsIgnoreCase("El Corte Inglés") ) {
                    double nuevo_error;
                    nuevo_error = (abs(latitud - supermercado.getLatitud()) + abs(longitud - supermercado.getLongitud()));
                    if (nuevo_error < error) {
                        error = nuevo_error;
                        longitud_mas_cercano = supermercado.getLongitud();
                        latitud_mas_cerano = supermercado.getLatitud();
                    }
                }

            }

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud_mas_cerano, longitud_mas_cercano))
                    .title("El Corte Inglés"));

        }

        if( Carrefour == 1 ) {

            double latitud_mas_cerano = 0;
            double longitud_mas_cercano = 0;
            double error = 999;

            for ( ComponenteSupermercados supermercado : COMPONENTES_SUPERMERCADOS ) {

                if ( supermercado.getSupermercado().equalsIgnoreCase("Carrefour") ) {
                    double nuevo_error;
                    nuevo_error = (abs(latitud - supermercado.getLatitud()) + abs(longitud - supermercado.getLongitud()));
                    if (nuevo_error < error) {
                        error = nuevo_error;
                        longitud_mas_cercano = supermercado.getLongitud();
                        latitud_mas_cerano = supermercado.getLatitud();
                    }
                }

            }

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud_mas_cerano, longitud_mas_cercano))
                    .title("Carrefour"));

        }

        if( Hipercor == 1 ) {

            double latitud_mas_cerano = 0;
            double longitud_mas_cercano = 0;
            double error = 999;

            for ( ComponenteSupermercados supermercado : COMPONENTES_SUPERMERCADOS ) {

                if ( supermercado.getSupermercado().equalsIgnoreCase("Hipercor") ) {
                    double nuevo_error;
                    nuevo_error = (abs(latitud - supermercado.getLatitud()) + abs(longitud - supermercado.getLongitud()));
                    if (nuevo_error < error) {
                        error = nuevo_error;
                        longitud_mas_cercano = supermercado.getLongitud();
                        latitud_mas_cerano = supermercado.getLatitud();
                    }
                }

            }

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud_mas_cerano, longitud_mas_cercano))
                    .title("Hipercor"));

        }

        if( Caprabo == 1 ) {

            double latitud_mas_cerano = 0;
            double longitud_mas_cercano = 0;
            double error = 999;

            for ( ComponenteSupermercados supermercado : COMPONENTES_SUPERMERCADOS ) {

                if ( supermercado.getSupermercado().equalsIgnoreCase("Caprabo") ) {
                    double nuevo_error;
                    nuevo_error = (abs(latitud - supermercado.getLatitud()) + abs(longitud - supermercado.getLongitud()));
                    if (nuevo_error < error) {
                        error = nuevo_error;
                        longitud_mas_cercano = supermercado.getLongitud();
                        latitud_mas_cerano = supermercado.getLatitud();
                    }
                }

            }

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud_mas_cerano, longitud_mas_cercano))
                    .title("Caprabo"));

        }

    }
}