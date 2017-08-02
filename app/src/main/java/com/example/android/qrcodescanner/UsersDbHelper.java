package com.example.android.qrcodescanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Harshit Maheshwari on 30-07-2017.
 */
public class UsersDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "users.db";

    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String create_table = "CREATE TABLE " + DatabaseContract.UserEntry.USERS_TABLE_NAME
            + " (" + DatabaseContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DatabaseContract.UserEntry.USERS_NAME + " TEXT NOT NULL, " +
            DatabaseContract.UserEntry.USERS_REG + " TEXT NOT NULL, " +
            DatabaseContract.UserEntry.YEAR + " TEXT NOT NULL);";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
