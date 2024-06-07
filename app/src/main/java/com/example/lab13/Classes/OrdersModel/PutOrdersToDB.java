package com.example.lab13.Classes.OrdersModel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab13.DBHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PutOrdersToDB {
    private static List<Order> Orders = new ArrayList<>();

    public static List<Order> GetOrders() {
        if(Orders.size() == 0) {
            Orders.add(new Order(0, 0, 0, 200, LocalDate.now().plus(1, ChronoUnit.DAYS)));
            Orders.add(new Order(1, 1, 1, 200, LocalDate.now().plus(2, ChronoUnit.DAYS)));
            Orders.add(new Order(2, 0, 2, 200, LocalDate.now().plus(3, ChronoUnit.DAYS)));
            Orders.add(new Order(3, 1, 3, 200, LocalDate.now().plus(4, ChronoUnit.DAYS)));
        }

        return Orders;
    }
}
