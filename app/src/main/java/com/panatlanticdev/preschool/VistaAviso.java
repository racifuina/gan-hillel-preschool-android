package com.panatlanticdev.preschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class VistaAviso extends AppCompatActivity {

    TextView tituloAviso;
    TextView infoAviso;
    String name;
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_aviso);

        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();

        // Get the name
        name = i.getStringExtra("tituloAviso");
        info = i.getStringExtra("infoAviso");

        // Locate the TextView in singleitemview.xml
        tituloAviso = (TextView) findViewById(R.id.tituloAviso);
        infoAviso = (TextView)findViewById(R.id.infoAviso);


        // Load the text into the TextView
        tituloAviso.setText(name);
        infoAviso.setText(info);
    }
}
