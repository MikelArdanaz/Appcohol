package com.example.linux1.appcohol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    /* Elementos del layout */
    private Button bt_aleatorio;
    private List<ParseObject> lista_objetos;

    /* Variables */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        /* Relacionar los elementos */
        bt_aleatorio = (Button) findViewById(R.id.bt_random_aleatorio);


        /* Generar Cocktel aleatorio */
        bt_aleatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("cockteles");
                try {
                    lista_objetos = query.find();
                    Random random = new Random();
                    int indice = random.nextInt(lista_objetos.size());
                    Intent intent = new Intent( RandomActivity.this, MostrarCocktelActivity.class );
                    intent.putExtra("Cocktel", (String) lista_objetos.get(indice).get("nombre") );
                    intent.putExtra("Precio", Double.toString((Double) lista_objetos.get(indice).get("precio")));
                    intent.putExtra("Personas", (String) lista_objetos.get(indice).get("personas"));
                    intent.putExtra("Calorias", Integer.toString((Integer) lista_objetos.get(indice).get("calorias")));
                    intent.putExtra("Calificacion", Integer.toString((Integer) lista_objetos.get(indice).get("calificacion")));
                    startActivity(intent);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
