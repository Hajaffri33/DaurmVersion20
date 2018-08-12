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

public class door2 extends AppCompatActivity {


    Button btn1, btn2, btn3, btn4, btn5, btn6;
    TextView text1,text2,text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door2);

        btn3 = findViewById(R.id.lock2_on);
        btn4 = findViewById(R.id.lock3_off);

        text1 = findViewById(R.id.textView5);


        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LOCK2=Locked");
                text1.setText("UNLOCKED");


            }
        });
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LOCK2=Unlocked");
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
            Toast.makeText(door2.this,"No connection detected", Toast.LENGTH_LONG).show();
        }
    }



    private class RequestedData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            return connection.getData(url[0]);
        }

    }
}
