package com.example.kaared_v08.ui.resumen;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.kaared_v08.DB.TinyDBCaja;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Caja;
import com.example.kaared_v08.ui.agendar.DatePickerFragment;
import com.example.kaared_v08.ui.ingresos.AdaptadorCajaItemIngresos;

import java.util.ArrayList;
import java.util.Calendar;

public class ResumenFragment extends Fragment {

    RecyclerView mRecyclerView;
    AdaptadorCajaItemResumen mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Caja> listaCaja = new ArrayList<>();
    ArrayList<Caja> listaCajaR = new ArrayList<>();

    Button btnDesde, btnHasta;

    Calendar ahorita = Calendar.getInstance();
    Calendar desdeCal = Calendar.getInstance();
    Calendar hastaCal = Calendar.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resumen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        desdeCal.setTimeInMillis(10000L);
        hastaCal.set(Calendar.HOUR, 23);
        hastaCal.set(Calendar.MINUTE, 59);


        cargarControles();
        cargarEventos();
        cargarLista();
        cargarRecycler();
    }

    private void cargarEventos() {
        btnDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFechaDesde();
            }
        });
        btnHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFechaHasta();
            }
        });
    }

    private void obtenerFechaDesde() {
        int mes = ahorita.get(Calendar.MONTH);
        int dia = ahorita.get(Calendar.DAY_OF_MONTH);
        int anio = ahorita.get(Calendar.YEAR);

        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                desdeCal.set(year, month, dayOfMonth, 0, 0);
                cargarLista();
                cargarRecycler();
            }

        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    private void obtenerFechaHasta() {
        int mes = ahorita.get(Calendar.MONTH);
        int dia = ahorita.get(Calendar.DAY_OF_MONTH);
        int anio = ahorita.get(Calendar.YEAR);

        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                hastaCal.set(year, month, dayOfMonth, 23, 59);
                cargarLista();
                cargarRecycler();
            }
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    private void cargarControles() {
        btnDesde = getActivity().findViewById(R.id.btn_desde_resum);
        btnHasta = getActivity().findViewById(R.id.btn_hasta_resum);
    }

    private void cargarRecycler() {

        mRecyclerView = getActivity().findViewById(R.id.recycler_resumen_frag);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new AdaptadorCajaItemResumen(listaCajaR);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void cargarLista() {
        TinyDBCaja loadCaja = new TinyDBCaja(getContext());
        listaCaja = loadCaja.getListObject("DBlistaCaja");
        listaCajaR.clear();
        int total = 0;
        for (Caja i : listaCaja) {
            if (desdeCal.getTimeInMillis() <= i.getFecha() && i.getFecha() <= hastaCal.getTimeInMillis()) {
                listaCajaR.add(i);
                total += i.getMonto();
            }
        }
        listaCajaR.add(new Caja("", "", total, 0L));
    }


}
