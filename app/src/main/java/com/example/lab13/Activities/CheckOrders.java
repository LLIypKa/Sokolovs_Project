package com.example.lab13.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab13.Classes.OrdersModel.GetOrdersFromDBClass;
import com.example.lab13.Classes.OrdersModel.PutOrdersToDB;
import com.example.lab13.Classes.OrdersModel.Order;
import com.example.lab13.Classes.OrdersModel.OrdersAdapter;
import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.ArrayList;
import java.util.List;

public class CheckOrders extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    List<Order> orders = new ArrayList<>();
    ListView ordersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_orders);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        helper = new DBHelper(this);
        orders = new GetOrdersFromDBClass(helper).GetOrdersFromDB();
        db = helper.getWritableDatabase();
        ordersListView = findViewById(R.id.ordersList);
        Cursor ordersCursor = db.query(DBHelper.TABLE_ORDERS, null,null,null, null, null, null);
        Cursor stocksCursor = db.query(DBHelper.TABLE_WAREHOUSES_STOCKS, null,null,null, null, null, null);
        int metalindex = stocksCursor.getColumnIndex(DBHelper.WAREHOUSES_STOCKS_METAL_ID);
        int metalcount = stocksCursor.getColumnIndex(DBHelper.WAREHOUSES_STOCKS_METAL_COUNT);
        while(ordersCursor.move(1)) {
            Log.d("check order", "order");
        }
        while(stocksCursor.moveToNext()){
            int metal = stocksCursor.getInt(metalindex);
            int count = stocksCursor.getInt(metalcount);

            Log.d("stocks", String.valueOf(metal) + " " + String.valueOf(count));
        }
        OrdersAdapter ordersAdapter = new OrdersAdapter(this, orders, helper);
        ordersListView.setAdapter(ordersAdapter);
    }

    public void onReturnClick(View v) {
        // Переход на главную страницу
        Intent intent = new Intent(CheckOrders.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}