package com.example.lab13.Classes.WarehouseModel;

public class Warehouse {
    public Integer RecordId;
    public Integer MetalStructureId;
    public Integer MetalStructureCount;
    public String UpdateDate;

    public Warehouse(Integer id, Integer metalId, Integer metalCount, String date) {
        this.RecordId = id;
        this.MetalStructureId = metalId;
        this.MetalStructureCount = metalCount;
        this.UpdateDate = date;
    }
}
