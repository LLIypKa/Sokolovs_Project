package com.example.lab13.Classes.SuppliesModel;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.time.LocalDateTime;
import java.util.List;

public class SuppliesAdapter extends ArrayAdapter<Supplies> {
    private Context context;
    private List<Supplies> supplies;
    private DBHelper dbHelper;

    public SuppliesAdapter(Context context, List<Supplies> supplies, DBHelper dbHelper) {
        super(context, 0, supplies);
        this.context = context;
        this.supplies = supplies;
        this.dbHelper = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Supplies supplies = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_supply, parent, false);
        }

        TextView textViewSupplyID = convertView.findViewById(R.id.textViewSupplyID);
        TextView textViewSupplierID = convertView.findViewById(R.id.textViewSupplierID);
        TextView textViewMetalID = convertView.findViewById(R.id.textViewMetalID);
        TextView textViewMetalCount = convertView.findViewById(R.id.textViewMetalCount);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);

        textViewSupplyID.setText("Supply ID: " + supplies.ID);
        textViewSupplierID.setText("Supplier : " + getSupplierNameById(supplies.SupplierID));
        textViewMetalID.setText("Metal: " + getMetalNameById(supplies.MetalID));
        textViewMetalCount.setText("Metal Count: " + supplies.MetalCount);
        textViewDate.setText("Date: " + supplies.Date.toString());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeSupply(supplies);
            }
        });

        return convertView;
    }

    public String getMetalNameById(int metalId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_METAL_STRUCTURES, new String[]{DBHelper.METAL_STRUCTURES_NAME}, DBHelper.METAL_STRUCTURES_ID + "=?",
                new String[]{String.valueOf(metalId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(DBHelper.METAL_STRUCTURES_NAME);
            String metalName = cursor.getString(index);
            cursor.close();
            return metalName;
        }
        return null;
    }

    public String getSupplierNameById(int supplierId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_SUPPLIERS, new String[]{DBHelper.SUPPLIERS_NAME}, DBHelper.SUPPLIERS_ID + "=?",
                new String[]{String.valueOf(supplierId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(DBHelper.SUPPLIERS_NAME);
            String supplierName = cursor.getString(index);
            cursor.close();
            return supplierName;
        }
        return null;
    }

    private void completeSupply(Supplies supply) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            Cursor cursor = db.query(DBHelper.TABLE_WAREHOUSES_STOCKS,
                    new String[]{DBHelper.WAREHOUSES_STOCKS_METAL_COUNT},
                    DBHelper.WAREHOUSES_STOCKS_METAL_ID + "=?",
                    new String[]{String.valueOf(supply.MetalID)}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndex(DBHelper.WAREHOUSES_STOCKS_METAL_COUNT);
                int currentCount = cursor.getInt(index);
                int newCount = currentCount + supply.MetalCount;
                ContentValues values = new ContentValues();
                values.put(DBHelper.WAREHOUSES_STOCKS_METAL_COUNT, newCount);
                db.update(DBHelper.TABLE_WAREHOUSES_STOCKS, values, DBHelper.WAREHOUSES_STOCKS_METAL_ID + "=?",
                        new String[]{String.valueOf(supply.MetalID)});
                cursor.close();
            } else {
                ContentValues values = new ContentValues();
                values.put(DBHelper.WAREHOUSES_STOCKS_METAL_ID, supply.MetalID);
                values.put(DBHelper.WAREHOUSES_STOCKS_METAL_COUNT, supply.MetalCount);
                values.put(DBHelper.WAREHOUSES_STOCKS_UPDATE_DATE, LocalDateTime.now().toString());
                db.insert(DBHelper.TABLE_WAREHOUSES_STOCKS, null, values);
            }

            db.delete(DBHelper.TABLE_SUPPLIES, DBHelper.SUPPLIES_ID + "=?",
                    new String[]{String.valueOf(supply.ID)});
            db.setTransactionSuccessful();

            supplies.remove(supply);
            notifyDataSetChanged();

            Toast.makeText(context, "Поставка выполнена", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
}
