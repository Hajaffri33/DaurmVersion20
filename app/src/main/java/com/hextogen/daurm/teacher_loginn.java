package com.hextogen.daurm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class teacher_loginn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email, password;
    boolean firstStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_loginn);
        mAuth     = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();
        email     = findViewById(R.id.t_login);
        password  = findViewById(R.id.tl_pass);


    }


    public void Login(View view) {

        String mail = email.getText().toString();
        String pass = password.getText().toString();

        if(mail.length()==0||pass.length()==0){

            Toast.makeText(teacher_loginn.this,"Enter in all fields", Toast.LENGTH_SHORT).show();
        }
        else if(pass.length()<8){

            Toast.makeText(teacher_loginn.this, "Password must be minimum 8 characters", Toast.LENGTH_SHORT).show();
        }
        else if(pass.length()>32){

            Toast.makeText(teacher_loginn.this, "Password must be maximum 32 characters", Toast.LENGTH_SHORT).show();
        }
        else if(emailValidator(mail)!=true){

            Toast.makeText(teacher_loginn.this, "Domain must be @uogisalkot.edu.pk", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(teacher_loginn.this, "Welcome"
                                        , Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(teacher_loginn.this, teacher_signup.class));
                                finish();



                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e("login", "signInWithEmail:failure", task.getException());
                                Toast.makeText(teacher_loginn.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }


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
