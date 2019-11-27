package com.example.kaared_v08.entidad;

public class citas {
    String idCitas,nombre,telefono,servicio,fecha,hora,eta,precio,status;

    public citas(String idCitas, String nombre, String telefono, String servicio, String fecha, String hora, String eta, String precio, String status) {
        this.idCitas = idCitas;
        this.nombre = nombre;
        this.telefono = telefono;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
        this.eta = eta;
        this.precio = precio;
        this.status = status;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
