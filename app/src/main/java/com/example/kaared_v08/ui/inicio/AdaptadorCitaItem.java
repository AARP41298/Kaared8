package com.example.kaared_v08.ui.inicio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Citas;

import java.util.ArrayList;
import java.util.Calendar;

public class AdaptadorCitaItem extends RecyclerView.Adapter<AdaptadorCitaItem.ExampleViewHolder> {

    private ArrayList<Citas> mListaCita;
    private OnItemClickListener mListener;

    Citas citaActual;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDia, tvServicio, tvHora, tvNombre;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvDia = itemView.findViewById(R.id.tv_ci_fecha);
            tvServicio = itemView.findViewById(R.id.tv_ci_servicio);
            tvHora = itemView.findViewById(R.id.tv_ci_hora);
            tvNombre = itemView.findViewById(R.id.tv_ci_nombre);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public AdaptadorCitaItem(ArrayList<Citas> listaCancion) {
        mListaCita = listaCancion;
    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cita, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        citaActual = mListaCita.get(position);


        holder.tvDia.setText(obtenerHMP());
        holder.tvServicio.setText(citaActual.getServicio());
        holder.tvHora.setText(citaActual.getHrs() + ":" + citaActual.getMin() + " " + obtenerAMPM(citaActual.getHrs()));
        holder.tvNombre.setText(citaActual.getNombre());


    }

    private String obtenerHMP() {
        Calendar c = Calendar.getInstance();
        if (c.get(Calendar.DAY_OF_MONTH) == citaActual.getDia() &&
                c.get(Calendar.MONTH) == citaActual.getMes() &&
                c.get(Calendar.YEAR) == citaActual.getAnio()) {
            return "Hoy";
        } else if (c.get(Calendar.DAY_OF_MONTH) + 1 == citaActual.getDia() &&
                c.get(Calendar.MONTH) == citaActual.getMes() &&
                c.get(Calendar.YEAR) == citaActual.getAnio()) {
            return "Mañana";
        } else if (c.get(Calendar.DAY_OF_MONTH) + 2 == citaActual.getDia() &&
                c.get(Calendar.MONTH) == citaActual.getMes() &&
                c.get(Calendar.YEAR) == citaActual.getAnio()) {
            return "Pasado";
        } else {
            return citaActual.getDia() + "/" + obtenerMes(citaActual.getMes()) + "/" + citaActual.getAnio();
        }
    }

    private Object obtenerAMPM(int hrs) {
        if (hrs < 12) {
            return "AM";
        } else {
            return "PM";
        }
    }

    @Override
    public int getItemCount() {
        return mListaCita.size();
    }

    private String obtenerMes(int month) {
        switch (month) {
            case 0:
                return "ene";
            case 1:
                return "feb";
            case 2:
                return "mar";
            case 3:
                return "abr";
            case 4:
                return "may";
            case 5:
                return "jun";
            case 6:
                return "jul";
            case 7:
                return "ago";
            case 8:
                return "sep";
            case 9:
                return "oct";
            case 10:
                return "nov";
            case 11:
                return "dic";
            default:
                return "error";
        }
    }

}

