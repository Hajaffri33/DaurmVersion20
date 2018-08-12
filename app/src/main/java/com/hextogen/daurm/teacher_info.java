package com.hextogen.daurm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class teacher_info extends AppCompatActivity {


    private FirebaseAuth mAuth;
    EditText tname, pass, cpass, designation;
    Spinner spinner;
    TextView skipThis;
    DatabaseReference databaseTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        databaseTeacher = FirebaseDatabase.getInstance().getReference("teacher");

        tname = findViewById(R.id.t_name);
        pass = findViewById(R.id.ts_pass);
        cpass = findViewById(R.id.ts_cpass);
        designation = findViewById(R.id.designation);

        spinner = findViewById(R.id.ts_spinner);
        skipThis = findViewById(R.id.skip);

        mAuth = FirebaseAuth.getInstance();

    }


    public void submit(View view) {

        String name = tname.getText().toString();
        String passw = pass.getText().toString();
        String cpassw = cpass.getText().toString();
        String depart = spinner.getSelectedItem().toString();
        String desig = designation.getText().toString();


        if (name.length() == 0 && name.length() < 8 || passw.length() == 0 && passw.length() < 8
                || cpassw.length() == 0 && cpassw.length() < 8 || depart.length() == 0 && depart.length() < 8 || desig.length() == 0 && desig.length() < 8) {

            Toast.makeText(this, "Enter in all fields", Toast.LENGTH_SHORT).show();
        } else if (pass.length() < 8) {

            Toast.makeText(teacher_info.this, "Password must be minimum 8 characters", Toast.LENGTH_SHORT).show();
        } else if (pass.length() > 32) {

            Toast.makeText(teacher_info.this, "Password must be maximum 32 characters", Toast.LENGTH_SHORT).show();
        } else if (passw.equals(cpassw) != true) {

            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else if (depart.length() == 0) {

            Toast.makeText(this, "Select Department", Toast.LENGTH_SHORT).show();
        } else if (desig.length() == 0) {

            Toast.makeText(this, "Enter Designation", Toast.LENGTH_SHORT).show();
        } else {

            boolean firstStart;
            String newPassword = passw;
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String mail = user.getEmail();
            user.updateEmail(mail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("update", "User email address updated.");
                            }
                        }
                    });
            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("update", "User password updated.");
                            }
                        }
                    });
            String id = user.getUid();
            databaseTeacher.child(id).child("t_name").setValue(name);
            databaseTeacher.child(id).child("email").setValue(mail);
            databaseTeacher.child(id).child("pass").setValue(newPassword);
            databaseTeacher.child(id).child("dept").setValue(depart);
            databaseTeacher.child(id).child("desig").setValue(desig);

            startActivity(new Intent(teacher_info.this, teacher_panel.class));
            finish();


        }
    }

    public void skip(View view) {

        startActivity(new Intent(teacher_info.this, teacher_panel.class));
        finish();
    }
}
