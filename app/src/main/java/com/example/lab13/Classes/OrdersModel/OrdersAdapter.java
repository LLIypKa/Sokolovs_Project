package com.example.lab13.Classes.OrdersModel;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab13.Activities.OrderDetailActivity;
import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.List;

public class OrdersAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orders;
    private DBHelper helper;
    private SQLiteDatabase db;

    public OrdersAdapter(Context context, List<Order> orders, DBHelper helper) {
        super(context, 0, orders);
        this.context = context;
        this.orders = orders;
        this.helper = helper;
        db = helper.getWritableDatabase();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order order = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_order, parent, false);
        }

        Cursor clientsCursor = db.query(DBHelper.TABLE_CLIENTS, null, null, null, null, null, null);
        int clientNameColumnIndex = clientsCursor.getColumnIndex(DBHelper.CLIENTS_NAME);
        clientsCursor.move(order.ClientID + 1);

        Cursor metalCursor = db.query(DBHelper.TABLE_METAL_STRUCTURES, null, null, null, null, null, null);
        int metalColumnIndex = metalCursor.getColumnIndex(DBHelper.METAL_STRUCTURES_NAME);
        metalCursor.move(order.MetalID);

        TextView textViewOrderID = convertView.findViewById(R.id.textViewOrderID);
        TextView textViewClientID = convertView.findViewById(R.id.textViewClientID);
        TextView textViewMetalID = convertView.findViewById(R.id.textViewMetalID);
        TextView textViewMetalCount = convertView.findViewById(R.id.textViewMetalCount);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);

        textViewOrderID.setText("Order ID: " + order.ID);
        textViewClientID.setText("Client: " + clientsCursor.getString(clientNameColumnIndex));
        textViewMetalID.setText("Metal: " + metalCursor.getString(metalColumnIndex));
        textViewMetalCount.setText("Metal Count: " + order.MetalCount);
        textViewDate.setText("Date: " + order.Date.toString());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("order", order);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
