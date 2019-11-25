package com.example.kaared_v08.ui.agendar;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kaared_v08.R;

public class AgendarFragment extends Fragment {

    private AgendarViewModel mViewModel;
    Button btnFecha, btnAgendar;
    EditText etNom, etSer, etTel, etEta, etPrecio;
    TimePicker timePicker;
    TextView tvFecha;
    int hora, minuto;

    public static AgendarFragment newInstance() {
        return new AgendarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.agendar_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(AgendarViewModel.class);
        cargarControles();
        cargarEventos();
        // TODO: Use the ViewModel
    }


    private void cargarEventos() {
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                agendarCita();
            }
        });
        /*
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //De aqui jalamos la hora y el minuto
                hora = hourOfDay;
                minuto = minute;
            }
        });

         */
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void agendarCita() {
        hora = timePicker.getHour();
        minuto = timePicker.getMinute();
/*
        Toast.makeText(getContext(), etNom.getText().toString() + ", "
                        + etTel.getText().toString() + ", "
                        + etSer.getText().toString() + ", "
                        + hora + ":" + minuto + ", "
                        + tvFecha.getText().toString()
                , Toast.LENGTH_SHORT).show();

 */

        if (validaTexto()) {
            Toast.makeText(getContext(), "chido", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validaTexto() {
        boolean valido = true;

        if (TextUtils.isEmpty(etNom.getText())) {
            valido = false;
            etNom.setError(String.format("Ingresa el %1s", "nombre"));
            Toast.makeText(getContext(), "Faltan agregar campos", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(etTel.getText())) {
            valido = false;
            etTel.setError(String.format("Ingresa el %1s", "telefono"));
            Toast.makeText(getContext(), "Faltan agregar campos", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(etSer.getText())) {
            valido = false;
            etSer.setError(String.format("Ingresa el %1s", "servicio"));
            Toast.makeText(getContext(), "Faltan agregar campos", Toast.LENGTH_SHORT).show();
        }
        if (tvFecha.getText().toString().equalsIgnoreCase("dd - mm - aaaa")) {
            valido = false;
            Toast.makeText(getContext(), "Ingresa una fecha", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(etEta.getText())) {
            valido = false;
            etEta.setError(String.format("Ingresa el %1s", "tiempo estimado"));
            Toast.makeText(getContext(), "Faltan agregar campos", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(etPrecio.getText())) {
            valido = false;
            etPrecio.setError(String.format("Ingresa el %1s", "precio"));
            Toast.makeText(getContext(), "Faltan agregar campos", Toast.LENGTH_SHORT).show();
        }

        return valido;
    }

    private void cargarControles() {
        btnFecha = getView().findViewById(R.id.btn_agfrag_fecha);
        btnAgendar = getView().findViewById(R.id.btn_agfrag_agendar);

        etNom = getView().findViewById(R.id.et_agfrag_nombre);
        etTel = getView().findViewById(R.id.et_agfrag_tel);
        etSer = getView().findViewById(R.id.et_agfrag_servicio);
        etEta = getView().findViewById(R.id.et_agfrag_eta);
        etPrecio = getView().findViewById(R.id.et_agfrag_precio);

        tvFecha = getView().findViewById(R.id.tv_agfrag_fecha);
        timePicker = getView().findViewById(R.id.tp_agfrag);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

}
