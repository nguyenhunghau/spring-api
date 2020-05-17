package model;

import java.io.Serializable;

public class Inventory implements Serializable {
    private String productName;
    private int receivedTotal;
    private int deliveryTotal;
    private int balance;

    public Inventory(String productName, int receivedTotal, int deliveryTotal, int balance) {
        this.productName = productName;
        this.receivedTotal = receivedTotal;
        this.deliveryTotal = deliveryTotal;
        this.balance = balance;
    }
}
