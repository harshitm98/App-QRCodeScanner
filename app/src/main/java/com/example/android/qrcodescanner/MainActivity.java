package com.example.android.qrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button scanButton;
    private TextView textView;
    private IntentIntegrator qrscan;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents() == null){
                Toast.makeText(this, "Result not found.", Toast.LENGTH_SHORT).show();
            }
            else{
                try{
                    JSONObject obj = new JSONObject(result.getContents());
                    textView.setText("Name: " + obj.getString("name") + "\n" +
                            "Registeration Number: " + obj.getString("reg") + "\n" +
                            "Year: " + obj.getString("year"));

                }
                catch (JSONException e){
                    Log.e("MainActivity", "Error while parsing the data");
                    e.printStackTrace();
                    Toast.makeText(this,result.getContents(),Toast.LENGTH_SHORT).show();
                }
            }
        } else{
            super.onActivityResult(requestCode,resultCode,data);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (Button)findViewById(R.id.scan_button);
        textView = (TextView)findViewById(R.id.display_text);

        qrscan = new IntentIntegrator(this);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrscan.initiateScan();
            }
        });

    }
}
