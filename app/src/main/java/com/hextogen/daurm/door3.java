package com.hextogen.daurm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
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

import java.util.Timer;
import java.util.TimerTask;

public class door3 extends AppCompatActivity {

    Timer timer  = new Timer();
    Button btn1, btn2;
    TextView text1, text2;
    String state, Uid;

    FirebaseDatabase myDatabase;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door3);

        btn1 = findViewById(R.id.lock1_on);
        btn2 = findViewById(R.id.lock1_off);
        text1 = findViewById(R.id.textView5);
        text2 = findViewById(R.id.text);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Uid   = currentUser.getUid();

        myDatabase = FirebaseDatabase.getInstance();
        myRef      = myDatabase.getReference("room");

        myRef.child("303").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                state = dataSnapshot.child("state").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LOCK3=Locked");
                myRef.child("303").child("state").setValue("Unlocked");
                myRef.child("303").child("id").setValue(Uid);
                text1.setText("Unlocked");
                timer();


            }
        });
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LOCK3=Unlocked");
                myRef.child("303").child("state").setValue("Locked");
                myRef.child("303").child("id").setValue(Uid);
                text1.setText("Locked");
                stopTimer();


            }
        });

    }


    public void timer(){

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                myRef.child("303").child("state").setValue("Locked");
                Intent intent= new Intent(door3.this,teacher_panel.class);
                startActivity(intent);
            }

        },10000);
    }

    public  void stopTimer(){

        timer.cancel();
    }



    public void request(String command){

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){

            String url = "http://192.168.43.146/";

            new RequestedData().execute(url+command);
        }
        else{
            Toast.makeText(door3.this,"No connection detected", Toast.LENGTH_LONG).show();
        }
    }

    public void back(View view) {


        Intent intent= new Intent(door3.this,rooms.class);
        startActivity(intent);
        finish();
    }


    private class RequestedData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            return connection.getData(url[0]);
        }

    }

    @Override
    public void finish() {
        super.finish();
    }
}
