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

public class VariosHijosNuevo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] state= {"Maternal A","Maternal B","Pre-Kinder 1 A","Pre-Kinder 1 B","Pre-Kinder 2 A","Pre-Kinder 2 B", "Pre-Kinder 3"};


    private Spinner primerHijoSeccionSpinner;
    private Spinner segundoHijoSeccionSpinner;
    private Spinner tercerHijoSeccionSpinner;
    private Spinner cuartoHijoSeccionSpinner;
    private EditText primerHijoNombreText;
    private EditText segundoHijoNombreText;
    private EditText tercerHijoNombreText;
    private EditText cuartoHijoNombreText;
    private EditText padreVariosText;
    private EditText correoVariosText;
    private EditText contraseñaVariosText;
    private String seccion1;
    private String seccion2;
    private String seccion3;
    private String seccion4;
    private Button entrarVariosButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varios_hijos_nuevo);

        padreVariosText = (EditText)findViewById(R.id.nombrePadreVariosText);
        correoVariosText = (EditText)findViewById(R.id.correoVariosText);
        contraseñaVariosText = (EditText)findViewById(R.id.contraseñaVariosText);
        primerHijoNombreText = (EditText)findViewById(R.id.primerHijoNombreText);
        segundoHijoNombreText = (EditText)findViewById(R.id.segundoHijoNombreText);
        tercerHijoNombreText = (EditText)findViewById(R.id.tercerHijoNombreText);
        cuartoHijoNombreText = (EditText)findViewById(R.id.cuartoHijoNombreText);
        primerHijoSeccionSpinner = (Spinner)findViewById(R.id.primerHijoSeccionSpinner);
        segundoHijoSeccionSpinner = (Spinner)findViewById(R.id.segundoHijoSeccionSpinner);
        tercerHijoSeccionSpinner= (Spinner)findViewById(R.id.tercerHijoSeccionSpinner);
        cuartoHijoSeccionSpinner = (Spinner)findViewById(R.id.cuartoHijoSeccionSpinner);
        entrarVariosButton = (Button)findViewById(R.id.entrarVariosButton);

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, state);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        primerHijoSeccionSpinner.setAdapter(adapter_state);
        primerHijoSeccionSpinner.setOnItemSelectedListener(this);

        /*ArrayAdapter<String> adapter_state2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, state2);
        adapter_state2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        segundoHijoSeccionSpinner.setAdapter(adapter_state);
        segundoHijoSeccionSpinner.setOnItemSelectedListener(this);

        /*ArrayAdapter<String> adapter_state3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, state3);
        adapter_state3
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        tercerHijoSeccionSpinner.setAdapter(adapter_state);
        tercerHijoSeccionSpinner.setOnItemSelectedListener(this);

        /*ArrayAdapter<String> adapter_state4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, state4);
        adapter_state4
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        cuartoHijoSeccionSpinner.setAdapter(adapter_state);
        cuartoHijoSeccionSpinner.setOnItemSelectedListener(this);


        entrarVariosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(correoVariosText.getText().toString());
                user.setPassword(contraseñaVariosText.getText().toString());
                user.put("nombrePapa", padreVariosText.getText().toString());
                user.put("hijo1", primerHijoNombreText.getText().toString());
                user.put("seccion1", seccion1);
                user.put("hijo2", segundoHijoNombreText.getText().toString());
                user.put("seccion2", seccion2);
                user.put("hijo3", tercerHijoNombreText.getText().toString());
                user.put("seccion3", seccion3);
                user.put("hijo4", cuartoHijoNombreText.getText().toString());
                user.put("seccion4", seccion4);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast.makeText(VariosHijosNuevo.this, "Usuario Creado Exitosamente", Toast.LENGTH_SHORT).show();
                            Intent goToMainScreen = new Intent(VariosHijosNuevo.this, HomeScreen.class);
                            startActivity(goToMainScreen);
                            finish();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Toast.makeText(VariosHijosNuevo.this, "Usuario NO ha sido Creado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

        switch(parent.getId()){
            case R.id.primerHijoSeccionSpinner:
                primerHijoSeccionSpinner.setSelection(position);

                String primerSeccion = (String) primerHijoSeccionSpinner.getSelectedItem();
                System.out.println(primerSeccion);
                switch (primerSeccion) {
                    case "Maternal A": seccion1 = "maternala";
                        break;
                    case "Maternal B": seccion1 = "maternalb";
                        break;
                    case "Pre-Kinder 1 A": seccion1 = "prekinder1a";
                        break;
                    case "Pre-Kinder 1 B": seccion1 = "prekinder1b";
                        break;
                    case "Pre-Kinder 2 A": seccion1 = "prekinder2a";
                        break;
                    case "Pre-Kinder 2 B": seccion1 = "prekinder2b";
                        break;
                    case "Pre-Kinder 3": seccion1 = "prekinder3";
                        break;
                    default: System.out.println("invalid Section");
                        break;
                }
                break;

            case R.id.segundoHijoSeccionSpinner:
                segundoHijoSeccionSpinner.setSelection(position);
                String segundaSeccion = (String) segundoHijoSeccionSpinner.getSelectedItem();
                System.out.println(segundaSeccion);
                switch (segundaSeccion) {
                    case "Maternal A": seccion2 = "maternala";
                        break;
                    case "Maternal B": seccion2 = "maternalb";
                        break;
                    case "Pre-Kinder 1 A": seccion2 = "prekinder1a";
                        break;
                    case "Pre-Kinder 1 B": seccion2 = "prekinder1b";
                        break;
                    case "Pre-Kinder 2 A": seccion2 = "prekinder2a";
                        break;
                    case "Pre-Kinder 2 B": seccion2 = "prekinder2b";
                        break;
                    case "Pre-Kinder 3": seccion2 = "prekinder3";
                        break;
                    default: System.out.println("invalid Section");
                        break;
                }
                break;

            case R.id.tercerHijoSeccionSpinner:
                tercerHijoSeccionSpinner.setSelection(position);
                String terceraSeccion = (String) tercerHijoSeccionSpinner.getSelectedItem();
                System.out.println(terceraSeccion);
                switch (terceraSeccion) {
                    case "Maternal A": seccion3 = "maternala";
                        break;
                    case "Maternal B": seccion3 = "maternalb";
                        break;
                    case "Pre-Kinder 1 A": seccion3 = "prekinder1a";
                        break;
                    case "Pre-Kinder 1 B": seccion3 = "prekinder1b";
                        break;
                    case "Pre-Kinder 2 A": seccion3 = "prekinder2a";
                        break;
                    case "Pre-Kinder 2 B": seccion3 = "prekinder2b";
                        break;
                    case "Pre-Kinder 3": seccion3 = "prekinder3";
                        break;
                    default: System.out.println("invalid Section");
                        break;
                }
                break;

            case R.id.cuartoHijoSeccionSpinner:
                cuartoHijoSeccionSpinner.setSelection(position);
                String cuartaSeccion = (String) cuartoHijoSeccionSpinner.getSelectedItem();
                System.out.println(cuartaSeccion);
                switch (cuartaSeccion) {
                    case "Maternal A": seccion4 = "maternala";
                        break;
                    case "Maternal B": seccion4 = "maternalb";
                        break;
                    case "Pre-Kinder 1 A": seccion4 = "prekinder1a";
                        break;
                    case "Pre-Kinder 1 B": seccion4 = "prekinder1b";
                        break;
                    case "Pre-Kinder 2 A": seccion4 = "prekinder2a";
                        break;
                    case "Pre-Kinder 2 B": seccion4 = "prekinder2b";
                        break;
                    case "Pre-Kinder 3": seccion4 = "prekinder3";
                        break;
                    default: System.out.println("invalid Section");
                        break;
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
