package com.example.kaared_v08.entidad;

public class Caja {
    String id, concepto;
    int monto, dia, mes, anio, hrs, min;

    public Caja(String id, String concepto, int monto, int dia, int mes, int anio, int hrs, int min) {
        this.id = id;
        this.concepto = concepto;
        this.monto = monto;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.hrs = hrs;
        this.min = min;
    }

    public String getId() {
        return id;
    }

    public String getConcepto() {
        return concepto;
    }

    public int getMonto() {
        return monto;
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

    public int getHrs() {
        return hrs;
    }

    public int getMin() {
        return min;
    }
}
