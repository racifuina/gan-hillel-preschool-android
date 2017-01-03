package com.panatlanticdev.preschool;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class CalendarioSeccion extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private TouchImageView calendarioImagen;
    List<ParseObject> objeto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_seccion);

        UserInfo user = (UserInfo)getApplication();
        String Seccion = user.getSeccionActual();
        calendarioImagen = (TouchImageView)findViewById(R.id.calendarioImage);

        progressDialog = ProgressDialog.show(CalendarioSeccion.this, "", "Descargando Imagen...", true);




        ParseQuery query = new ParseQuery("Calendarios");

        query.whereEqualTo("seccion", Seccion);
        try {
            objeto = query.find();
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }


        for (ParseObject info : objeto) {
            ParseFile fileObject = (ParseFile) info.get("imagen");
            fileObject.getDataInBackground(new GetDataCallback() {
                public void done(byte[] data,
                                 ParseException e) {
                    if (e == null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        calendarioImagen.setImageBitmap(bmp);
                        progressDialog.dismiss();
                    } else {
                        Log.d("test", "There was a problem downloading the data.");
                    }
                }
            });


        }
    }

}

