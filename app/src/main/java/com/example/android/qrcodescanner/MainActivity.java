package com.example.android.qrcodescanner;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    public UsersDbHelper mDbHelper;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents() == null){
                Toast.makeText(this, "Result not found.", Toast.LENGTH_SHORT).show();
            }
            else{
                try{

                    ContentValues values = new ContentValues();

                    JSONObject obj = new JSONObject(result.getContents());
                    String name = obj.getString("name");
                    String reg = obj.getString("reg");
                    String year = obj.getString("year");

                    textView.setText("Name: " + obj.getString("name") + "\n" +
                            "Registration Number: " + obj.getString("reg") + "\n" +
                            "Year: " + obj.getString("year"));

                    mDbHelper = new UsersDbHelper(getApplicationContext());
                    SQLiteDatabase db =  mDbHelper.getWritableDatabase();

                    values.put(DatabaseContract.UserEntry.USERS_NAME,name);
                    values.put(DatabaseContract.UserEntry.USERS_REG,reg);
                    values.put(DatabaseContract.UserEntry.YEAR,year);


                    long i = db.insert(DatabaseContract.UserEntry.USERS_TABLE_NAME,null,values);
                    if(i==-1){
                        Toast.makeText(this,"Error adding data to database", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this,"Data added to database", Toast.LENGTH_SHORT).show();
                    }


                }
                catch (JSONException e){
                    textView.setText("There was problem parsing the JSON response. The scanned QR fetched this result:\n\n" + result.getContents()
                     + "\n\n(Help: Make sure that scanned QR code is format: \n{ \"name\" : \"Harshit\" , \"reg\": \"16BCI0039\" , \"year\" : \"Second Year\"})");
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
