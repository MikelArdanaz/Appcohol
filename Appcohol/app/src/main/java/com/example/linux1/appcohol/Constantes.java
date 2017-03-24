package com.example.linux1.appcohol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 21/03/2017.
 */

public class Constantes {

    public static final String[] COMPONENTES_NOMBRES = {

            "Coca-Cola", "KAS Limón","Vodka","Ron","Baileys","Ginebra","Whisky","Limoncello","Licor de hierbas","Orujo","Anís","Mojito","Brandy","Licor De Manzana","Tequila","Pacharán","Batido de Chocolate","Batido de Fresa","Batido de Vainilla","Sidra","Cava","Cerveza (Rubia)","Cerveza (Negra)","Cerveza con Tequila","Moscatel","Jerez","Gaseosa","Red Bull","Rockstar","Burn","Aquarius Limón","Aquarius Naranja","Bitter Kas","Pepsi","Sprite","Kas Naranja","Sunny","Nestea","Tónica","Sangria","Vermouth","Vino","Mosto"

    };



    public static final List<ComponenteDatos> COMPONENTES_DATOS = new ArrayList<ComponenteDatos>() {
        {
            add( new ComponenteDatos("Coca-Cola", 0.58, 42, "Eroski") );
            add( new ComponenteDatos("KAS Limón", 0.39, 33, "Mercadona"));
            add( new ComponenteDatos("KAS Naranja", 0.39, 34, "Mercadona"));
            add( new ComponenteDatos("Vodka",4.36,231,"El Corte Inglés"));
            add( new ComponenteDatos("Ron",4.39,231,"Dia"));
            add( new ComponenteDatos("Baileys",4.58,327,"Dia"));
            add( new ComponenteDatos("Ginebra",4.59,263,"Dia"));
            add( new ComponenteDatos("Whisky",4.95,250,"Carrefour"));
            add( new ComponenteDatos("Limoncello",5.09,300,"Dia"));
            add( new ComponenteDatos("Licor de hierbas",5.75,156,"Dia"));
            add( new ComponenteDatos("Orujo",6.05,225,"Dia"));
            add( new ComponenteDatos("Anís",7.85,267,"Hipercor"));
            add( new ComponenteDatos("Mojito",7.10,216,"Carrefour"));
            add( new ComponenteDatos("Brandy",8.75,213,"Mercadona"));
            add( new ComponenteDatos("Licor De Manzana",3.30,200,"Dia"));
            add( new ComponenteDatos("Tequila",8.63,110,"El Corte Inglés"));
            add( new ComponenteDatos("Pacharán",6.50,267,"Carrefour"));
            add( new ComponenteDatos("Batido de Chocolate",0.82,125,"Dia"));
            add( new ComponenteDatos("Batido de Fresa",1.05,113,"Dia"));
            add( new ComponenteDatos("Batido de Vainilla",1.05,149,"Dia"));
            add( new ComponenteDatos("Sidra",1.44,49,"Dia"));
            add( new ComponenteDatos("Cava",1.93,75,"Dia"));
            add( new ComponenteDatos("Cerveza (Rubia)",0.24,43,"Dia"));
            add( new ComponenteDatos("Cerveza (Negra)",0.5,43,"Dia"));
            add( new ComponenteDatos("Cerveza con Tequila",1.1,60,"Hipercor"));
            add( new ComponenteDatos("Moscatel",2.31,120,"Dia"));
            add( new ComponenteDatos("Jerez",1.99,98,"Dia"));
            add( new ComponenteDatos("Gaseosa",0.19,30,"Dia"));
            add( new ComponenteDatos("Red Bull",1,114,"Mercadona"));
            add( new ComponenteDatos("Rockstar",1.09,116,"Caprabo"));
            add( new ComponenteDatos("Burn",0.72,104,"Hipercor"));
            add( new ComponenteDatos("Aquarius Limón",0.59,23,"Caprabo"));
            add( new ComponenteDatos("Aquarius Naranja",0.59,23,"Caprabo"));
            add( new ComponenteDatos("Bitter Kas",0.7,32,"El Corte Inglés"));
            add( new ComponenteDatos("Pepsi",0.43,44,"Mercadona"));
            add( new ComponenteDatos("Sprite",0.38,46,"Caprabo"));
            add( new ComponenteDatos("Sunny",1.59,60,"Eroski"));
            add( new ComponenteDatos("Nestea",0.5,36,"Eroski"));
            add( new ComponenteDatos("Tónica",0.59,34,"Carrefour"));
            add( new ComponenteDatos("Sangria",1.16,96,"Dia"));
            add( new ComponenteDatos("Vermouth",2.05,130,"Dia"));
            add( new ComponenteDatos("Vino",1.18,83,"Mercadona"));
            add( new ComponenteDatos("Mosto",1.6,61,"El Corte Inglés"));
                 }

    };
    public static final List<ComponenteSupermercados> COMPONENTES_SUPERMERCADOS = new ArrayList<ComponenteSupermercados>() {
        {
         add( new ComponenteSupermercados("Dia",42.812745, -1.614591) );
         add( new ComponenteSupermercados("Dia",42.809030, -1.591073) );
         add( new ComponenteSupermercados("Dia",42.831411, -1.591760) );
         add( new ComponenteSupermercados("Dia",42.825053, -1.617466) );
         add( new ComponenteSupermercados("Dia",42.826846, -1.616359) );
         add( new ComponenteSupermercados("Dia",42.830999, -1.609944) );
         add( new ComponenteSupermercados("Dia",42.833018, -1.615952) );
         add( new ComponenteSupermercados("Dia",42.825193, -1.629875) );
         add( new ComponenteSupermercados("Dia",42.831940, -1.630345) );
         add( new ComponenteSupermercados("Dia",42.826111, -1.646083) );
         add( new ComponenteSupermercados("Dia",42.826364, -1.654437) );
         add( new ComponenteSupermercados("Dia",42.831160, -1.650245) );
         add( new ComponenteSupermercados("Dia",42.832146, -1.645051) );
         add( new ComponenteSupermercados("Dia",42.837034, -1.674588) );
         add( new ComponenteSupermercados("Dia",42.820053, -1.668424) );
         add( new ComponenteSupermercados("Dia",42.813787, -1.663699) );
         add( new ComponenteSupermercados("Dia",42.809564, -1.665576) );
         add( new ComponenteSupermercados("Dia",42.815072, -1.661822) );
         add( new ComponenteSupermercados("Dia",42.809678, -1.656565) );
         add( new ComponenteSupermercados("Dia",42.806557, -1.650902) );
         add( new ComponenteSupermercados("Dia",42.805983, -1.645989) );
         add( new ComponenteSupermercados("Dia",42.804559, -1.644519) );
         add( new ComponenteSupermercados("Dia",42.809495, -1.639325) );
         add( new ComponenteSupermercados("Dia",42.810597, -1.638480) );
         add( new ComponenteSupermercados("Dia",42.810252, -1.634569) );
         add( new ComponenteSupermercados("Dia",42.786330, -1.633755) );
         add( new ComponenteSupermercados("Dia",42.785824, -1.690170) );
         add( new ComponenteSupermercados("Dia",42.805202, -1.686258) );
         add( new ComponenteSupermercados("Dia",42.805110, -1.674994) );
         add( new ComponenteSupermercados("Eroski",42.787320, -1.693197) );
         add( new ComponenteSupermercados("Eroski",42.808289, -1.673317) );
         add( new ComponenteSupermercados("Eroski",42.810220, -1.660835) );
         add( new ComponenteSupermercados("Eroski",42.807186, -1.652901) );
         add( new ComponenteSupermercados("Eroski",42.804841, -1.653729) );
         add( new ComponenteSupermercados("Eroski",42.801311, -1.645457) );
         add( new ComponenteSupermercados("Eroski",42.804345, -1.644743) );
         add( new ComponenteSupermercados("Eroski",42.805972, -1.641885) );
         add( new ComponenteSupermercados("Eroski",42.806331, -1.635306) );
         add( new ComponenteSupermercados("Eroski",42.812013, -1.635869) );
         add( new ComponenteSupermercados("Eroski",42.813585, -1.643690) );
         add( new ComponenteSupermercados("Eroski",42.806497, -1.635456) );
         add( new ComponenteSupermercados("Eroski",42.826574, -1.617973) );
         add( new ComponenteSupermercados("Eroski",42.824175, -1.618086) );
         add( new ComponenteSupermercados("Eroski",42.823514, -1.613912) );
         add( new ComponenteSupermercados("Eroski",42.825913, -1.630982) );
         add( new ComponenteSupermercados("Eroski",42.832668, -1.639216) );
         add( new ComponenteSupermercados("Eroski",42.831290, -1.638464) );
         add( new ComponenteSupermercados("Eroski",42.826657, -1.651097) );
         add( new ComponenteSupermercados("Eroski",42.833992, -1.680348) );
         add( new ComponenteSupermercados("Carrefour",42.806361, -1.672987) );
         add( new ComponenteSupermercados("Carrefour",42.815206, -1.655584) );
         add( new ComponenteSupermercados("Carrefour",42.817732, -1.646972) );
         add( new ComponenteSupermercados("Carrefour",42.812236, -1.643908) );
         add( new ComponenteSupermercados("Carrefour",42.811267, -1.639725) );
         add( new ComponenteSupermercados("Carrefour",42.831692, -1.589603) );
         add( new ComponenteSupermercados("Mercadona",42.802495, -1.688100) );
         add( new ComponenteSupermercados("Mercadona",42.821636, -1.668874) );
         add( new ComponenteSupermercados("Mercadona",42.832715, -1.665784) );
         add( new ComponenteSupermercados("Mercadona",42.835233, -1.649991) );
         add( new ComponenteSupermercados("Mercadona",42.832212, -1.618405) );
         add( new ComponenteSupermercados("Mercadona",42.815593, -1.594029) );
         add( new ComponenteSupermercados("Mercadona",42.795946, -1.614972) );
         add( new ComponenteSupermercados("El Corte Inglés",42.813754, -1.644958) );
         add( new ComponenteSupermercados("Caprabo",42.759849, -1.634815) );
         add( new ComponenteSupermercados("Caprabo",42.801679, -1.688030) );
         add( new ComponenteSupermercados("Caprabo",42.807976, -1.682193) );
         add( new ComponenteSupermercados("Caprabo",42.810243, -1.664340) );
         add( new ComponenteSupermercados("Caprabo",42.815532, -1.662967) );
         add( new ComponenteSupermercados("Caprabo",42.821072, -1.668804) );
         add( new ComponenteSupermercados("Caprabo",42.837942, -1.674640) );
         add( new ComponenteSupermercados("Caprabo",42.833662, -1.662967) );
         add( new ComponenteSupermercados("Caprabo",42.804953, -1.654384) );
         add( new ComponenteSupermercados("Caprabo",42.825604, -1.652324) );
         add( new ComponenteSupermercados("Caprabo",42.829130, -1.644771) );
         add( new ComponenteSupermercados("Caprabo",42.823842, -1.647174) );
         add( new ComponenteSupermercados("Caprabo",42.803442, -1.642368) );
         add( new ComponenteSupermercados("Caprabo",42.810746, -1.641338) );
         add( new ComponenteSupermercados("Caprabo",42.812509, -1.636531) );
         add( new ComponenteSupermercados("Caprabo",42.832403, -1.635501) );
         add( new ComponenteSupermercados("Caprabo",42.831647, -1.628978) );
         add( new ComponenteSupermercados("Caprabo",42.829885, -1.620052) );
         add( new ComponenteSupermercados("Caprabo",42.832403, -1.612155) );
         add( new ComponenteSupermercados("Caprabo",42.824345, -1.619709) );
         add( new ComponenteSupermercados("Caprabo",42.785051, -1.689060) );
         add( new ComponenteSupermercados("Caprabo",42.805527, -1.646176) );
         add( new ComponenteSupermercados("Caprabo",42.810360, -1.633275) );
         add( new ComponenteSupermercados("Hipercor",42.829077, -1.582347) );
        }
     };
}
