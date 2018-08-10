package com.hextogen.daurm;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    public void gotoTeacher(View view) {

        //startActivity(new Intent(MainActivity.this, teacher_loginn.class));

    }


    public void gotoStudent(View view) {

       // startActivity(new Intent(MainActivity.this, student_login.class));
    }
}
