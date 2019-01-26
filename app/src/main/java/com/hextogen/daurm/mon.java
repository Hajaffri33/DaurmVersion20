package com.hextogen.daurm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mon extends AppCompatActivity {




    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;

    TextView t1,r1,b1, t2,r2,b2;
    String userid, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("timetable");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userid = user.getEmail();

        t1= findViewById(R.id.title1);
        r1= findViewById(R.id.room1);
        b1= findViewById(R.id.batch1);

        t2= findViewById(R.id.title2);
        r2= findViewById(R.id.room2);
        b2= findViewById(R.id.batch2);

        myRef.child("monday").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });










    }
}
