package com.example.kaared_v08.entidad;

public class Citas {
    String idCitas,nombre,telefono,servicio,status;
    int precio;
    long fechaIni,fechaFin;

    public Citas(String idCitas, String nombre, String telefono, String servicio, String status, int precio, long fechaIni, long fechaFin) {
        this.idCitas = idCitas;
        this.nombre = nombre;
        this.telefono = telefono;
        this.servicio = servicio;
        this.status = status;
        this.precio = precio;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public String getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(String idCitas) {
        this.idCitas = idCitas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public long getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(long fechaIni) {
        this.fechaIni = fechaIni;
    }

    public long getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(long fechaFin) {
        this.fechaFin = fechaFin;
    }
}
