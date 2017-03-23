package com.example.linux1.appcohol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

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
                    int indice = ((int) Math.random() * lista_objetos.size()-1); //Los
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}