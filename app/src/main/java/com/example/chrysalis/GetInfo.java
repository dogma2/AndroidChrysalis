package com.example.chrysalis;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GetInfo {
private static boolean IsOnlyUserDelegacion = false;

    private static ArrayList<String> DelegacionesUser = new ArrayList<String>(); //Primero se carga de aqui i despues se cargara del JSON
    private static ArrayList<String> ComunidadesUser = new ArrayList<String>();
    private static ArrayList<String> ProvinciasUser = new ArrayList<String>();

    public static void setDelegacionesUser(ArrayList<String> delegacionesUser) {
        DelegacionesUser = delegacionesUser;
    }

    public static void setAutonomiasUser(ArrayList<String> comunidadesUser) {
        ComunidadesUser = comunidadesUser;
    }

    public static void setProvinciasUser(ArrayList<String> provinciasUser) {
        ProvinciasUser = provinciasUser;
    }



    public static String GetHashLCode() {return "CODIGO"; }
    public static boolean GetIsOnlyUserDelegacion()
    {
        return IsOnlyUserDelegacion;
    }
    public static void SetIsOnlyUserDelegation(boolean isOnlyUserDelegacion){IsOnlyUserDelegacion = isOnlyUserDelegacion;}

    public static ArrayList<String> GetProvincias()
    {
        ArrayList<String> provincias = new ArrayList<String>();
        provincias.add("Araba/Álava");
        provincias.add("Albacete");
        provincias.add("Alicante");
        provincias.add("Almería");
        provincias.add("Ávila");
        provincias.add("Badajoz");
        provincias.add("Balears (Illes)");
        provincias.add("Barcelona");
        provincias.add("Burgos");
        provincias.add("Cáceres");
        provincias.add("Cádiz");
        provincias.add("Castellón");
        provincias.add("Ciudad Real");
        provincias.add("Córdoba");
        provincias.add("Coruña");
        provincias.add("Cuenca");
        provincias.add("Girona");
        provincias.add("Granada");
        provincias.add("Guadalajara");
        provincias.add("Gipuzkoa");
        provincias.add("Huelva");
        provincias.add("Huesca");
        provincias.add("Jaén");
        provincias.add("León");
        provincias.add("Lleida");
        provincias.add("Rioja");
        provincias.add("Lugo");
        provincias.add("Madrid");
        provincias.add("Málaga");
        provincias.add("Murcia");
        provincias.add("Navarra");
        provincias.add("Ourense");
        provincias.add("Asturias");
        provincias.add("Palencia");
        provincias.add("Palmas");
        provincias.add("Pontevedra");
        provincias.add("Salamanca");
        provincias.add("Santa Cruz de Tenerife");
        provincias.add("Cantabria");
        provincias.add("Segovia");
        provincias.add("Sevilla");
        provincias.add("Soria");
        provincias.add("Tarragona");
        provincias.add("Teruel");
        provincias.add("Toledo");
        provincias.add("Valencia/València");
        provincias.add("Valladolid");
        provincias.add("Bizkaia");
        provincias.add("Zamora");
        provincias.add("Zaragoza");
        provincias.add("Ceuta");
        provincias.add("Melilla");

        return provincias;
    }

    public static ArrayList<String> GetComunidadesAutonomas()
    {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("Andalucía");
        lista.add("Aragón");
        lista.add("Asturias");
        lista.add("Baleares");
        lista.add("Canarias");
        lista.add("Cantabria");
        lista.add("Castilla-La Mancha");
        lista.add("Castilla y León");
        lista.add("Cataluña");
        lista.add("Comunidad Valenciana");
        lista.add("Extremadura");
        lista.add("Galicia");
        lista.add("Madrid");
        lista.add("Murcia");
        lista.add("Navarra");
        lista.add("País Vasco");
        lista.add("La Rioja");

        return lista;

    }

    public static ArrayList<String> GetDelegaciones()
    {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("barcelona");

        return lista;
    }
    public static ArrayList<String> GetDelegacionesUser()
    {
        return DelegacionesUser;
    }
    public static ArrayList<String> GetProvinciasUser()
    {
        return ProvinciasUser;
    }
    public static ArrayList<String> GetComunidadesUser()
    {
       return ComunidadesUser;
    }
    public static String GetTerminosDeUso()
    {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tristique ac massa finibus tincidunt. Suspendisse ultricies urna non purus aliquet eleifend. Mauris in pellentesque neque. Morbi ut luctus velit. Nam at nunc tortor. Proin id nisi libero. Nam faucibus enim in ex accumsan ultricies. Sed a nisl et nulla lacinia viverra id ac turpis. Nunc in tortor felis. Nunc eleifend tincidunt elit at ullamcorper. Quisque congue elementum velit aliquet efficitur. Quisque bibendum, justo in ullamcorper tristique, ante turpis vulputate tellus, nec lobortis est metus vel odio. Fusce a nunc blandit, porta orci id, vulputate ante.\n" +
                "\n" +
                "Nunc id odio a risus viverra pellentesque. Nam elementum, dolor ac bibendum laoreet, est dolor posuere lacus, id venenatis ex magna ac lectus. In in ante nisi. Nullam rutrum eleifend libero vel volutpat. Aliquam placerat eget elit nec feugiat. In eget odio nunc. Sed molestie, arcu at aliquet placerat, justo erat venenatis libero, non pellentesque est felis quis nunc. Praesent ac urna ipsum. Donec sed quam arcu. Sed id scelerisque sem.\n" +
                "\n" +
                "Nam auctor, massa a cursus lobortis, orci urna aliquam nibh, ut suscipit neque magna non odio. Donec turpis purus, egestas elementum dictum ut, mattis et odio. Suspendisse at massa libero. In hac habitasse platea dictumst. Vestibulum aliquet nulla risus, eget iaculis enim elementum non. Cras suscipit tincidunt odio, vitae blandit velit iaculis vel. Quisque non posuere nunc. Donec finibus elit id orci egestas pellentesque nec sit amet lacus. Aliquam augue orci, efficitur at justo at, accumsan efficitur sem. Praesent cursus ex quis mi placerat elementum. Etiam eget posuere dolor, nec lacinia lectus. Duis bibendum, augue a mattis sodales, est ligula tempus neque, nec molestie mauris ex vitae enim.\n" +
                "\n" +
                "Curabitur id condimentum nisl. Curabitur id tortor lacus. Donec ornare turpis eu rhoncus ultrices. Duis ultrices massa a mattis interdum. Proin at dui id purus interdum varius. Suspendisse potenti. Aliquam mi dui, dapibus tempus malesuada sed, pharetra id mi.\n" +
                "\n" +
                "Suspendisse consectetur metus ac consectetur viverra. Sed gravida, sapien eget vulputate varius, purus elit varius nisi, ac aliquet tortor metus in sapien. Aliquam feugiat rutrum sapien id rhoncus. Aenean ac tristique dui. Vivamus lacinia luctus dui, et eleifend arcu. Nullam dignissim fringilla lectus id feugiat. Nam laoreet est sed dui blandit, nec posuere mauris blandit. Fusce vestibulum arcu a leo finibus, quis luctus tortor consectetur. Vestibulum vel augue feugiat, sagittis nulla sit amet, maximus diam. Phasellus ac orci in massa interdum imperdiet id convallis ex.";
    }


}
