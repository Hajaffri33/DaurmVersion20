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

        FirebaseUser user  =  FirebaseAuth.getInstance().getCurrentUser();
        String email =   user.getEmail();

        if(email=="ad@uogsialkot.edu.pk"){

            startActivity(new Intent(teacher_panel.this, door1.class));
        }

        else{

            Toast.makeText(this, "Not your class", Toast.LENGTH_SHORT).show();
        }



       //

    }

}
