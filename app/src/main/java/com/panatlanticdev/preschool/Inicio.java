package com.panatlanticdev.preschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class Inicio extends AppCompatActivity {

    //Connecting with Objects
    protected EditText mCodigoAccesoTextField;
    protected Button mNuevoUsuarioButton;
    protected Button mUsuarioExistenteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        ParseUser currentUser = ParseUser.getCurrentUser();

        //currentUser.logOut();
        if (currentUser != null) {
            // do stuff with the user
            UserInfo user = (UserInfo)getApplication();
            user.setSeccionActual(currentUser.getString("seccion1"));
            Intent goToMainScreen = new Intent(Inicio.this, HomeScreen.class);
            startActivity(goToMainScreen);
            finish();

        } else {
            // show the signup or login screen
        }

        //iniciando objetos
        mCodigoAccesoTextField = (EditText)findViewById(R.id.codigoAcessoText);
        mNuevoUsuarioButton = (Button)findViewById(R.id.nuevoUsuarioButton);
        mUsuarioExistenteButton = (Button)findViewById(R.id.usuarioExistenteButton);


        //Confirmando el Código de Acceso en Nuevo Usuario

        mNuevoUsuarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCodigoAccesoTextField.getText().toString().equals("2756")) {
                    Toast.makeText(Inicio.this, "Código Correcto", Toast.LENGTH_LONG).show();
                    Intent goToSeleccion = new Intent(Inicio.this, Seleccion.class);
                    startActivity(goToSeleccion);
                    finish();
                } else {
                    Toast.makeText(Inicio.this, "Código Incorrecto", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Confirmando el código de Acesso en Usuario Existente

        mUsuarioExistenteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCodigoAccesoTextField.getText().toString().equals("2756")) {
                    Toast.makeText(Inicio.this, "Código Correcto", Toast.LENGTH_LONG).show();
                    Intent goToExistente = new Intent(Inicio.this, Existente.class);
                    startActivity(goToExistente);
                    finish();
                } else {
                    Toast.makeText(Inicio.this, "Código Incorrecto", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
