package com.example.kaared_v08.ui.resumen;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kaared_v08.R;
import com.example.kaared_v08.entidad.Caja;

import java.util.ArrayList;
import java.util.Calendar;

public class AdaptadorCajaItemResumen extends RecyclerView.Adapter<AdaptadorCajaItemResumen.ExampleViewHolder> {
    private ArrayList<Caja> mListaCita;
    private AdaptadorCajaItemResumen.OnItemClickListener mListener;

    Caja cajaActual;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdaptadorCajaItemResumen.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDiaIn, tvConceptoIn, tvHoraIn, tvMontoIn;
        public TextView tvDiaOut, tvConceptoOut, tvHoraOut, tvMontoOut;


        public ExampleViewHolder(View itemView, final AdaptadorCajaItemResumen.OnItemClickListener listener) {
            super(itemView);
            tvDiaIn = itemView.findViewById(R.id.tv_itemresumen_fecha_in);
            tvConceptoIn = itemView.findViewById(R.id.tv_itemresumen_concepto_in);
            tvHoraIn = itemView.findViewById(R.id.tv_itemresumen_hora_in);
            tvMontoIn = itemView.findViewById(R.id.tv_itemresumen_monto_in);

            tvDiaOut = itemView.findViewById(R.id.tv_itemresumen_fecha_out);
            tvConceptoOut = itemView.findViewById(R.id.tv_itemresumen_concepto_out);
            tvHoraOut = itemView.findViewById(R.id.tv_itemresumen_hora_out);
            tvMontoOut = itemView.findViewById(R.id.tv_itemresumen_monto_out);


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

    public AdaptadorCajaItemResumen(ArrayList<Caja> listaCaja) {
        mListaCita = listaCaja;
    }


    @Override
    public AdaptadorCajaItemResumen.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resumen, parent, false);
        AdaptadorCajaItemResumen.ExampleViewHolder evh = new AdaptadorCajaItemResumen.ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(AdaptadorCajaItemResumen.ExampleViewHolder holder, int position) {
        cajaActual = mListaCita.get(position);

        if (cajaActual.getFecha() == 0L && cajaActual.getMonto() < 0) {
            holder.tvDiaOut.setText("TOTAL");
            holder.tvMontoOut.setText("=$" + cajaActual.getMonto());
            holder.tvMontoOut.setTextColor(Color.RED);
            holder.tvConceptoOut.setText("");
            holder.tvHoraOut.setText("");

            holder.tvDiaIn.setText("");
            holder.tvMontoIn.setText("");
            holder.tvConceptoIn.setText("");
            holder.tvHoraIn.setText("");
        } else if (cajaActual.getFecha() == 0L && 0 <= cajaActual.getMonto()) {
            holder.tvDiaIn.setText("TOTAL");
            holder.tvMontoIn.setText("=$" + cajaActual.getMonto());
            holder.tvMontoIn.setTextColor(Color.GREEN);
            holder.tvConceptoIn.setText("");
            holder.tvHoraIn.setText("");

            holder.tvDiaOut.setText("");
            holder.tvMontoOut.setText("");
            holder.tvConceptoOut.setText("");
            holder.tvHoraOut.setText("");
        } else if (cajaActual.getMonto() < 0) {
            holder.tvDiaOut.setText(obtenerHAAn());
            holder.tvMontoOut.setText("$" + cajaActual.getMonto());
            holder.tvConceptoOut.setText(cajaActual.getConcepto());
            holder.tvHoraOut.setText(obtenerHora());

            holder.tvDiaIn.setText("");
            holder.tvMontoIn.setText("");
            holder.tvConceptoIn.setText("");
            holder.tvHoraIn.setText("");
        } else {
            holder.tvDiaIn.setText(obtenerHAAn());
            holder.tvMontoIn.setText("$" + cajaActual.getMonto());
            holder.tvConceptoIn.setText(cajaActual.getConcepto());
            holder.tvHoraIn.setText(obtenerHora());

            holder.tvDiaOut.setText("");
            holder.tvMontoOut.setText("");
            holder.tvConceptoOut.setText("");
            holder.tvHoraOut.setText("");
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
        long dosDias = 86400000L;
        c.setTimeInMillis(c.getTimeInMillis() - dosDias);
        if (c.get(Calendar.DAY_OF_MONTH) == in.get(Calendar.DAY_OF_MONTH) &&
                c.get(Calendar.MONTH) == in.get(Calendar.MONTH) &&
                c.get(Calendar.YEAR) == in.get(Calendar.YEAR)) {
            return true;
        } else return false;
    }

    private boolean ayer(Calendar c, Calendar in) {
        long unDia = 86400000L;
        c.setTimeInMillis(c.getTimeInMillis() - unDia);
        if (c.get(Calendar.DAY_OF_MONTH) == in.get(Calendar.DAY_OF_MONTH) &&
                c.get(Calendar.MONTH) == in.get(Calendar.MONTH) &&
                c.get(Calendar.YEAR) == in.get(Calendar.YEAR)) {
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
