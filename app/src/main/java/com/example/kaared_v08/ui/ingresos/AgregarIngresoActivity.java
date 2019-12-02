package com.example.kaared_v08.ui.ingresos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kaared_v08.DB.TinyDBCaja;
import com.example.kaared_v08.MenuActivity;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Caja;
import com.example.kaared_v08.ui.egresos.DatePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class AgregarIngresoActivity extends AppCompatActivity {
    EditText etConcepto, etMonto;
    TextView tvFecha;
    Button btnFecha, btnRegistro;

    TimePicker timePicker;
    int hora, minuto;
    ArrayList<Caja> listaCaja = new ArrayList<>();

    Calendar ahorita = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ingreso);

        cargarControles();
        cargarEventos();
    }

    private void cargarEventos() {
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                cargarListayGuardarRegistro();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();

            }
        });
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDatePickerDialog(v);
                obtenerFecha();
            }
        });

    }

    private void obtenerFecha() {
        int mes = ahorita.get(Calendar.MONTH);
        int dia = ahorita.get(Calendar.DAY_OF_MONTH);
        int anio = ahorita.get(Calendar.YEAR);

        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvFecha.setText(view.getDayOfMonth() + "-" + obtenerMes(view.getMonth()) + "-" + view.getYear());
            }
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    private String obtenerMes(int month) {
        switch (month) {
            case 0:
                return "ene";
            case 1:
                return "feb";
            case 2:
                return "mar";
            case 3:
                return "abr";
            case 4:
                return "may";
            case 5:
                return "jun";
            case 6:
                return "jul";
            case 7:
                return "ago";
            case 8:
                return "sep";
            case 9:
                return "oct";
            case 10:
                return "nov";
            case 11:
                return "dic";
            default:
                return "error";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void cargarListayGuardarRegistro() {
        TinyDBCaja loadCaja = new TinyDBCaja(this);
        listaCaja = loadCaja.getListObject("DBlistaCaja");
        String[] fecha = tvFecha.getText().toString().split("-");

        int dia, mes, anio;

        dia = Integer.parseInt(fecha[0]);
        mes = obtenerNumMes(fecha[1]);
        anio = Integer.parseInt(fecha[2]);


        hora = timePicker.getHour();
        minuto = timePicker.getMinute();

        Calendar cal = Calendar.getInstance();
        cal.set(anio, mes, dia, timePicker.getHour(), timePicker.getMinute());
        long fechaLong = cal.getTimeInMillis();

        Caja c = new Caja(listaCaja.size() + 1 + "", etConcepto.getText().toString(),
                Integer.parseInt("" + etMonto.getText().toString()), fechaLong);
        listaCaja.add(c);
        TinyDBCaja save = new TinyDBCaja(this);
        save.putListObject("DBlistaCaja", listaCaja);

    }

    private int obtenerNumMes(String s) {
        switch (s) {
            case "ene":
                return 0;
            case "feb":
                return 1;
            case "mar":
                return 2;
            case "abr":
                return 3;
            case "may":
                return 4;
            case "jun":
                return 5;
            case "jul":
                return 6;
            case "ago":
                return 7;
            case "sep":
                return 8;
            case "oct":
                return 9;
            case "nov":
                return 10;
            case "dic":
                return 11;
            default:
                return 404;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void cargarControles() {
        etConcepto = findViewById(R.id.et_ingreso_concepto);
        etMonto = findViewById(R.id.et_ingreso_monto);
        tvFecha = findViewById(R.id.tv_ingreso_fecha);
        btnFecha = findViewById(R.id.btn_ingreso_fecha);
        btnRegistro = findViewById(R.id.btn_ingreso_act);

        timePicker = findViewById(R.id.tp_ingreso);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
