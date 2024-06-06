package com.example.lab13.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab13.Classes.OrdersModel.Order;
import com.example.lab13.R;

import java.time.format.DateTimeFormatter;

public class OrderDetailActivity extends AppCompatActivity {
    private Order order;

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

        Button buttonCompleteOrder = findViewById(R.id.buttonCompleteOrder);
        buttonCompleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeOrder();
            }
        });
    }

    private void completeOrder() {
        // Логика уменьшения количества металла на складе и удаления записи из базы данных
        // ...

        // Возвращение к предыдущей активности
        finish();
    }
}
