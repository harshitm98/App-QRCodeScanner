package com.example.android.qrcodescanner;

import android.provider.BaseColumns;

/**
 * Created by Harshit Maheshwari on 30-07-2017.
 */
public class DatabaseContract implements BaseColumns {

    public static final class UserEntry{

        public static final String ID = "_ID";
        public static final String USERS_TABLE_NAME = "users";
        public static final String USERS_NAME = "name";
        public static final String USERS_REG  = "reg";
        public static final String YEAR = "year";



    }


}
