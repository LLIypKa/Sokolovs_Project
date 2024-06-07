package com.example.lab13.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

import java.time.LocalDate;
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
        Cursor warehouseCursor = db.query(
                DBHelper.TABLE_WAREHOUSES_STOCKS,
                null,
                DBHelper.WAREHOUSES_STOCKS_METAL_ID + " = ?",
                new String[]{order.MetalID.toString()},
                null,
                null,
                null
        );

        // Проверка наличия достаточного количества металла на складе
        if (warehouseCursor.moveToFirst()) {
            int index = warehouseCursor.getColumnIndex(DBHelper.WAREHOUSES_STOCKS_METAL_COUNT);
            int currentStock = warehouseCursor.getInt(index);
            if (currentStock >= order.MetalCount) {
                // Уменьшение количества металла на складе
                int updatedStock = currentStock - order.MetalCount;
                ContentValues values = new ContentValues();
                values.put(DBHelper.WAREHOUSES_STOCKS_METAL_COUNT, updatedStock);
                db.update(
                        DBHelper.TABLE_WAREHOUSES_STOCKS,
                        values,
                        DBHelper.WAREHOUSES_STOCKS_METAL_ID + " = ?",
                        new String[]{order.MetalID.toString()}
                );

                db.delete(
                        DBHelper.TABLE_ORDERS,
                        DBHelper.ORDERS_ID + " = ?",
                        new String[]{order.ID.toString()}
                );
                startActivity(new Intent(OrderDetailActivity.this, CheckOrders.class));
                finish();
            } else {
                Toast.makeText(this, "Недостаточно металла на складе", Toast.LENGTH_SHORT).show();
            }
        }
        warehouseCursor.close();
    }
}