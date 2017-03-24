package com.example.linux1.appcohol;

/**
 * Created by mikelillo_1 on 24/3/17.
 */

 public class ComponenteSupermercados {

     String supermercado;
     double latitud;
     double longitud;

     public ComponenteSupermercados(String supermercado, double latitud, double longitud) {
         this.supermercado = supermercado;
         this.latitud = latitud;
         this.longitud = longitud;
     }

     public String getSupermercado() {
         return supermercado;
     }

     public double getLatitud() {
         return latitud;
     }

     public  double getLongitud() {
         return longitud;
     }

 }
