package com.example.android.qrcodescanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    public UsersDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        ListView listView = (ListView) findViewById(R.id.list);
        mDbHelper = new UsersDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseContract.UserEntry._ID,
                DatabaseContract.UserEntry.USERS_NAME,
                DatabaseContract.UserEntry.USERS_REG,
                DatabaseContract.UserEntry.YEAR
        };

        Cursor cursor = db.query(
                DatabaseContract.UserEntry.USERS_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<DataObject> words = null;
        DataAdapter adapter;
        try {
            int idColumnIndex = cursor.getColumnIndex(DatabaseContract.UserEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(DatabaseContract.UserEntry.USERS_NAME);
            int regColumnIndex = cursor.getColumnIndex(DatabaseContract.UserEntry.USERS_REG);
            int yearColumnIndex = cursor.getColumnIndex(DatabaseContract.UserEntry.YEAR);
            words = new ArrayList<DataObject>();

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColumnIndex);
                String currentReg = cursor.getString(regColumnIndex);
                String currentYear = cursor.getString(yearColumnIndex);
                words.add(new DataObject(currentName, currentReg, currentYear));
            }
        } finally {
            adapter = new DataAdapter(this, words);
        }
        listView.setAdapter(adapter);

    }


}
