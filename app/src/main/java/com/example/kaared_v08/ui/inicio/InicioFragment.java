package com.example.kaared_v08.ui.inicio;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.kaared_v08.DB.TinyDBCaja;
import com.example.kaared_v08.DB.TinyDBCitas;
import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Caja;
import com.example.kaared_v08.entidad.Citas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class InicioFragment extends Fragment {
    RecyclerView mRecyclerView;
    AdaptadorCitaItem mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Citas> listaCitas = new ArrayList<>();
    ArrayList<Citas> listaCitasA = new ArrayList<>();

    ArrayList<Caja> listaCaja = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cargarLista();
        cargarRecycler();

        TinyDBCaja loadCaja = new TinyDBCaja(getContext());
        listaCaja = loadCaja.getListObject("DBlistaCaja");

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
                //Toast.makeText(getActivity(), listaCitasA.get(position).getNombre(), Toast.LENGTH_SHORT).show();
                if (seleccionEsHoy(listaCitasA.get(position))) {
                    confirmacion(listaCitasA.get(position), listaCitas);
                } else
                    Toast.makeText(getContext(), "Solo puedes confirmar citas de hoy", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean seleccionEsHoy(Citas citas) {
        Calendar c = Calendar.getInstance();

        Calendar in = Calendar.getInstance();
        in.setTimeInMillis(citas.getFechaIni());


        if (c.get(Calendar.DAY_OF_MONTH) == in.get(Calendar.DAY_OF_MONTH) &&
                c.get(Calendar.MONTH) == in.get(Calendar.MONTH) &&
                c.get(Calendar.YEAR) == in.get(Calendar.YEAR)) {
            return true;
        } else {
            return false;
        }
    }

    private void confirmacion(final Citas citas, final ArrayList listaCitaGlobal) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Confirmación");
        alert.setMessage("Seguro que desea confirmar la cita?");
        alert.setCancelable(false);
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                for (int j = 0; j < listaCitas.size(); j++) {
                    Citas c = listaCitas.get(j);
                    if (c.getIdCitas() == citas.getIdCitas()) {
                        listaCitas.get(j).setStatus("confirmada");

                        TinyDBCitas saveCita = new TinyDBCitas(getContext());
                        saveCita.putListObject("DBlistaCitas", listaCitaGlobal);

                        guardarConfirmada(listaCitas.get(j));
                        //refresh fragment
                        refresh();
                    }
                }
            }
        });
        alert.setNegativeButton("No", null);
        alert.show();
    }

    private void guardarConfirmada(Citas cita) {
        Calendar c=Calendar.getInstance();

        listaCaja.add(new Caja(listaCaja.size() + 1 + "", cita.getServicio(), cita.getPrecio(), c.getTimeInMillis()));
        ordenarmM();
        TinyDBCaja saveCaja = new TinyDBCaja(getContext());
        saveCaja.putListObject("DBlistaCaja", listaCaja);


    }
    private void ordenarmM() {
        Collections.sort(listaCaja, new Comparator<Caja>() {
            public int compare(Caja c1, Caja c2) {
                return Long.valueOf(c1.getFecha()).compareTo(c2.getFecha());
            }
        });
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