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

        if (cajaActual.getFecha() == 0L) {
            holder.tvDia.setText("TOTAL");
            holder.tvMonto.setText("=$" + cajaActual.getMonto());
            holder.tvConcepto.setText("");
            holder.tvHora.setText("");
        } else {


            holder.tvDia.setText(obtenerHAAn());
            holder.tvMonto.setText("$" + cajaActual.getMonto());
            holder.tvConcepto.setText(cajaActual.getConcepto());
            holder.tvHora.setText(obtenerHora());
        }


    }

    private String obtenerHora() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(cajaActual.getFecha());
        return c.get(Calendar.HOUR) + ":" + obtenerMin(c.get(Calendar.MINUTE)) + " " + obtenerAMPM(c.get(Calendar.HOUR_OF_DAY));
    }
    private String obtenerMin(int min) {
        if (min<10){
            return "0"+min;
        } else return min+"";
    }

    private String obtenerHAAn() {

        Calendar c = Calendar.getInstance();

        Calendar in = Calendar.getInstance();
        in.setTimeInMillis(cajaActual.getFecha());
        if (c.get(Calendar.DAY_OF_MONTH) == in.get(Calendar.DAY_OF_MONTH) &&
                c.get(Calendar.MONTH) == in.get(Calendar.MONTH) &&
                c.get(Calendar.YEAR) == in.get(Calendar.YEAR)) {
            return "Hoy";
        } else if (ayer(c, in)) {
            return "Ayer";
        } else if (antier(c, in)) {
            return "Antier";
        } else {
            //return citaActual.getDia() + "/" + obtenerMes(citaActual.getMes()) + "/" + citaActual.getAnio();
            return in.get(Calendar.DAY_OF_MONTH) + "/" + obtenerMes(in.get(Calendar.MONTH)) + "/" + in.get(Calendar.YEAR);
        }
    }

    private boolean antier(Calendar c, Calendar in) {
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 2);
        if (c.get(Calendar.DAY_OF_MONTH) == in.get(Calendar.DAY_OF_MONTH) &&
                c.get(Calendar.MONTH) == in.get(Calendar.MONTH) &&
                c.get(Calendar.YEAR) == in.get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else return false;
    }

    private boolean ayer(Calendar c, Calendar in) {
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
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
