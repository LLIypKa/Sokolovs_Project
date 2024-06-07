package com.example.lab13.Classes.MetalModels;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.List;

public class MetalAdapter extends ArrayAdapter<Metal> {
    private Context context;
    private List<Metal> metals;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public MetalAdapter(Context context, List<Metal> metals, DBHelper dbHelper) {
        super(context, 0, metals);
        this.context = context;
        this.metals = metals;
        this.dbHelper = dbHelper;
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Metal metal = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_metal, parent, false);
        }

        TextView textViewMetalID = convertView.findViewById(R.id.textViewMetalID);
        TextView textViewMetalName = convertView.findViewById(R.id.textViewMetalName);
        TextView textViewMetalColor = convertView.findViewById(R.id.textViewMetalColor);

        textViewMetalID.setText("Metal ID: " + metal.metalStructureID);
        textViewMetalName.setText("Metal Name: " + metal.metalStructureName);
        textViewMetalColor.setText("Metal Color: " + metal.metalStructureColor);

        return convertView;
    }
}
