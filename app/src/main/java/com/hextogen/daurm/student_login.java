package com.hextogen.daurm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class student_login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        mAuth     = FirebaseAuth.getInstance();
        email     = findViewById(R.id.sl_email);
        password  = findViewById(R.id.sl_pass);

    }

    public void Login(View view) {

        String mail = email.getText().toString();
        String pass = password.getText().toString();

        if(mail.length()==0||pass.length()==0){

            Toast.makeText(student_login.this,"Enter in all fields", Toast.LENGTH_SHORT).show();
        }
        else if(pass.length()<8){

            Toast.makeText(student_login.this, "Password must be minimum 8 characters", Toast.LENGTH_SHORT).show();
        }
        else if(pass.length()>32){

            Toast.makeText(student_login.this, "Password must be maximum 32 characters", Toast.LENGTH_SHORT).show();
        }
        else if(emailValidator(mail)==true){
            Toast.makeText(student_login.this, "Domain must be different than @uogsialkot.edu.pk", Toast.LENGTH_SHORT).show();
        }

        else if(emailValidator2(mail)!=true){
            Toast.makeText(student_login.this, "Enter valid email address", Toast.LENGTH_SHORT).show();
        }

        else{
            mAuth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e("login", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(student_login.this, "Welcome"
                                        , Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(student_login.this, student_panel.class));
                               finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e("login", "signInWithEmail:failure", task.getException());
                                Toast.makeText(student_login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }


    }






    public void gotoStdudentSignup(View view) {

       startActivity(new Intent(student_login.this, student_signup.class));

    }


    private boolean emailValidator2(String email) {


        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =   "([\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4})" ;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();


    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@uogsialkot.edu.pk";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
