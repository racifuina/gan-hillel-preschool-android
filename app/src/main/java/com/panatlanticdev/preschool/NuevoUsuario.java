package com.panatlanticdev.preschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class NuevoUsuario extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText correoTextField;
    private EditText contraseñaTextField;
    private EditText nombrePapaTextField;
    private EditText nombreHijoTextField;
    private Button signUpButton;
    private Spinner seccionesSpinner;
    private String[] state= {"Maternal A","Maternal B","Pre-Kinder 1 A","Pre-Kinder 1 B","Pre-Kinder 2 A","Pre-Kinder 2 B ", "Pre-Kinder 3"};
    String seccionSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        //Parse.initialize(this, "YwvDcEU3CPCHqxqs3uxfXbhiyvXX1aBf3zTOY730", "kzftvMchwaserexcLFZv3TPpUqbyLtWvZbZCNo3O");
        //Init vars
        correoTextField = (EditText)findViewById(R.id.emailSignUpTextField);
        contraseñaTextField = (EditText)findViewById(R.id.passwordSignUpTextField);
        nombrePapaTextField = (EditText)findViewById(R.id.nombrePadreSignUpTextField);
        nombreHijoTextField = (EditText)findViewById(R.id.nombreAlumnoSignUpTextField);
        signUpButton = (Button)findViewById(R.id.entrarSignUpButton);



        seccionesSpinner  = (Spinner) findViewById(R.id.seccionSpinner);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, state);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seccionesSpinner.setAdapter(adapter_state);
        seccionesSpinner.setOnItemSelectedListener(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(correoTextField.getText().toString());
                user.setPassword(contraseñaTextField.getText().toString());
                user.put("nombrePapa", nombrePapaTextField.getText().toString());
                user.put("hijo1", nombreHijoTextField.getText().toString());
                user.put("seccion1", seccionSeleccionada);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast.makeText(NuevoUsuario.this, "Usuario Creado Exitosamente", Toast.LENGTH_LONG).show();
                            Intent goToMainScreen = new Intent(NuevoUsuario.this, HomeScreen.class);
                            startActivity(goToMainScreen);
                            finish();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Toast.makeText(NuevoUsuario.this, "Usuario NO ha sido Creado", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        seccionesSpinner.setSelection(position);
        String selState = (String) seccionesSpinner.getSelectedItem();
        System.out.println(selState);
        String ChatSeleccionado;
        switch (selState) {
            case "Maternal A": seccionSeleccionada = "maternala";
                break;
            case "Maternal B": seccionSeleccionada = "maternalb";
                break;
            case "Pre-Kinder 1 A": seccionSeleccionada = "prekinder1a";
                break;
            case "Pre-Kinder 1 B": seccionSeleccionada = "prekinder1b";
                break;
            case "Pre-Kinder 2 A": seccionSeleccionada = "prekinder2a";
                break;
            case "Pre-Kinder 2 B": seccionSeleccionada = "prekinder2b";
                break;
            case "Pre-Kinder 3": seccionSeleccionada = "prekinder3";
                break;
            default: System.out.println("invalid Section");
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


