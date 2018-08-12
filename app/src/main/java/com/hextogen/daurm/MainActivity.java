package com.hextogen.daurm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            startActivity(new Intent(MainActivity.this, teacher_panel.class));
        }



    }



    public void gotoTeacher(View view) {

     startActivity(new Intent(MainActivity.this, teacher_loginn.class));

    }


    public void gotoStudent(View view) {

        startActivity(new Intent(MainActivity.this, student_login.class));
    }
}
