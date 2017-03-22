package com.example.linux1.appcohol;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.ParseUser;

public class InicioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /* Elementos del layout */
    private TextView tv_menu_usuario;
    private View cabecera_menu;

    /* Variables */
    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Barra de navegacion lateral
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dl_inicio_menu);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navegacion_lateral);
        navigationView.setNavigationItemSelectedListener(this);

        /* Relacionar elementos del layout */
        cabecera_menu = navigationView.getHeaderView(0);
        tv_menu_usuario = (TextView) cabecera_menu.findViewById(R.id.tv_inicio_usuario);

        //Boton flotante
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        /* Abrir menu de navegacion lateral */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abrir menu de navegacion lateral
                drawer.openDrawer(GravityCompat.START);
            }
        });

        /* Mostrar el nombre de usuario */

        user = ParseUser.getCurrentUser();
        tv_menu_usuario.setText(user.getUsername());
    }

    /* Abrir/Cerrar menu lateral al pulsar retroceder */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dl_inicio_menu);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /* Gestionar las opciones del menu de navegacion lateral*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            /* Abrir ventana de perfil */
            Intent intent = new Intent( InicioActivity.this, PerfilActivity.class );
            startActivity( intent );

        } else if (id == R.id.nav_cocket) {
            /* Abrir ventana de añadir nuevo cocktel */
            Intent intent = new Intent( InicioActivity.this, NuevoCocktelActivity.class );
            startActivity( intent );

        }else if (id == R.id.nav_random) {
            /* Abrir ventana de cocktel aleatorio */
            Intent intent = new Intent( InicioActivity.this, RandomActivity.class );
            startActivity( intent );

        } else if (id == R.id.nav_calificacion) {
            //Mostrar lista ordenada por calificacion

        } else if (id == R.id.nav_calorias) {
            //Mostrar lista ordenada por calorias

        } else if (id == R.id.nav_precio) {
            //Mostrar lista ordenada por precio

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dl_inicio_menu);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
