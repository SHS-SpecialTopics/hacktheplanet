package com.example.rayqu.timertest;

/**
 * Created by Owner on 2/21/2016.
 */
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView imageViewHexaGon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewHexaGon=(ImageView)findViewById(R.id.imageView_hexagon);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.hexagon);
        imageViewHexaGon.setImageBitmap(icon);
    }
}
