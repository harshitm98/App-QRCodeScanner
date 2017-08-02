package com.example.android.qrcodescanner;

/**
 * Created by Harshit Maheshwari on 02-08-2017.
 */

public class DataObject {

    private String mName;
    private String mReg;
    private String mYear;

    public DataObject(String vName, String vReg, String vYear){
        mName = vName;
        mReg = vReg;
        mYear = vYear;
    }

    public String getName(){
        return mName;
    }

    public String getReg(){
        return mReg;
    }

    public String getYear(){
        return mYear;
    }

}
