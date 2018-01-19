package com.example.pierrebouillon.basicgallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonExit = findViewById(R.id.butExit) ;
        Button buttonTake = findViewById(R.id.butTake) ;

        buttonExit.setOnClickListener( v -> {
            finish() ;
        }) ;

        buttonTake.setOnClickListener( v -> {

        });
    }
}
