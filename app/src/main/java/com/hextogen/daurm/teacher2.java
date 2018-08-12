package com.hextogen.daurm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class teacher2 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher2);

        mAuth = FirebaseAuth.getInstance();
       signout = findViewById(R.id.panel_signout);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                startActivity(new Intent(teacher2.this, MainActivity.class));
                finish();


            }
        });
    }


    public void gotoLock(View view) {

       // startActivity(new Intent(teacher_panel.this, lock_panel.class));

    }

}
