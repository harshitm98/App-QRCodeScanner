package com.example.android.qrcodescanner;


import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshit Maheshwari on 02-08-2017.
 */

public class DataAdapter extends ArrayAdapter<DataObject> {
    public DataAdapter(Context context, ArrayList<DataObject> objects) {
        super(context, 0, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitem = convertView;

        if(listitem == null){
            listitem = LayoutInflater.from(getContext()).inflate(R.layout.activity_database,parent,false);
        }

        DataObject obj = getItem(position);

        TextView name = (TextView)listitem.findViewById(R.id.name);
        name.setText(obj.getName());

        TextView reg = (TextView)listitem.findViewById(R.id.reg);
        reg.setText(obj.getReg());

        TextView year = (TextView)listitem.findViewById(R.id.year);
        year.setText(obj.getYear());

        return listitem;
    }
}
