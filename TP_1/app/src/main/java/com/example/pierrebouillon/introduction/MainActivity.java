package com.example.pierrebouillon.introduction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button helloButton = findViewById(R.id.but_hello) ;
        helloButton.setOnClickListener (v ->
                Toast.makeText(
                        MainActivity.this,
                        "Hello World !",
                        Toast.LENGTH_SHORT
                ).show()
        ) ;
    }
}
