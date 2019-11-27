package com.example.kaared_v08.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kaared_v08.DB.TinyDBCitas;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Citas;

import java.util.ArrayList;

public class InicioFragment extends Fragment {
    RecyclerView mRecyclerView;
    AdaptadorCitaItem mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Citas> listaCitas = new ArrayList<>();
    ArrayList<Citas> listaCitasA = new ArrayList<>();


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
        mAdapter = new AdaptadorCitaItem(listaCitasA);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdaptadorCitaItem.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), listaCitasA.get(position).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarLista() {
        TinyDBCitas load = new TinyDBCitas(getContext());
        listaCitas = load.getListObject("DBlistaCitas");
        for (Citas c : listaCitas){
            if (c.getStatus().equalsIgnoreCase("agendada")){
                listaCitasA.add(c);
            }
        }

    }
}