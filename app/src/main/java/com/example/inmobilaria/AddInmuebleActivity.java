package com.example.inmobilaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inmobilaria.configuraciones.Constantes;
import com.example.inmobilaria.databinding.ActivityAddInmuebleBinding;
import com.example.inmobilaria.modelos.Inmueble;

public class AddInmuebleActivity extends AppCompatActivity {

    private ActivityAddInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAddInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inmueble inmueble = crearInmueble();
                if (inmueble != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.INMUEBLE, inmueble);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(AddInmuebleActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Inmueble crearInmueble() {
        if (binding.txtDireccionAddInmueble.getText().toString().isEmpty() ||
                binding.txtnNumeroAddInmueble.getText().toString().isEmpty() ||
                binding.txtCiudadAddInmueble.getText().toString().isEmpty() ||
                binding.txtnCodigoPostalAddInmueble.getText().toString().isEmpty() ||
                binding.txtProvinciaAddInmueble.getText().toString().isEmpty())
            return null;

        return new Inmueble(
                binding.txtDireccionAddInmueble.getText().toString(),
                Integer.parseInt(binding.txtnNumeroAddInmueble.getText().toString()),
                binding.txtCiudadAddInmueble.getText().toString(),
                binding.txtProvinciaAddInmueble.getText().toString(),
                binding.txtnCodigoPostalAddInmueble.getText().toString(),
                binding.rbAddInmueble.getRating());
    }
}