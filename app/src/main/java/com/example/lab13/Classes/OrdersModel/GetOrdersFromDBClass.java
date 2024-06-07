package com.example.lab13.Classes.OrdersModel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab13.DBHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GetOrdersFromDBClass {
    private DBHelper helper;


    public GetOrdersFromDBClass(DBHelper helper) {
        this.helper = helper;
    }

    public List<Order> GetOrdersFromDB() {
        List<Order> Orders = new ArrayList<>();

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor ordersCursor = db.query(DBHelper.TABLE_ORDERS, null, null, null, null, null, null);
        int idIndex = ordersCursor.getColumnIndex(DBHelper.ORDERS_ID);
        int clientIdIndex = ordersCursor.getColumnIndex(DBHelper.ORDERS_CLIENT_ID);
        int metalIdIndex = ordersCursor.getColumnIndex(DBHelper.ORDERS_METAL_ID);
        int metalCountIndex = ordersCursor.getColumnIndex(DBHelper.ORDERS_METAL_COUNT);
        int dateIndex = ordersCursor.getColumnIndex(DBHelper.ORDERS_DATE);

        while (ordersCursor.move(1)) {
            int idValue = ordersCursor.getInt(idIndex);
            int clientIdValue = ordersCursor.getInt(clientIdIndex);
            int metalIdValue = ordersCursor.getInt(metalIdIndex);
            int metalCountValue = ordersCursor.getInt(metalCountIndex);
            String dateValue = ordersCursor.getString(dateIndex);

            Orders.add(new Order(idValue, clientIdValue, metalIdValue, metalCountValue, LocalDate.parse(dateValue, DateTimeFormatter.ISO_LOCAL_DATE)));
        }

        return Orders;
    }
}
