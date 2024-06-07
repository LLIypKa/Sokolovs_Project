package com.example.lab13.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab13.Classes.OrdersModel.GetOrders;
import com.example.lab13.Classes.OrdersModel.Order;
import com.example.lab13.Classes.OrdersModel.OrdersAdapter;
import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.List;

public class CheckOrders extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    List<Order> orders = GetOrders.GetOrders();
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
        db = helper.getWritableDatabase();
        ordersListView = findViewById(R.id.ordersList);
        Cursor ordersCursor = db.query(DBHelper.TABLE_ORDERS, null,null,null, null, null, null);
        while(ordersCursor.move(1)) {
            Log.d("check order", "order");
        }
        OrdersAdapter ordersAdapter = new OrdersAdapter(this, orders, helper);
        ordersListView.setAdapter(ordersAdapter);
    }
}