package com.example.kaared_v08.ui.inicio;

public class CitaItem{
    private String dia,nombre,servicio,hora;

    public CitaItem(String dia, String nombre, String servicio, String hora) {
        this.dia = dia;
        this.nombre = nombre;
        this.servicio = servicio;
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getServicio() {
        return servicio;
    }

    public String getHora() {
        return hora;
    }

}
