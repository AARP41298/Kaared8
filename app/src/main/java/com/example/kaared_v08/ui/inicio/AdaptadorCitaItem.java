package com.example.kaared_v08.ui.inicio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.citas;

import java.util.ArrayList;

public class AdaptadorCitaItem extends RecyclerView.Adapter<AdaptadorCitaItem.ExampleViewHolder> {

    private ArrayList<citas> mListaCita;
    private OnItemClickListener mListener;

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
            tvDia = itemView.findViewById(R.id.tv_ci_dia);
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

    public AdaptadorCitaItem(ArrayList<citas> listaCancion) {
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
        citas citaActual = mListaCita.get(position);
        holder.tvDia.setText(citaActual.getFecha());
        holder.tvServicio.setText(citaActual.getServicio());
        holder.tvHora.setText(citaActual.getHora());
        holder.tvNombre.setText(citaActual.getNombre());


    }

    @Override
    public int getItemCount() {
        return mListaCita.size();
    }
}

