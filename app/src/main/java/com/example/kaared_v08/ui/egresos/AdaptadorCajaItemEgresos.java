package com.example.kaared_v08.ui.egresos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Caja;

import java.util.ArrayList;
import java.util.Calendar;

public class AdaptadorCajaItemEgresos extends RecyclerView.Adapter<AdaptadorCajaItemEgresos.ExampleViewHolder> {
    private ArrayList<Caja> mListaCita;
    private AdaptadorCajaItemEgresos.OnItemClickListener mListener;

    Caja cajaActual;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdaptadorCajaItemEgresos.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDia, tvConcepto, tvHora, tvMonto;


        public ExampleViewHolder(View itemView, final AdaptadorCajaItemEgresos.OnItemClickListener listener) {
            super(itemView);
            tvDia = itemView.findViewById(R.id.tv_itemcaja_fecha);
            tvConcepto = itemView.findViewById(R.id.tv_itemcaja_concepto);
            tvHora = itemView.findViewById(R.id.tv_itemcaja_hora);
            tvMonto = itemView.findViewById(R.id.tv_itemcaja_monto);


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

    public AdaptadorCajaItemEgresos(ArrayList<Caja> listaCaja) {
        mListaCita = listaCaja;
    }


    @Override
    public AdaptadorCajaItemEgresos.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caja, parent, false);
        AdaptadorCajaItemEgresos.ExampleViewHolder evh = new AdaptadorCajaItemEgresos.ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(AdaptadorCajaItemEgresos.ExampleViewHolder holder, int position) {
        cajaActual = mListaCita.get(position);

        if (cajaActual.getDia() == 0) {
            holder.tvDia.setText("TOTAL");
            holder.tvMonto.setText("=$" + cajaActual.getMonto());
            holder.tvConcepto.setText("");
            holder.tvHora.setText("");
        } else {


            holder.tvDia.setText(obtenerHAAn());
            holder.tvMonto.setText("$" + cajaActual.getMonto());
            holder.tvConcepto.setText(cajaActual.getConcepto());
            holder.tvHora.setText(cajaActual.getHrs() + ":" + cajaActual.getMin() + " " + obtenerAMPM(cajaActual.getHrs()));
        }

    }

    private String obtenerHAAn() {
        Calendar c = Calendar.getInstance();
        if (c.get(Calendar.DAY_OF_MONTH) == cajaActual.getDia() &&
                c.get(Calendar.MONTH) == cajaActual.getMes() &&
                c.get(Calendar.YEAR) == cajaActual.getAnio()) {
            return "Hoy";
        } else if (c.get(Calendar.DAY_OF_MONTH) - 1 == cajaActual.getDia() &&
                c.get(Calendar.MONTH) == cajaActual.getMes() &&
                c.get(Calendar.YEAR) == cajaActual.getAnio()) {
            return "Ayer";
        } else if (c.get(Calendar.DAY_OF_MONTH) - 2 == cajaActual.getDia() &&
                c.get(Calendar.MONTH) == cajaActual.getMes() &&
                c.get(Calendar.YEAR) == cajaActual.getAnio()) {
            return "Antier";
        } else {
            return cajaActual.getDia() + "/" + obtenerMes(cajaActual.getMes()) + "/" + cajaActual.getAnio();
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
