package com.hextogen.daurm;

// for selecting rooms


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class rooms extends AppCompatActivity {

    FirebaseDatabase myDatabase;
    DatabaseReference myRef;
    String lr301,lr302,lr303;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        myDatabase = FirebaseDatabase.getInstance();
        myRef      = myDatabase.getReference("room");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lr301 = dataSnapshot.child("301").child("state").getValue(String.class);
                lr302 = dataSnapshot.child("302").child("state").getValue(String.class);
                lr303 = dataSnapshot.child("303").child("state").getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void goto301(View view) {

        if(lr301 == "Unlocked"){

            Toast.makeText(this,"This room is currently unavailable", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(rooms.this, door1.class);
            startActivity(intent);
        }

    }

    public void goto302(View view) {

        if(lr302 == "Unlocked"){

            Toast.makeText(this,"This room is currently unavailable", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(rooms.this, door2.class);
            startActivity(intent);
        }
    }

    public void goto303(View view) {

        if(lr303 == "Unlocked"){

            Toast.makeText(this,"This room is currently unavailable", Toast.LENGTH_SHORT).show();
        }
        else{
        Intent intent = new Intent(rooms.this, door3.class);
        startActivity(intent);
        }
    }

    public void goback(View view) {

            Intent intent = new Intent(rooms.this, teacher_panel.class);
            startActivity(intent);
            finish();
    }

    public String getId(String id) {
        return id;
    }
}
