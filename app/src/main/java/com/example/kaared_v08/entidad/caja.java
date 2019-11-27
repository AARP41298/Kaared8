package com.example.kaared_v08.entidad;

public class caja {
    String id, fecha, concepto, monto, hora;

    public caja(String id, String fecha, String concepto, String monto, String hora) {
        this.id = id;
        this.fecha = fecha;
        this.concepto = concepto;
        this.monto = monto;
        this.hora = hora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
