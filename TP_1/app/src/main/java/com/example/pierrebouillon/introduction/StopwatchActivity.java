package com.example.pierrebouillon.introduction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class StopwatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        Toast.makeText(
                StopwatchActivity.this,
                "Stopwatch enable",
                Toast.LENGTH_SHORT
        ).show() ;

        Button startButton = findViewById(R.id.but_stopwatch_start) ;
        Button stopButton  = findViewById(R.id.but_stopwatch_stop) ;

        stopButton.setEnabled(false) ;
        final long[] begin = {0};

        startButton.setOnClickListener(v -> {
            toggle(startButton, stopButton) ;
            begin[0] = GregorianCalendar.getInstance().getTimeInMillis() ;
        });

        stopButton.setOnClickListener(v -> {
            toggle(startButton, stopButton) ;
            String elapsed = "Time elapsed: " + getElapsed(begin[0]) ;
            Toast.makeText(StopwatchActivity.this, elapsed, Toast.LENGTH_LONG).show() ;
        });
    }

    private void toggle(Button start, Button stop) {
        start.setEnabled(!start.isEnabled()) ;
        stop.setEnabled(!start.isEnabled()) ;
    }

    private int getElapsed(long timestamp) {
        long now    = GregorianCalendar.getInstance().getTimeInMillis() ;
        return (int) (now - timestamp) / 1000 ;
    }
}
