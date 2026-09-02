package com.instituto.semana3_pedro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etDni, etNombres, etApellidos, etTelefono, etAsunto, etDescripcion;
    private Button btnRegistrar, btnIrABuscar;
    private ArrayList<Persona> listaPersonas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDni = findViewById(R.id.etDni);
        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        etTelefono = findViewById(R.id.etTelefono);
        etAsunto = findViewById(R.id.etAsunto);
        etDescripcion = findViewById(R.id.etDescripcion);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnIrABuscar = findViewById(R.id.btnIrABuscar);

        // Registrar la persona ingresada
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    Persona nuevaPersona = new Persona(
                            etDni.getText().toString().trim(),
                            etNombres.getText().toString().trim(),
                            etApellidos.getText().toString().trim(),
                            etTelefono.getText().toString().trim(),
                            etAsunto.getText().toString().trim(),
                            etDescripcion.getText().toString().trim()
                    );

                    listaPersonas.add(nuevaPersona);
                    Toast.makeText(MainActivity.this, "¡Consulta registrada correctamente!", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                }
            }
        });

        // Pasar la lista con los nuevos registrados a la pantalla de búsqueda
        btnIrABuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuscarActivity.class);
                intent.putExtra("LISTA_PERSONAS", listaPersonas);
                startActivity(intent);
            }
        });
    }

    private boolean validarCampos() {
        if (TextUtils.isEmpty(etDni.getText().toString().trim()) || etDni.getText().toString().trim().length() < 8) {
            etDni.setError("Ingrese un DNI válido de 8 dígitos");
            return false;
        }
        if (TextUtils.isEmpty(etNombres.getText().toString().trim())) {
            etNombres.setError("Ingrese los nombres");
            return false;
        }
        if (TextUtils.isEmpty(etApellidos.getText().toString().trim())) {
            etApellidos.setError("Ingrese los apellidos");
            return false;
        }
        if (TextUtils.isEmpty(etAsunto.getText().toString().trim())) {
            etAsunto.setError("Ingrese el asunto");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        etDni.setText("");
        etNombres.setText("");
        etApellidos.setText("");
        etTelefono.setText("");
        etAsunto.setText("");
        etDescripcion.setText("");
    }
}