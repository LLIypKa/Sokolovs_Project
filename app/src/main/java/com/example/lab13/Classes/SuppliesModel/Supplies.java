package com.example.lab13.Classes.SuppliesModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Supplies implements Serializable {
    public Integer ID;
    public Integer SupplierID;
    public Integer MetalID;
    public Integer MetalCount;
    public LocalDate Date;

    public Supplies(Integer id, Integer supplierID, Integer metalID, Integer metalCount, LocalDate date) {
        this.ID = id;
        this.SupplierID = supplierID;
        this.MetalID = metalID;
        this.MetalCount = metalCount;
        this.Date = date;
    }
}
