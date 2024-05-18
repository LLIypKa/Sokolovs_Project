package com.example.lab13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "metal_structures";

    public static final String TABLE_METAL_STRUCTURES = "allStrucures";
    public static final String METAL_STRUCTURES_ID = "allStructures_ID";
    public static final String METAL_STRUCTURES_NAME = "allStructures_Name";
    public static final String METAL_STRUCTURES_COLOR = "allStructures_Color";

    public static final String TABLE_WAREHOUSES_STOCKS = "warehouseStocks";
    public static final String WAREHOUSES_STOCKS_ID = "warehouseStocks_ID";
    public static final String WAREHOUSES_STOCKS_METAL_ID = "warehouseStocks_MetalID";
    public static final String WAREHOUSES_STOCKS_METAL_COUNT = "warehouseStocks_MetalCount";
    public static final String WAREHOUSES_STOCKS_UPDATE_DATE = "warehouseStocks_UpdateDate";

    public static final String TABLE_SUPPLIERS = "suppliers";
    public static final String SUPPLIERS_ID = "suppliers_ID";
    public static final String SUPPLIERS_NAME = "suppliers_Name";

    public static final String TABLE_SUPPLIES = "supplies";
    public static final String SUPPLIES_ID = "supplies_ID";
    public static final String SUPPLIES_SUPPLIER_ID = "supplies_Supplier";
    public static final String SUPPLIES_METAL_ID = "supplies_MetalID";
    public static final String SUPPLIES_METAL_COUNT = "supplies_MetalCount";
    public static final String SUPPLIES_DATE = "supplies_Date";

    public static final String TABLE_CLIENTS = "clients";
    public static final String CLIENTS_ID = "clients_ID";
    public static final String CLIENTS_NAME = "clients_Name";

    //Orders
    public static final String TABLE_ORDERS = "orders";
    public static final String ORDERS_ID = "orders_ID";
    public static final String ORDERS_CLIENT_ID = "orders_Client";
    public static final String ORDERS_METAL_ID = "orders_MetalID";
    public static final String ORDERS_METAL_COUNT = "orders_MetalCount";
    public static final String ORDERS_DATE = "orders_Date";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String createMetalStructures = String.format("create table %s(%s integer primary key, %s text, %s text)"
            , TABLE_METAL_STRUCTURES, METAL_STRUCTURES_ID, METAL_STRUCTURES_NAME,  METAL_STRUCTURES_COLOR);
        String createWarehousesStocks = String.format("create table %s(%s integer primary key, %s integer, %s integer, %s text, FOREIGN KEY (%s) REFERENCES %s(%s))"
                , TABLE_WAREHOUSES_STOCKS, WAREHOUSES_STOCKS_ID, WAREHOUSES_STOCKS_METAL_ID, WAREHOUSES_STOCKS_METAL_COUNT
                , WAREHOUSES_STOCKS_UPDATE_DATE, WAREHOUSES_STOCKS_METAL_ID, TABLE_METAL_STRUCTURES, METAL_STRUCTURES_ID);
        String createSuppliers = String.format("create table %s(%s integer primary key, %s text)"
            , TABLE_SUPPLIERS, SUPPLIERS_ID, SUPPLIERS_NAME);
        String createSupplies = String.format("create table %s(%s integer primary key, %s int, %s int, %s int, %s text, foreign key (%s) references %s(%s), foreign key (%s) references %s(%s))"
            ,TABLE_SUPPLIES, SUPPLIES_ID, SUPPLIES_METAL_ID, SUPPLIES_SUPPLIER_ID, SUPPLIES_METAL_COUNT, SUPPLIES_DATE, SUPPLIES_METAL_ID, TABLE_METAL_STRUCTURES, METAL_STRUCTURES_ID, SUPPLIES_SUPPLIER_ID, TABLE_SUPPLIERS, SUPPLIERS_ID);
        String createClients = String.format("create table %s(%s integer primary key, %s text)"
                , TABLE_CLIENTS, CLIENTS_ID, CLIENTS_NAME);
        String createOrders = String.format("create table %s(%s integer primary key, %s int, %s int, %s int, %s text, foreign key (%s) references %s(%s), foreign key (%s) references %s(%s))"
                , TABLE_ORDERS, ORDERS_ID, ORDERS_CLIENT_ID, ORDERS_METAL_ID, ORDERS_METAL_COUNT, ORDERS_DATE, ORDERS_CLIENT_ID, TABLE_CLIENTS, CLIENTS_ID, ORDERS_METAL_ID, TABLE_METAL_STRUCTURES, METAL_STRUCTURES_ID);

        db.execSQL(createMetalStructures);
        db.execSQL(createWarehousesStocks);
        db.execSQL(createSuppliers);
        db.execSQL(createSupplies);
        db.execSQL(createClients);
        db.execSQL(createOrders);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_METAL_STRUCTURES);
        db.execSQL("drop table if exists " + TABLE_WAREHOUSES_STOCKS);
        db.execSQL("drop table if exists " + TABLE_SUPPLIERS);
        db.execSQL("drop table if exists " + TABLE_SUPPLIES);
        db.execSQL("drop table if exists " + TABLE_CLIENTS);
        db.execSQL("drop table if exists " + TABLE_ORDERS);

        onCreate(db);
    }
}
