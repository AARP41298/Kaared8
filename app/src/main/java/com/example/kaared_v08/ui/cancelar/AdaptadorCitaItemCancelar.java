package com.example.kaared_v08.ui.cancelar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Citas;
import com.example.kaared_v08.ui.cancelar.AdaptadorCitaItemCancelar;

import java.util.ArrayList;
import java.util.Calendar;

public class AdaptadorCitaItemCancelar extends RecyclerView.Adapter<AdaptadorCitaItemCancelar.ExampleViewHolder> {
    private ArrayList<Citas> mListaCita;
    private AdaptadorCitaItemCancelar.OnItemClickListener mListener;

    Citas citaActual;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdaptadorCitaItemCancelar.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDia, tvServicio, tvHora, tvNombre;


        public ExampleViewHolder(View itemView, final AdaptadorCitaItemCancelar.OnItemClickListener listener) {
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

    public AdaptadorCitaItemCancelar(ArrayList<Citas> listaCancion) {
        mListaCita = listaCancion;
    }


    @Override
    public AdaptadorCitaItemCancelar.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cita, parent, false);
        AdaptadorCitaItemCancelar.ExampleViewHolder evh = new AdaptadorCitaItemCancelar.ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(AdaptadorCitaItemCancelar.ExampleViewHolder holder, int position) {
        citaActual = mListaCita.get(position);


        holder.tvDia.setText(obtenerHMP());
        holder.tvServicio.setText(citaActual.getServicio());
        //holder.tvHora.setText(citaActual.getHrs() + ":" + citaActual.getMin() + " " + obtenerAMPM(citaActual.getHrs()));
        holder.tvHora.setText(obtenerHora());
        holder.tvNombre.setText(citaActual.getNombre());


    }

    private String obtenerHora() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(citaActual.getFechaIni());
        return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + " " + obtenerAMPM(c.get(Calendar.HOUR_OF_DAY));
    }

    private String obtenerHMP() {
        Calendar c = Calendar.getInstance();

        Calendar in = Calendar.getInstance();
        in.setTimeInMillis(citaActual.getFechaIni());
        if (c.get(Calendar.DAY_OF_MONTH) == in.get(Calendar.DAY_OF_MONTH) &&
                c.get(Calendar.MONTH) == in.get(Calendar.MONTH) &&
                c.get(Calendar.YEAR) == in.get(Calendar.YEAR)) {
            return "Hoy";
        } else if (manana(c, in)) {
            return "Mañana";
        } else if (pasado(c, in)) {
            return "Pasado Mañana";
        } else {
            //return citaActual.getDia() + "/" + obtenerMes(citaActual.getMes()) + "/" + citaActual.getAnio();
            return in.get(Calendar.DAY_OF_MONTH) + "/" + obtenerMes(in.get(Calendar.MONTH)) + "/" + in.get(Calendar.YEAR);
        }
    }

    private boolean pasado(Calendar c, Calendar in) {
        long dosDias = 86400000L;
        c.setTimeInMillis(c.getTimeInMillis() + dosDias);
        if (c.get(Calendar.DAY_OF_MONTH) == in.get(Calendar.DAY_OF_MONTH) &&
                c.get(Calendar.MONTH) == in.get(Calendar.MONTH) &&
                c.get(Calendar.YEAR) == in.get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else return false;
    }

    private boolean manana(Calendar c, Calendar in) {
        long unDia = 86400000L;
        c.setTimeInMillis(c.getTimeInMillis() + unDia);
        if (c.get(Calendar.DAY_OF_MONTH) == in.get(Calendar.DAY_OF_MONTH) &&
                c.get(Calendar.MONTH) == in.get(Calendar.MONTH) &&
                c.get(Calendar.YEAR) == in.get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else return false;
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
