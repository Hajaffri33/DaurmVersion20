package com.hextogen.daurm;

// for selecting rooms


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class rooms extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);



    }


    public void goto301(View view) {
        Intent intent = new Intent(rooms.this, door1.class);
        startActivity(intent);

    }

    public void goto302(View view) {
        Intent intent = new Intent(rooms.this, door2.class);
        startActivity(intent);
    }

    public void goto303(View view) {
        Intent intent = new Intent(rooms.this, door3.class);
        startActivity(intent);
    }

    public void goback(View view) {
        Intent intent = new Intent(rooms.this, teacher_panel.class);
        startActivity(intent);
        finish();
    }
}
