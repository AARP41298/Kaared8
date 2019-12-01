package com.example.kaared_v08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    Button btnRegistrarse;
    EditText etCorreo, etContra, etContraConfir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        cargarControles();
        cargarEventos();

    }

    private void cargarEventos() {
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    private void cargarControles() {
        btnRegistrarse = findViewById(R.id.btn_ar_regis);
        etCorreo = findViewById(R.id.et_ar_correo);
        etContra = findViewById(R.id.et_ar_contra);
        etContraConfir = findViewById(R.id.et_ar_contra_conf);

    }

    private void registrar() {
        if (validaTexto()) {
            Intent intent = new Intent(RegistroActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean validaTexto() {
        boolean valido = true;

        if (TextUtils.isEmpty(etCorreo.getText())) {
            valido = false;
            etCorreo.setError(String.format("Ingresa tu %1s", "correo"));
            Toast.makeText(RegistroActivity.this, "Faltan agregar campos", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(etContra.getText())) {
            valido = false;
            etContra.setError(String.format("Ingresa tu %1s", "contraseña"));
            Toast.makeText(RegistroActivity.this, "Faltan agregar campos", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(etContraConfir.getText())) {
            valido = false;
            etContraConfir.setError(String.format("Confirma tu %1s", "contraseña"));
            Toast.makeText(RegistroActivity.this, "Faltan agregar campos", Toast.LENGTH_SHORT).show();
        }
        if (!etContra.getText().toString().equals(etContraConfir.getText().toString())) {
            valido = false;

            String con = etContra.getText().toString();
            String conConf = etContraConfir.getText().toString();

            etContra.setError(String.format("La contraseña no coincide"));
            etContraConfir.setError(String.format("La contraseña no coincide"));
        }
        return valido;
    }
}
