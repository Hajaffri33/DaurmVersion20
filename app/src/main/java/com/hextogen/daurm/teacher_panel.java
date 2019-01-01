package com.hextogen.daurm;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class teacher_panel extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String userID;
    TextView t_panel;
    Button signout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);

        t_panel = findViewById(R.id.teacher_panel);
        signout = findViewById(R.id.panel_signout);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Get", "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d("sign out", "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }

            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(teacher_panel.this, MainActivity.class));
                finish();

            }
        });


    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            teacher_db_values tinfo = new teacher_db_values();
            tinfo.setT_name(ds.child(userID).getValue(teacher_db_values.class).getT_name()); //set the name
            tinfo.setEmail(ds.child(userID).getValue(teacher_db_values.class).getEmail()); //set the email

            //display all the information
            Log.d("Name", "showData: name: " + tinfo.getT_name());
            Log.d("Ëmail", "showData: email: " + tinfo.getEmail());

            t_panel.setText(tinfo.getT_name());

        }
    }


    public  void update(View view){

        startActivity(new Intent(teacher_panel.this, teacher_info.class));
        finish();
        onDestroy();
    }

    public void gotoLock(View view) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String mail1 = "asifkhan@uogsialkot.edu.pk";
        String mail2 = "ad@uogsialkot.edu.pk";

        String email = user.getEmail();


        if (email.equals(mail1)) {


            startActivity(new Intent(teacher_panel.this, door3.class));
        } else if (email.equals(mail2)) {

            startActivity(new Intent(teacher_panel.this, door1.class));
        } /*else if (email.equals(mail3)) {

            startActivity(new Intent(teacher_panel.this, door2.class));
        } */ else {

            Toast.makeText(this, "Not your class", Toast.LENGTH_SHORT).show();
        }

    }



    public void timetable(View view) {

        startActivity(new Intent(teacher_panel.this, teacher_timetable.class));
    }

    public void next(View view) {

        startActivity(new Intent(teacher_panel.this, next_session.class));
    }

    private void toastMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
