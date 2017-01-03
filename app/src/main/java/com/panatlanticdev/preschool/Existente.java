package com.panatlanticdev.preschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;


public class Existente extends AppCompatActivity {

    private EditText emailTextField;
    private EditText passwordTextField;
    private Button entrarButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existente);



        emailTextField =  (EditText)findViewById(R.id.emailSignUpTextField);
        passwordTextField = (EditText)findViewById(R.id.passwordLoginTextField);
        entrarButton = (Button)findViewById(R.id.entrarLoginButton);

        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LOGIN TO PARSE
                ParseUser.logInInBackground(emailTextField.getText().toString(), passwordTextField.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            /* Hooray! The user is logged in. */
                            System.out.println("Logged In");
                            Toast.makeText(Existente.this, "Inicio de sesión Exitosa", Toast.LENGTH_LONG).show();
                            Intent goToMainScreen = new Intent(Existente.this, HomeScreen.class);
                            startActivity(goToMainScreen);
                            finish();

                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            Toast.makeText(Existente.this, "Inicio de sesión Fallida", Toast.LENGTH_LONG).show();
                        }
                    }


                });


            }
        });





    }
}
