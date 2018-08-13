package com.hextogen.daurm;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ongoing extends AppCompatActivity {
    private TextView timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ongoing);
        timer=findViewById(R.id.timer);
           timer.setText("1 Minute Left");


    }
}