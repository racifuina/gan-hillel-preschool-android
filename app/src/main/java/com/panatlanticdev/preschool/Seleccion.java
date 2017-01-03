package com.panatlanticdev.preschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Seleccion extends AppCompatActivity {

    protected Button unHijoButton;
    protected Button variosHijosButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        unHijoButton = (Button)findViewById(R.id.unHijoButton);
        variosHijosButton = (Button)findViewById(R.id.variosHijosButton);

        unHijoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNuevoUsuario = new Intent(Seleccion.this, NuevoUsuario.class);
                startActivity(goToNuevoUsuario);
            }
        });

        variosHijosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToVariosHijosNuevo = new Intent(Seleccion.this, VariosHijosNuevo.class);
                startActivity(goToVariosHijosNuevo);
            }
        });
    }
}
