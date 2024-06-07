package com.example.lab13.Classes.SupplierModel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab13.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class GetSuppliersFromDB {
    private DBHelper helper;

    public GetSuppliersFromDB(DBHelper helper) {
        this.helper = helper;
    }

    public List<Supplier> GetSuppliers() {
        List<Supplier> SupplierList = new ArrayList<>();

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor suppliersCursor = db.query(DBHelper.TABLE_SUPPLIERS, null, null, null, null, null, null);

        int idIndex = suppliersCursor.getColumnIndex(DBHelper.SUPPLIERS_ID);
        int nameIndex = suppliersCursor.getColumnIndex(DBHelper.SUPPLIERS_NAME);

        while (suppliersCursor.moveToNext()) {
            int idValue = suppliersCursor.getInt(idIndex);
            String nameValue = suppliersCursor.getString(nameIndex);

            SupplierList.add(new Supplier(idValue, nameValue));
        }
        suppliersCursor.close();
        return SupplierList;
    }
}
