package com.example.kaared_v08.ui.editar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kaared_v08.R;


public class EditarFragment extends Fragment {

TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cargarEventos();
        cargarControles();
    }

    private void cargarControles() {
        textView.setText("Este es el fragment editar");
    }

    private void cargarEventos() {
        textView=getActivity().findViewById(R.id.tv_edfrag);
    }
}
