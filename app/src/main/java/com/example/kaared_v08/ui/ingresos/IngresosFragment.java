package com.example.kaared_v08.ui.ingresos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kaared_v08.DB.TinyDBCaja;
import com.example.kaared_v08.DB.TinyDBCitas;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Caja;
import com.example.kaared_v08.entidad.Citas;
import com.example.kaared_v08.ui.egresos.AgregarEgresoActivity;
import com.example.kaared_v08.ui.inicio.AdaptadorCitaItem;

import java.util.ArrayList;

public class IngresosFragment extends Fragment {

    RecyclerView mRecyclerView;
    AdaptadorCajaItemIngresos mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Caja> listaCaja = new ArrayList<>();
    ArrayList<Caja> listaCajaI = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingresos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btnRegIngreso;
        btnRegIngreso = getView().findViewById(R.id.btn_ingresos_frag);
        btnRegIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AgregarIngresoActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        cargarLista();
        cargarRecycler();
    }

    private void cargarRecycler() {

        mRecyclerView = getActivity().findViewById(R.id.recycler_ingresos_frag);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new AdaptadorCajaItemIngresos(listaCajaI);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void cargarLista() {
        TinyDBCaja loadCaja = new TinyDBCaja(getContext());
        listaCaja = loadCaja.getListObject("DBlistaCaja");
        listaCajaI.clear();
        int total=0;
        for (Caja i : listaCaja) {
            if (0 <= i.getMonto()) {
                listaCajaI.add(i);
                total+=i.getMonto();
            }
        }
        listaCajaI.add(new Caja("","",total,0L));

    }
}
