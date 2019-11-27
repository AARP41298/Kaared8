package com.example.kaared_v08.ui.cancelar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kaared_v08.DB.TinyDBCitas;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Citas;
import com.example.kaared_v08.ui.inicio.AdaptadorCitaItem;

import java.util.ArrayList;


public class CancelarFragment extends Fragment {


    RecyclerView mRecyclerView;
    AdaptadorCitaItem mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Citas> listaCitas = new ArrayList<>();
    ArrayList<Citas> listaCitasA = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cancelar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cargarLista();
        cargarRecycler();
    }

    private void cargarRecycler() {

        mRecyclerView = getActivity().findViewById(R.id.recycler_cancelar_frag);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new AdaptadorCitaItem(listaCitasA);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdaptadorCitaItem.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(getActivity(), listaCitasA.get(position).getNombre(), Toast.LENGTH_SHORT).show();
                confirmacion(listaCitasA.get(position), listaCitas);
            }
        });
    }

    private void confirmacion(final Citas citas, final ArrayList listaCitaGlobal) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Confirmación");
        alert.setMessage("Seguro que desea cancelar la cita?");
        alert.setCancelable(false);
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                for (int j = 0; j < listaCitas.size(); j++) {
                    Citas c = listaCitas.get(j);
                    if (c.getIdCitas() == citas.getIdCitas()) {
                        listaCitas.get(j).setStatus("cancelada");

                        TinyDBCitas save = new TinyDBCitas(getContext());
                        save.putListObject("DBlistaCitas", listaCitaGlobal);
                        //refresh fragment
                        refresh();
                    }

                }


            }
        });
        alert.setNegativeButton("No", null);
        alert.show();
    }

    private void refresh() {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

    private void cargarLista() {
        TinyDBCitas load = new TinyDBCitas(getContext());
        listaCitas = load.getListObject("DBlistaCitas");
        listaCitasA.clear();
        for (Citas c : listaCitas) {
            if (c.getStatus().equalsIgnoreCase("agendada")) {
                listaCitasA.add(c);
            }
        }

    }
}
