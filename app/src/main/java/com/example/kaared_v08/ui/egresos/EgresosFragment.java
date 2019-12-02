package com.example.kaared_v08.ui.egresos;

import android.content.Context;
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
import com.example.kaared_v08.MainActivity;
import com.example.kaared_v08.MenuActivity;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Caja;
import com.example.kaared_v08.ui.ingresos.AdaptadorCajaItemIngresos;

import java.util.ArrayList;

public class EgresosFragment extends Fragment {
    RecyclerView mRecyclerView;
    AdaptadorCajaItemIngresos mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Caja> listaCaja = new ArrayList<>();
    ArrayList<Caja> listaCajaE = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_egresos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btnRegEgreso;
        btnRegEgreso=getView().findViewById(R.id.btn_egresos_frag);
        btnRegEgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AgregarEgresoActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        cargarLista();
        cargarRecycler();
    }

    private void cargarRecycler() {

        mRecyclerView = getActivity().findViewById(R.id.recycler_egresos_frag);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new AdaptadorCajaItemIngresos(listaCajaE);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void cargarLista() {
        TinyDBCaja loadCaja = new TinyDBCaja(getContext());
        listaCaja = loadCaja.getListObject("DBlistaCaja");
        listaCajaE.clear();
        int total = 0;
        for (Caja i : listaCaja) {
            if (i.getMonto() < 0) {
                listaCajaE.add(i);
                total += i.getMonto();
            }
        }
        //Agregar el TOTAL al recycler
        //listaCajaE.add(new Caja("", "", total, 0, 0, 0, 0, 0));
        listaCajaE.add(new Caja("",""))

    }
}
