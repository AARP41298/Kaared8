package com.example.kaared_v08.ui.main;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaared_v08.DB.TinyDBCitas;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Citas;
import com.example.kaared_v08.ui.inicio.AdaptadorCitaItem;

import java.util.ArrayList;
import java.util.Calendar;


public class DiaFragment extends Fragment implements View.OnClickListener {
    RecyclerView mRecyclerView;
    AdaptadorCitaItem mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Citas> listaCitas = new ArrayList<>();
    ArrayList<Citas> listaCitasA = new ArrayList<>();

    View vista;
    int diaTab;

    public DiaFragment(int position) {
        diaTab = position + 2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_dia, container, false);
        cargarLista();
        cargarRecycler();
        return vista;
    }

    private void cargarRecycler() {

        mRecyclerView = vista.findViewById(R.id.recycler_dia_frag);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new AdaptadorCitaItem(listaCitasA);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }


    private void cargarLista() {
        TinyDBCitas load = new TinyDBCitas(getContext());
        listaCitas = load.getListObject("DBlistaCitas");
        listaCitasA.clear();
        Calendar calCita = Calendar.getInstance();
        Calendar ahorita = Calendar.getInstance();
        for (Citas c : listaCitas) {
            calCita.setTimeInMillis(c.getFechaIni());
            if (c.getStatus().equalsIgnoreCase("agendada") &&
                    calCita.get(Calendar.DAY_OF_WEEK) == diaTab &&
                    calCita.get(Calendar.WEEK_OF_YEAR) == ahorita.get(Calendar.WEEK_OF_YEAR)) {
                listaCitasA.add(c);
            }
        }

    }

    @Override
    public void onClick(View v) {

    }
}
