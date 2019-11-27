package com.example.kaared_v08.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kaared_v08.MainActivity;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.citas;
import com.example.kaared_v08.ui.gallery.GalleryViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InicioFragment extends Fragment {
    RecyclerView mRecyclerView;
    AdaptadorCitaItem mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<citas> listaCitas = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inicio_fragment, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cargarLista();
        cargarRecycler();
    }

    private void cargarRecycler() {

        mRecyclerView = getActivity().findViewById(R.id.recycler_inicio_frag);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new AdaptadorCitaItem(listaCitas);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdaptadorCitaItem.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), listaCitas.get(position).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarLista() {
        listaCitas.clear();
        listaCitas.add(new citas("1","Aaron","5545023016","Uñas","10/20/2019","15:23","50","100","agendada"));
        listaCitas.add(new citas("2","Eevee","7861541203","Tinte","10/20/2019","15:23","50","100","cancelada"));
        listaCitas.add(new citas("3","Aram","5527157000","Pelo","10/20/2019","15:23","50","100","confirmada"));
        listaCitas.add(new citas("4","Karina","7861234567","ABC","10/20/2019","15:23","50","100","agendada"));
        listaCitas.add(new citas("5","Manulz","7861234567","DEF","10/20/2019","15:23","50","100","agendada"));
        listaCitas.add(new citas("6","Adilene","7861234567","GHI","10/20/2019","15:23","50","100","agendada"));


    }
}