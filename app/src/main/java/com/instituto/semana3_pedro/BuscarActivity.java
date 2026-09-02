package com.instituto.semana3_pedro;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;

public class BuscarActivity extends AppCompatActivity {
    private TextInputEditText etBuscarDni;
    private Button btnBuscarConsulta, btnVolver;
    private TextView tvResultadoInfo;
    private ArrayList<Persona> listaPersonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        etBuscarDni = findViewById(R.id.etBuscarDni);
        btnBuscarConsulta = findViewById(R.id.btnBuscarConsulta);
        btnVolver = findViewById(R.id.btnVolver);
        tvResultadoInfo = findViewById(R.id.tvResultadoInfo);

        // Obtener la lista enviada desde MainActivity
        listaPersonas = (ArrayList<Persona>) getIntent().getSerializableExtra("LISTA_PERSONAS");
        if (listaPersonas == null) {
            listaPersonas = new ArrayList<>();
        }
        btnBuscarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniBuscado = etBuscarDni.getText().toString().trim();

                if (TextUtils.isEmpty(dniBuscado) || dniBuscado.length() < 8) {
                    Toast.makeText(BuscarActivity.this, "Por favor ingrese un DNI válido de 8 dígitos", Toast.LENGTH_SHORT).show();
                    return;
                }
                Persona personaEncontrada = null;
                for (Persona p : listaPersonas) {
                    if (p.getDni().equals(dniBuscado)) {
                        personaEncontrada = p;
                        break;
                    }
                }
                if (personaEncontrada != null) {
                    String resultado = "👤 Ciudadano: " + personaEncontrada.getNombres() + " " + personaEncontrada.getApellidos() + "\n" +
                            "🆔 DNI: " + personaEncontrada.getDni() + "\n" +
                            "📞 Teléfono: " + personaEncontrada.getTelefono() + "\n\n" +
                            "📌 Asunto: " + personaEncontrada.getAsunto() + "\n" +
                            "📝 Detalle: " + personaEncontrada.getDescripcion();

                    tvResultadoInfo.setText(resultado);
                    tvResultadoInfo.setTextColor(getResources().getColor(android.R.color.white));
                } else {
                    tvResultadoInfo.setText("❌ No se encontró ningún ciudadano registrado con el DNI: " + dniBuscado);
                    tvResultadoInfo.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}