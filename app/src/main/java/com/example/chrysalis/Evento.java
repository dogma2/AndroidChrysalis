package com.example.chrysalis;

public class Evento {
    int Id;
    String CiudadEvento;
    String Nombre;
    String Descripcion;
    String DatosInteres;
    Integer Imagen;
    Integer Documentos;

    String Hora;
    int Img;
    String delegacion;

    public Evento(String nombre, String descripcion) {
        Nombre = nombre;
        Descripcion = descripcion;
        delegacion = "Crysalis cat";
    }

    public Evento(String nombre, String descripcion, int img) {
        Nombre = nombre;
        Descripcion = descripcion;
        Img = img;
        delegacion = "Crysalis cat";
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public int getImg() {
        return Img;
    }
}
