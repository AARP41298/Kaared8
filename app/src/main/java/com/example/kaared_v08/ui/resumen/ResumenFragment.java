package com.example.kaared_v08.ui.resumen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaared_v08.DB.TinyDBCaja;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Caja;
import com.example.kaared_v08.ui.ingresos.AdaptadorCajaItemIngresos;

import java.util.ArrayList;

public class ResumenFragment extends Fragment {

    RecyclerView mRecyclerView;
    AdaptadorCajaItemResumen mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Caja> listaCaja = new ArrayList<>();
    ArrayList<Caja> listaCajaR = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resumen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cargarLista();
        cargarRecycler();
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

            listaCajaR.add(i);
            total += i.getMonto();

        }
        listaCajaR.add(new Caja("", "", total, 0L));

    }
}
