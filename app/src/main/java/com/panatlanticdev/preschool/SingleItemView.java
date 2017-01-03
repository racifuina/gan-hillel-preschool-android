package com.panatlanticdev.preschool;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class SingleItemView extends AppCompatActivity {

    TextView txtname;
    String name;
    TextView fechaEventoText;
    TextView infoEventoText;
    ImageView imagenEvento;
    List<ParseObject> objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);
        Intent i = getIntent();
        fechaEventoText = (TextView)findViewById(R.id.fechaEventoText);
        infoEventoText = (TextView)findViewById(R.id.infoEventoText);
        imagenEvento = (ImageView)findViewById(R.id.imagenEvento);
        txtname = (TextView) findViewById(R.id.name);
        // Get the name and Load the Title into the TextView
        name = i.getStringExtra("titulo");
        txtname.setText(name);

        ParseQuery query = new ParseQuery("Eventos");
        query.whereEqualTo("titulo", name);
        try {
            objeto = query.find();
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }


        for (ParseObject info : objeto) {
            fechaEventoText.setText(info.getString("fechaString"));
            infoEventoText.setText(info.getString("infoCompleta"));

            ParseFile fileObject = (ParseFile) info.get("imagenEvento");
            fileObject.getDataInBackground(new GetDataCallback() {
                public void done(byte[] data,
                                 ParseException e) {
                    if (e == null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        imagenEvento.setImageBitmap(bmp);
                    } else {
                        Log.d("test", "There was a problem downloading the data.");
                    }
                }
            });
        }
    }
}
