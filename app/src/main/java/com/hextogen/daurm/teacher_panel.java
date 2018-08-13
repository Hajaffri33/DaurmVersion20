package com.hextogen.daurm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class teacher_panel extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);

        mAuth = FirebaseAuth.getInstance();
        signout = findViewById(R.id.panel_signout);


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                startActivity(new Intent(teacher_panel.this, MainActivity.class));
                finish();


            }
        });
    }


    public void gotoLock(View view) {


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String mail1 = "asifkhan@uogsialkot.edu.pk";
        String mail2 = "ad@uogsialkot.edu.pk";
        String mail3 = "faizanarshad@uogsialkot.edu.pk";
        String email = user.getEmail();


        if (email.equals(mail1)) {

            startActivity(new Intent(teacher_panel.this, door1.class));
        } else if (email.equals(mail2)) {

            startActivity(new Intent(teacher_panel.this, door2.class));
        } else if (email.equals(mail3)) {

            startActivity(new Intent(teacher_panel.this, door3.class));
        } else {

            Toast.makeText(this, "Not your class", Toast.LENGTH_SHORT).show();
        }

    }


    public void timetable(View view) {

        startActivity(new Intent(teacher_panel.this, teacher_timetable.class));
    }

    public void next(View view) {

        startActivity(new Intent(teacher_panel.this, next_session.class));
    }
}
