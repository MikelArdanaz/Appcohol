package com.example.linux1.appcohol;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mapbox.directions.service.models.Waypoint;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationSource;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.services.Constants;
import com.mapbox.services.android.telemetry.location.LocationEngine;
import com.mapbox.services.android.telemetry.location.LocationEngineListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsManager;
import com.mapbox.services.api.ServicesException;
import com.mapbox.services.api.directions.v5.DirectionsCriteria;
import com.mapbox.services.api.directions.v5.MapboxDirections;
import com.mapbox.services.api.directions.v5.models.DirectionsResponse;
import com.mapbox.services.api.directions.v5.models.DirectionsRoute;
import com.mapbox.services.api.directions.v5.models.DirectionsWaypoint;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.commons.models.Position;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private DirectionsRoute currentRoute;
    private Location localizacion;

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
                localizacion = lastLocation;
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
                        localizacion = location;
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
        List<Position> lista_puntos = new ArrayList<>();

        lista_puntos.add(Position.fromCoordinates( localizacion.getLongitude(), localizacion.getLatitude()));
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


            lista_puntos.add( Position.fromCoordinates( longitud_mas_cercano, latitud_mas_cerano ) );

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

            lista_puntos.add( Position.fromCoordinates( longitud_mas_cercano, latitud_mas_cerano ) );

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

            lista_puntos.add( Position.fromCoordinates( longitud_mas_cercano, latitud_mas_cerano ) );

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

            lista_puntos.add( Position.fromCoordinates( longitud_mas_cercano, latitud_mas_cerano ) );

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

            lista_puntos.add( Position.fromCoordinates( longitud_mas_cercano, latitud_mas_cerano ) );

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

            lista_puntos.add( Position.fromCoordinates( longitud_mas_cercano, latitud_mas_cerano ) );

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

            lista_puntos.add( Position.fromCoordinates( longitud_mas_cercano, latitud_mas_cerano ) );

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud_mas_cerano, longitud_mas_cercano))
                    .title("Caprabo"));

        }

        /* En lista_puntos ya tenemos todos los puntos por los que vamos a pasar */

        System.out.println( lista_puntos );

        if( lista_puntos.size() > 1 ){

            Position origen = lista_puntos.get(0);
            for ( int i = 1; i<lista_puntos.size(); i++ ){
                Position destino = lista_puntos.get(i);

                try {
                    getRoute(origen, destino);
                } catch (ServicesException servicesException) {

                }

                origen = destino;
            }

        }

    }

    private void getRoute(Position origin, Position destination) throws ServicesException {

        MapboxDirections client = new MapboxDirections.Builder()
                .setOrigin(origin)
                .setDestination(destination)
                .setProfile(DirectionsCriteria.PROFILE_CYCLING)
                .setAccessToken(getString(R.string.access_token))
                .build();

        client.enqueueCall(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                if (response.body() == null) {
                    return;
                }

                currentRoute = response.body().getRoutes().get(0);

                drawRoute(currentRoute);
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {

            }
        });
    }

    private void drawRoute(DirectionsRoute route) {
        LineString lineString = LineString.fromPolyline(route.getGeometry(), Constants.OSRM_PRECISION_V5);
        List<Position> coordinates = lineString.getCoordinates();
        System.out.println("\n\n" + coordinates);
        LatLng[] points = new LatLng[coordinates.size()];

        for (int i = 0; i < coordinates.size(); i++) {

            points[i] = new LatLng(
                    coordinates.get(i).getLatitude()/10,
                    coordinates.get(i).getLongitude()/10);
        }

        map.addPolyline(new PolylineOptions()
                .add(points)
                .color(Color.parseColor("#009688"))
                .width(5));
    }
}