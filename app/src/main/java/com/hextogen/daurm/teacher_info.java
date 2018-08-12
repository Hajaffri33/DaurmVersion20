package com.hextogen.daurm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class teacher_info extends AppCompatActivity {


    private FirebaseAuth mAuth;
    EditText tname,mail,pass,cpass, designation;
    Spinner spinner;
    TextView skipThis;
    DatabaseReference databaseTeacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        databaseTeacher = FirebaseDatabase.getInstance().getReference("teacher");

        tname = findViewById(R.id.t_name);
        mail  = findViewById(R.id.ts_email);
        pass  = findViewById(R.id.ts_pass);
        cpass = findViewById(R.id.ts_cpass);
        designation =findViewById(R.id.designation);

        spinner = findViewById(R.id.ts_spinner);
        skipThis = findViewById(R.id.skip);

        mAuth = FirebaseAuth.getInstance();

    }




    public void submit(View view) {

        String name    = tname.getText().toString();
        String email   = mail.getText().toString();
        String passw   = pass.getText().toString();
        String cpassw  = cpass.getText().toString();
        String depart  = spinner.getSelectedItem().toString();
        String desig  = designation.getText().toString();



        if(email.length()==0&&email.length()<8||name.length()==0&&name.length()<8||passw.length()==0&&passw.length()<8
                ||cpassw.length()==0&&cpassw.length()<8||depart.length()==0&&depart.length()<8||desig.length()==0&&desig.length()<8){

            Toast.makeText(this,"Enter in all fields", Toast.LENGTH_SHORT).show();
        }
        else if(emailValidator(email)!=true){

            Toast.makeText(this, "Domain must be @uogsialkot.edu.pk", Toast.LENGTH_SHORT).show();

        }
        else if(pass.length()<8){

            Toast.makeText(teacher_info.this, "Password must be minimum 8 characters", Toast.LENGTH_SHORT).show();
        }
        else if(pass.length()>32){

            Toast.makeText(teacher_info.this, "Password must be maximum 32 characters", Toast.LENGTH_SHORT).show();
        }
        else  if(passw.equals(cpassw)!=true){

            Toast.makeText(this,"Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        else  if(depart.length()==0){

            Toast.makeText(this,"Select Department", Toast.LENGTH_SHORT).show();
        }

        else  if(desig.length()==0){

            Toast.makeText(this,"Enter Designation", Toast.LENGTH_SHORT).show();
        }

        else{
            mAuth.createUserWithEmailAndPassword(email, passw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e("create", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(teacher_info.this, "Signed up Successfully",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(teacher_info.this, teacher_loginn.class));
                                finish();
                                addTeacher();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e("create", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(teacher_info.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });


        }



    }




    private void addTeacher(){

        String id = databaseTeacher.push().getKey();
        String name    = tname.getText().toString();
        String email   = mail.getText().toString();
        String passw   = pass.getText().toString();
        String cpassw  = cpass.getText().toString();
        String depart  = spinner.getSelectedItem().toString();
        String desig   = designation.getText().toString();

        teacher_db_values teacher = new teacher_db_values(id, name, email, passw ,depart, desig);
        databaseTeacher.child(id).setValue(teacher);

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


    public void skip(View view) {

        startActivity(new Intent(teacher_info.this, teacher_panel.class ));
        finish();
    }
}
