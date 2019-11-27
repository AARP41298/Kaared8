package com.example.kaared_v08.entidad;

public class Citas {
    String idCitas,nombre,telefono,servicio,status;
    int hrs,min,precio,eta,dia,mes,anio;

    public Citas(String idCitas, String nombre, String telefono, String servicio, String status, int hrs, int min, int precio, int eta, int dia, int mes, int anio) {
        this.idCitas = idCitas;
        this.nombre = nombre;
        this.telefono = telefono;
        this.servicio = servicio;
        this.status = status;
        this.hrs = hrs;
        this.min = min;
        this.precio = precio;
        this.eta = eta;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public String getIdCitas() {
        return idCitas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getServicio() {
        return servicio;
    }

    public String getStatus() {
        return status;
    }

    public int getHrs() {
        return hrs;
    }

    public int getMin() {
        return min;
    }

    public int getPrecio() {
        return precio;
    }

    public int getEta() {
        return eta;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }
}
