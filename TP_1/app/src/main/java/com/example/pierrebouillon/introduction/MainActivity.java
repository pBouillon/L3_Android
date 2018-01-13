package com.example.pierrebouillon.introduction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button stopwatchButton = findViewById(R.id.but_stopwatch) ;
        Button helloButton     = findViewById(R.id.but_hello) ;

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
    }
}
