package com.example.linux1.appcohol;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v4.app.DialogFragment;


import com.parse.DeleteCallback;
import com.parse.ParseUser;

/**
 * Created by mikelillo_1 on 23/3/17.
 */

public class EliminarCuenta extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Estás seguro de que deseas eliminar tu cuenta?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ParseUser user = ParseUser.getCurrentUser();//Obtenemos el usuario actual
                        user.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(com.parse.ParseException e) {
                                if (e == null) {
                                    Intent intent = new Intent(getContext(),MainActivity.class);
                                    startActivity( intent );
                                } else {
                                    Toast.makeText(getActivity(), "No se ha podido eliminar la cuenta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();//Cerrar Popup
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


}
