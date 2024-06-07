package com.example.lab13.Classes.MetalModels;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab13.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class GetMetalStructuresFromDB {
    private DBHelper helper;

    public GetMetalStructuresFromDB(DBHelper helper) {
        this.helper = helper;
    }

    public List<Metal> GetStructuresFromDB() {
        List<Metal> MetalStructures = new ArrayList<>();

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor metalCursor = db.query(DBHelper.TABLE_METAL_STRUCTURES, null, null, null, null, null, null);

        int idIndex = metalCursor.getColumnIndex(DBHelper.METAL_STRUCTURES_ID);
        int nameIndex = metalCursor.getColumnIndex(DBHelper.METAL_STRUCTURES_NAME);
        int colorIndex = metalCursor.getColumnIndex(DBHelper.METAL_STRUCTURES_COLOR);

        while (metalCursor.moveToNext()) {
            int idValue = metalCursor.getInt(idIndex);
            String nameValue = metalCursor.getString(nameIndex);
            String colorValue = metalCursor.getString(colorIndex);

            MetalStructures.add(new Metal(idValue, nameValue, colorValue));
        }
        metalCursor.close();
        return MetalStructures;
    }
}
