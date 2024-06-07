package com.example.lab13.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab13.Classes.OrdersModel.Order;
import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.time.format.DateTimeFormatter;

public class OrderDetailActivity extends AppCompatActivity {
    private Order order;
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Получение данных из Intent
        order = (Order) getIntent().getSerializableExtra("order");

        // Установка данных в текстовые поля
        TextView textViewOrderIDDetail = findViewById(R.id.textViewOrderIDDetail);
        TextView textViewClientIDDetail = findViewById(R.id.textViewClientIDDetail);
        TextView textViewMetalIDDetail = findViewById(R.id.textViewMetalIDDetail);
        TextView textViewMetalCountDetail = findViewById(R.id.textViewMetalCountDetail);
        TextView textViewDateDetail = findViewById(R.id.textViewDateDetail);

        textViewOrderIDDetail.setText("Order ID: " + order.ID);
        textViewClientIDDetail.setText("Client ID: " + order.ClientID);
        textViewMetalIDDetail.setText("Metal ID: " + order.MetalID);
        textViewMetalCountDetail.setText("Metal Count: " + order.MetalCount);
        textViewDateDetail.setText("Date: " + order.Date.toString());

        helper = new DBHelper(this);

        Button buttonCompleteOrder = findViewById(R.id.buttonCompleteOrder);
        buttonCompleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeOrder();
            }
        });
    }

    private void completeOrder() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(DBHelper.TABLE_ORDERS, String.format("%s = %s", DBHelper.ORDERS_ID, order.ID.toString()), null);
        finish();
        startActivity(new Intent(OrderDetailActivity.this, CheckOrders.class));
    }
}
