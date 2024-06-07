package com.example.lab13.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab13.Classes.WarehouseModel.GetWarehouseStocksFromDB;
import com.example.lab13.Classes.WarehouseModel.Warehouse;
import com.example.lab13.Classes.WarehouseModel.WarehouseAdapter;
import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.List;

public class WarehouseListActivity extends AppCompatActivity {
    DBHelper dbHelper;
    List<Warehouse> warehouses;
    ListView warehousesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_list);

        dbHelper = new DBHelper(this);
        warehouses = new GetWarehouseStocksFromDB(dbHelper).GetWarehouseStocks();

        warehousesListView = findViewById(R.id.warehousesList);
        WarehouseAdapter warehouseAdapter = new WarehouseAdapter(this, warehouses, dbHelper);
        warehousesListView.setAdapter(warehouseAdapter);
    }
}
