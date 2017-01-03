package com.panatlanticdev.preschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {



    private Toolbar toolbar;
    private Button perfilButton;
    private Button actividadesButton;
    private Button avisosButton;
    private Button calendarioSeccion;
    private Button mensajesButton;
    boolean firstTime = true;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        if (firstTime == true) {
            Intent goToAds = new Intent(HomeScreen.this, Ads.class);
            startActivity(goToAds);
            firstTime = false;
        }






        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        perfilButton = (Button)findViewById(R.id.perfilButton);
        actividadesButton = (Button)findViewById(R.id.actividadesButton);
        avisosButton = (Button)findViewById(R.id.avisosButton);
        calendarioSeccion = (Button)findViewById(R.id.calendarioSeccionButton);
        mensajesButton = (Button)findViewById(R.id.mensajesButton);

        perfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToPerfil = new Intent(HomeScreen.this, Perfil.class);
                startActivity(goToPerfil);
            }
        });


        actividadesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActividades = new Intent(HomeScreen.this, ListadoEventos.class);
                startActivity(goToActividades);
            }
        });

        avisosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAvisos = new Intent(HomeScreen.this, Avisos.class);
                startActivity(goToAvisos);

            }
        });
        calendarioSeccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCalendarios = new Intent(HomeScreen.this, CalendarioSeccion.class);
                startActivity(goToCalendarios);
            }
        });


        mensajesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToChat = new Intent(HomeScreen.this, Chat.class);
                startActivity(goToChat);
            }
        });
    }



}
