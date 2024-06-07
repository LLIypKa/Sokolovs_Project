package com.example.lab13.Classes.SuppliesModel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab13.DBHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GetSuppliesFromDB {
    private DBHelper helper;

    public GetSuppliesFromDB(DBHelper helper) {
        this.helper = helper;
    }

    public List<Supplies> GetSupplies() {
        List<Supplies> SuppliesList = new ArrayList<>();

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor suppliesCursor = db.query(DBHelper.TABLE_SUPPLIES, null, null, null, null, null, null);

        int idIndex = suppliesCursor.getColumnIndex(DBHelper.SUPPLIES_ID);
        int supplierIdIndex = suppliesCursor.getColumnIndex(DBHelper.SUPPLIES_SUPPLIER_ID);
        int metalIdIndex = suppliesCursor.getColumnIndex(DBHelper.SUPPLIES_METAL_ID);
        int metalCountIndex = suppliesCursor.getColumnIndex(DBHelper.SUPPLIES_METAL_COUNT);
        int dateIndex = suppliesCursor.getColumnIndex(DBHelper.SUPPLIES_DATE);

        while (suppliesCursor.moveToNext()) {
            int idValue = suppliesCursor.getInt(idIndex);
            int supplierIdValue = suppliesCursor.getInt(supplierIdIndex);
            int metalIdValue = suppliesCursor.getInt(metalIdIndex);
            int metalCountValue = suppliesCursor.getInt(metalCountIndex);
            String dateValue = suppliesCursor.getString(dateIndex);

            SuppliesList.add(new Supplies(idValue, supplierIdValue, metalIdValue, metalCountValue, LocalDate.parse(dateValue, DateTimeFormatter.ISO_LOCAL_DATE)));
        }
        suppliesCursor.close();
        return SuppliesList;
    }
}
