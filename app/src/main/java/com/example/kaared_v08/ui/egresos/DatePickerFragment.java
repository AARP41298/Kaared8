package com.example.kaared_v08.ui.egresos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.kaared_v08.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    int nomDia;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        //Obtenemos el dia de hoy
        //Toast.makeText(getContext(), c.get(Calendar.YEAR)+" "+c.get(Calendar.MONTH)+" "+c.get(Calendar.DAY_OF_MONTH)+"", Toast.LENGTH_SHORT).show();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        TextView tv1 = getActivity().findViewById(R.id.tv_egreso_fecha);
        //tv1.setText("Año: " + view.getYear() + " Month: " + view.getMonth() + " Day: " + view.getDayOfMonth());

        tv1.setText(view.getDayOfMonth() + "-" + obtenerMes(view.getMonth()) + "-" + view.getYear());


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
