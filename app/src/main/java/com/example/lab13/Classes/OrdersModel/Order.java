package com.example.lab13.Classes.OrdersModel;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {
    public Integer ID;
    public Integer ClientID;
    public Integer MetalID;
    public Integer MetalCount;
    public LocalDate Date;

    public Order(Integer id, Integer clientID, Integer metalID, Integer metalCount, LocalDate date) {
        this.ID = id;
        this.ClientID = clientID;
        this.MetalID = metalID;
        this.MetalCount = metalCount;
        this.Date = date;
    }
}
