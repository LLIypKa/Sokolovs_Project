package com.example.lab13.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab13.Classes.OrdersModel.GetOrders;
import com.example.lab13.Classes.OrdersModel.Order;
import com.example.lab13.Classes.SuppliesModel.GetSupplies;
import com.example.lab13.Classes.SuppliesModel.Supplies;
import com.example.lab13.Classes.UserModels.GetClientsList;
import com.example.lab13.Classes.MetalModels.GetMetalStructures;
import com.example.lab13.Classes.MetalModels.Metal;
import com.example.lab13.Classes.SupplierModel.GetSupplier;
import com.example.lab13.Classes.SupplierModel.Supplier;
import com.example.lab13.Classes.UserModels.User;
import com.example.lab13.Classes.WarehouseModel.GetWarehouseList;
import com.example.lab13.Classes.WarehouseModel.Warehouse;
import com.example.lab13.DBHelper;
import com.example.lab13.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void toOrdersClick(View view) {
        Log.d("mlog", "hihihih");
        startActivity(new Intent(MainActivity.this, CheckOrders.class));
    }
}