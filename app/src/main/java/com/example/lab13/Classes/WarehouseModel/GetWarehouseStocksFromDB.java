package com.example.lab13.Classes.WarehouseModel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab13.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class GetWarehouseStocksFromDB {
    private DBHelper helper;

    public GetWarehouseStocksFromDB(DBHelper helper) {
        this.helper = helper;
    }

    public List<Warehouse> GetWarehouseStocks() {
        List<Warehouse> WarehouseStocks = new ArrayList<>();

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor warehouseCursor = db.query(DBHelper.TABLE_WAREHOUSES_STOCKS, null, null, null, null, null, null);

        int idIndex = warehouseCursor.getColumnIndex(DBHelper.WAREHOUSES_STOCKS_ID);
        int metalIdIndex = warehouseCursor.getColumnIndex(DBHelper.WAREHOUSES_STOCKS_METAL_ID);
        int metalCountIndex = warehouseCursor.getColumnIndex(DBHelper.WAREHOUSES_STOCKS_METAL_COUNT);
        int updateDateIndex = warehouseCursor.getColumnIndex(DBHelper.WAREHOUSES_STOCKS_UPDATE_DATE);

        while (warehouseCursor.moveToNext()) {
            int idValue = warehouseCursor.getInt(idIndex);
            int metalIdValue = warehouseCursor.getInt(metalIdIndex);
            int metalCountValue = warehouseCursor.getInt(metalCountIndex);
            String updateDateValue = warehouseCursor.getString(updateDateIndex);

            WarehouseStocks.add(new Warehouse(idValue, metalIdValue, metalCountValue, updateDateValue));
        }
        warehouseCursor.close();
        return WarehouseStocks;
    }
}
