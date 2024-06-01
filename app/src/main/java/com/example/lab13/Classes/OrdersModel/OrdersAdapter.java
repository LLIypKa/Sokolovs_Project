package com.example.lab13.Classes.OrdersModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab13.R;

import java.util.List;

public class OrdersAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orders;

    public OrdersAdapter(Context context, List<Order> orders) {
        super(context, 0, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order order = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_order, parent, false);
        }

        TextView textViewOrderID = convertView.findViewById(R.id.textViewOrderID);
        TextView textViewClientID = convertView.findViewById(R.id.textViewClientID);
        TextView textViewMetalID = convertView.findViewById(R.id.textViewMetalID);
        TextView textViewMetalCount = convertView.findViewById(R.id.textViewMetalCount);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);

        textViewOrderID.setText("Order ID: " + order.ID);
        textViewClientID.setText("Client ID: " + order.ClientID);
        textViewMetalID.setText("Metal ID: " + order.MetalID);
        textViewMetalCount.setText("Metal Count: " + order.MetalCount);
        textViewDate.setText("Date: " + order.Date.toString());

        return convertView;
    }
}
