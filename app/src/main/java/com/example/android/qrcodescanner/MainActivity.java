package com.example.android.qrcodescanner;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
    private Button viewDatabaseButton;
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
        viewDatabaseButton = (Button)findViewById(R.id.database_button);
        qrscan = new IntentIntegrator(this);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonEffect(scanButton);
                qrscan.initiateScan();
            }
        });
        viewDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonEffect(viewDatabaseButton);
                Intent intent = new Intent(MainActivity.this,DatabaseActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe05c6bc0, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
}
