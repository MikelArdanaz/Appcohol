package com.example.linux1.appcohol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class NuevoCocktelActivity extends AppCompatActivity {

    /* Elementos del layout */
    private EditText et_cocktel;
    private ListView lv_componentes;
    private AutoCompleteTextView actv_componente;
    private Spinner sp_cantidad;
    private Button bt_addcomponente;
    private Spinner sp_personas;
    private Spinner sp_categoria;
    private Button bt_add;
    private Button bt_volver;

    /* Variables */

    /* Constantes */
    private static final String[] COMPONENTES = new String[] {
            "Coca-cola", "Kas limon", "Vodka blanco"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cocktel);

        /* Relacionar los elementos */
        et_cocktel = (EditText) findViewById(R.id.et_nuevococktel_nombre);
        lv_componentes = (ListView) findViewById(R.id.lv_nuevococktel_lista);
        actv_componente = (AutoCompleteTextView) findViewById(R.id.actv_nuevococktel_componente);
        sp_cantidad = (Spinner) findViewById(R.id.sp_nuevococktel_cantidad);
        bt_addcomponente = (Button) findViewById(R.id.bt_nuevococktel_addcomponente);
        sp_personas = (Spinner) findViewById(R.id.sp_nuevococktel_personas);
        sp_categoria = (Spinner) findViewById(R.id.sp_nuevococktel_categoria);
        bt_add = (Button) findViewById(R.id.bt_nuevococktel_add);
        bt_volver = (Button) findViewById(R.id.bt_nuevococktel_volver);

        /* Establecer los campos permitidos en cada Spinner */
        ArrayAdapter<CharSequence> adaptador_cantidades = ArrayAdapter.createFromResource(this,
                R.array.spiner_cantidad, android.R.layout.simple_spinner_item);
        adaptador_cantidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_cantidad.setAdapter(adaptador_cantidades);

        ArrayAdapter<CharSequence> adaptador_personas = ArrayAdapter.createFromResource(this,
                R.array.spiner_personas, android.R.layout.simple_spinner_item);
        adaptador_personas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_personas.setAdapter(adaptador_personas);

        ArrayAdapter<CharSequence> adaptador_categorias = ArrayAdapter.createFromResource(this,
                R.array.spiner_categoria, android.R.layout.simple_spinner_item);
        adaptador_categorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_categoria.setAdapter(adaptador_categorias);

        /* Establecer los campos permitidos en el campo de componentes */

        ArrayAdapter<String> adaptador_componentes = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COMPONENTES );
        actv_componente.setAdapter(adaptador_componentes);

        /* Cancelar y volver al inicio */
        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}