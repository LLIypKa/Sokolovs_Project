package com.example.lab13.Classes.WarehouseModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.List;

public class WarehouseAdapter extends ArrayAdapter<Warehouse> {
    private Context context;
    private List<Warehouse> warehouses;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public WarehouseAdapter(Context context, List<Warehouse> warehouses, DBHelper dbHelper) {
        super(context, 0, warehouses);
        this.context = context;
        this.warehouses = warehouses;
        this.dbHelper = dbHelper;
        this.db = dbHelper.getWritableDatabase();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Warehouse warehouse = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse, parent, false);
        }

        TextView textViewWarehouseID = convertView.findViewById(R.id.textViewWarehouseID);
        TextView textViewMetalName = convertView.findViewById(R.id.textViewMetalName);
        TextView textViewMetalCount = convertView.findViewById(R.id.textViewMetalCount);
        TextView textViewUpdateDate = convertView.findViewById(R.id.textViewUpdateDate);

        // Получение имени металла по его ID
        String metalName = getMetalNameById(warehouse.MetalStructureId);

        textViewWarehouseID.setText("Warehouse ID: " + warehouse.RecordId);
        textViewMetalName.setText("Metal Name: " + metalName);
        textViewMetalCount.setText("Metal Count: " + warehouse.MetalStructureCount);
        textViewUpdateDate.setText("Last Update: " + warehouse.UpdateDate);

        return convertView;
    }

    private String getMetalNameById(int metalId) {
        String metalName = "";
        Cursor cursor = db.query(DBHelper.TABLE_METAL_STRUCTURES,
                new String[]{DBHelper.METAL_STRUCTURES_NAME},
                DBHelper.METAL_STRUCTURES_ID + "=?",
                new String[]{String.valueOf(metalId)},
                null, null, null);

        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(DBHelper.METAL_STRUCTURES_NAME);
            metalName = cursor.getString(index);
        }
        cursor.close();
        return metalName;
    }
}
