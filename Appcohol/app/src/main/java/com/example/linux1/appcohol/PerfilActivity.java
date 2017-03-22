package com.example.linux1.appcohol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseUser;

public class PerfilActivity extends AppCompatActivity {

    /* Elementos del layout */
    private TextView tv_usuario;
    private Button bt_cockteles;
    private Button bt_favoritos;
    private ListView lv_lista;

    /* Variables */
    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        /* Relacionar los elementos */
        tv_usuario = (TextView) findViewById(R.id.tv_perfil_usuario);
        bt_cockteles = (Button) findViewById(R.id.bt_perfil_cockteles);
        bt_favoritos = (Button) findViewById(R.id.bt_perfil_favoritos);
        lv_lista = (ListView) findViewById(R.id.lv_perfil_lista);


        /* Mostrar mis cockteles en la lista */
        bt_cockteles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /* Mostrar mis cocketeles favoritos en la lista */
        bt_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

            }
        });

        /* Mostrar el nombre de usuario */
        user = ParseUser.getCurrentUser();
        tv_usuario.setText(user.getUsername());
    }
}