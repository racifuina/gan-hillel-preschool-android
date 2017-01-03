package com.panatlanticdev.preschool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Ads extends AppCompatActivity {

    ImageView publiImage;
    List<ParseObject> objeto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        publiImage = (ImageView)findViewById(R.id.publiImage);

        ParseQuery query = new ParseQuery("Publicidad");
        query.whereEqualTo("titulo", "publicidad");
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
                        publiImage.setImageBitmap(bmp);
                    } else {
                        Log.d("test", "There was a problem downloading the data.");
                    }
                }
            });
        }


    }
}
