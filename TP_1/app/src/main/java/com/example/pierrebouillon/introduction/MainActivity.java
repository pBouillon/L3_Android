package com.example.pierrebouillon.introduction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static int MAX_COMPRESSION = 100 ;
    public static int PHOTO ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_main) ;

        Button helloButton   = findViewById(R.id.but_hello) ;
        Button pictureButton = findViewById(R.id.but_picture) ;
        Button pictureShowButton = findViewById(R.id.but_show_picture) ;
        Button stopwatchButton   = findViewById(R.id.but_stopwatch) ;

        helloButton.setOnClickListener (v -> {
            Log.i("MainActivity", "Hello toast requested") ;
            Toast.makeText(
                    MainActivity.this,
                    "Hello World !",
                    Toast.LENGTH_SHORT
            ).show() ;
        }) ;

        stopwatchButton.setOnClickListener( v -> {
            Log.i("MainActivity", "StopwatchActivity activity requested") ;
            startActivity(new Intent(MainActivity.this, StopwatchActivity.class)) ;
        });

        pictureButton.setOnClickListener(view -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE) ;
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(i, PHOTO) ;
            }
        });

        pictureShowButton.setOnClickListener( v-> {
            try {
                FileInputStream fis = openFileInput("image.data") ;
                Bitmap bm = BitmapFactory.decodeStream(fis) ;
                ImageView iv = findViewById(R.id.imageView) ;
                iv.setImageBitmap(bm) ;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras() ;
            Bitmap imageBitmap = (Bitmap) (extras != null ? extras.get("data")
                                                          : null) ;

            FileOutputStream fos = null;
            try {
                fos = openFileOutput("image.data", MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (imageBitmap != null) {
                imageBitmap.compress(Bitmap.CompressFormat.PNG, MAX_COMPRESSION, fos) ;
            }

            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
