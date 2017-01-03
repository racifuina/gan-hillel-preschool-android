package com.panatlanticdev.preschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class Perfil extends AppCompatActivity {

    private Button cerrarSesionButton;
    private TextView perfilNombrePapaLabel;
    private TextView perfilCodigoLabel;
    private TextView primerHijoPerfilLabel;
    private TextView primerHijoSeccionLabel;
    private Button primerPerfilButton;
    private TextView segundoHijoPerfilLabel;
    private TextView segundoHijoSeccionLabel;
    private Button segundoPerfilButton;
    private TextView tercerHijoPerfilLabel;
    private TextView tercerHijoSeccionLabel;
    private Button tercerPerfilButton;
    private TextView cuartoHijoPerfilLabel;
    private TextView cuartoHijoSeccionLabel;
    private Button cuartoPerfilButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        final ParseUser currentUser = ParseUser.getCurrentUser();

        //Initializing Variables.
        perfilNombrePapaLabel = (TextView)findViewById(R.id.perfilNombrePapaLabel);
        perfilCodigoLabel = (TextView)findViewById(R.id.perfilCodigoLabel);
        primerHijoPerfilLabel = (TextView)findViewById(R.id.primerHijoPerfilLabel);
        primerHijoSeccionLabel = (TextView)findViewById(R.id.primerHijoSeccionLabel);
        primerPerfilButton = (Button)findViewById(R.id.primerPerfilButton);
        segundoHijoPerfilLabel = (TextView)findViewById(R.id.segundoHijoPerfilLabel);
        segundoHijoSeccionLabel = (TextView)findViewById(R.id.segundoHijoSeccionLabel);
        segundoPerfilButton = (Button)findViewById(R.id.segundoPerfilButton);
        tercerHijoPerfilLabel = (TextView)findViewById(R.id.tercerHijoPerfilLabel);
        tercerHijoSeccionLabel = (TextView)findViewById(R.id.tercerHijoSeccionLabel);
        tercerPerfilButton = (Button)findViewById(R.id.tercerPerfilButton);
        cuartoHijoPerfilLabel = (TextView)findViewById(R.id.cuartoHijoPerfilLabel);
        cuartoHijoSeccionLabel = (TextView)findViewById(R.id.cuartoHijoSeccionLabel);
        cuartoPerfilButton = (Button)findViewById(R.id.cuartoPerfilButton);
        cerrarSesionButton = (Button)findViewById(R.id.cerrarSesionButton);

        // MARK - Agregando Info a Labels.
        perfilNombrePapaLabel.setText(currentUser.getString("nombrePapa"));
        perfilCodigoLabel.setText(currentUser.getString("correlativo"));
        primerHijoPerfilLabel.setText(currentUser.getString("hijo1"));
        primerHijoSeccionLabel.setText(currentUser.getString("seccion1"));
        segundoHijoPerfilLabel.setText(currentUser.getString("hijo2"));
        segundoHijoSeccionLabel.setText(currentUser.getString("seccion2"));
        tercerHijoPerfilLabel.setText(currentUser.getString("hijo3"));
        tercerHijoSeccionLabel.setText(currentUser.getString("seccion3"));
        cuartoHijoPerfilLabel.setText(currentUser.getString("hijo4"));
        cuartoHijoSeccionLabel.setText(currentUser.getString("seccion4"));
        if (segundoHijoPerfilLabel.getText().equals("")){
            segundoPerfilButton.setVisibility(View.INVISIBLE);
        }
        if (tercerHijoPerfilLabel.getText().equals("")){
            tercerPerfilButton.setVisibility(View.INVISIBLE);
        }
        if (cuartoHijoPerfilLabel.getText().equals("")){
            cuartoPerfilButton.setVisibility(View.INVISIBLE);
        }

        cerrarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.logOut();
                Intent goToInicio = new Intent(Perfil.this, Inicio.class);
                startActivity(goToInicio);
            }
        });

        primerPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo user = (UserInfo) getApplication();
                user.setSeccionActual(currentUser.getString("seccion1"));
                Toast.makeText(Perfil.this, "Perfil 1 Seleccionado", Toast.LENGTH_SHORT).show();
            }
        });

        segundoPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo user = (UserInfo) getApplication();
                user.setSeccionActual(currentUser.getString("seccion2"));
                Toast.makeText(Perfil.this, "Perfil 2 Seleccionado", Toast.LENGTH_SHORT).show();
            }
        });

        tercerPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo user = (UserInfo) getApplication();
                user.setSeccionActual(currentUser.getString("seccion3"));
                Toast.makeText(Perfil.this, "Perfil 3 Seleccionado", Toast.LENGTH_SHORT).show();
            }
        });

        cuartoPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo user = (UserInfo) getApplication();
                user.setSeccionActual(currentUser.getString("seccion4"));
                Toast.makeText(Perfil.this, "Perfil 4 Seleccionado", Toast.LENGTH_SHORT).show();
            }
        });


    }





}
