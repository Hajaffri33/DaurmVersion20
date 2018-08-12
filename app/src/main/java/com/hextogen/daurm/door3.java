package com.hextogen.daurm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class door3 extends AppCompatActivity {


    Button  btn5, btn6;
    TextView text1,text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door3);


        btn5 = findViewById(R.id.lock3_on);
        btn6 = findViewById(R.id.lock3_off);
        text1 = findViewById(R.id.textView5);


        btn5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LOCK3=Locked");
                text1.setText("UNLOCKED");

            }
        });
        btn6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LOCK3=Unlocked");

                text1.setText("LOCKED");

            }
        });

    }

    public void request(String command){

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){

            String url = "http://192.168.1.100/";

            new RequestedData().execute(url+command);
        }
        else{
            Toast.makeText(door3.this,"No connection detected", Toast.LENGTH_LONG).show();
        }
    }

    private class RequestedData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            return connection.getData(url[0]);
        }

    }
}
