package com.hextogen.daurm;

// for selecting rooms


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class rooms extends AppCompatActivity {

    FirebaseDatabase myDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    String lr301,lr302,lr303, Uid, Uid1,Uid2, Uid3;
    Button l301, l302, l303;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        l301 = findViewById(R.id.lr301);
        l302 = findViewById(R.id.lr302);
        l303 = findViewById(R.id.lr303);

        mAuth = FirebaseAuth.getInstance();
        Uid   = mAuth.getUid();
        myDatabase = FirebaseDatabase.getInstance();
        myRef      = myDatabase.getReference("room");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lr301 = dataSnapshot.child("301").child("state").getValue(String.class);
                Uid1  = dataSnapshot.child("301").child("id").getValue(String.class);

                lr302 = dataSnapshot.child("302").child("state").getValue(String.class);
                Uid2  = dataSnapshot.child("302").child("id").getValue(String.class);

                lr303 = dataSnapshot.child("303").child("state").getValue(String.class);
                Uid3  = dataSnapshot.child("302").child("id").getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

     /*   myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lr302 = dataSnapshot.child("302").child("state").getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lr303 = dataSnapshot.child("303").child("state").getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });  */

        l301.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Uid == Uid1){

                    Intent intent = new Intent(rooms.this, door1.class);
                    startActivity(intent);
                    finish();
                }
                else if(lr301 == "Unlocked"){

                    Toast.makeText(rooms.this, "This room is currently unavaible", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(rooms.this, door1.class);
                    startActivity(intent);

                }

            }
        });

        l302.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Uid == Uid2){

                    Intent intent = new Intent(rooms.this, door2.class);
                    startActivity(intent);
                    finish();
                }
                else if(lr302 == "Unlocked"){

                    Toast.makeText(rooms.this, "This room is currently unavaible", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(rooms.this, door2.class );
                    startActivity(intent);

                }

            }
        });

        l303.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Uid == Uid3){

                    Intent intent = new Intent(rooms.this, door3.class);
                    startActivity(intent);
                    finish();
                }
                else if(lr303 == "Unlocked"){

                    Toast.makeText(rooms.this, "This room is currently unavaible", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(rooms.this, door3.class);
                    startActivity(intent);

                }

            }
        });




    }


   /* public void goto301(View view) {

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
    } */

    public void goback(View view) {

            Intent intent = new Intent(rooms.this, teacher_panel.class);
            startActivity(intent);
            finish();
    }

    public String getId(String id) {
        return id;
    }
}
