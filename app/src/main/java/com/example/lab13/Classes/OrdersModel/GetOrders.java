package com.example.lab13.Classes.OrdersModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GetOrders {
    private static List<Order> Orders = new ArrayList<>();

    public static List<Order> GetOrders() {
        Orders.add(new Order(0, 0, 1, 200, LocalDate.now().plus(1, ChronoUnit.DAYS)));
        Orders.add(new Order(0, 1, 2, 200, LocalDate.now().plus(2, ChronoUnit.DAYS)));
        Orders.add(new Order(0, 0, 3, 200, LocalDate.now().plus(3, ChronoUnit.DAYS)));
        Orders.add(new Order(0, 1, 4, 200, LocalDate.now().plus(4, ChronoUnit.DAYS)));

        return Orders;
    }
}
