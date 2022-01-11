package dto;

import java.util.ArrayList;

public class Order {
    private String oderID;
    private String cutID;
    private String oderDate;
    private double cost;
    private ArrayList<OrderDetails> items;

    public Order(String string, String rstString, String s, double aDouble) {
    }

    public Order(String oderID, String cutID, String oderDate, double cost, ArrayList<OrderDetails> items) {
        this.oderID = oderID;
        this.cutID = cutID;
        this.oderDate = oderDate;
        this.cost = cost;
        this.items = items;
    }

    public String getOderID() {
        return oderID;
    }

    public void setOderID(String oderID) {
        this.oderID = oderID;
    }

    public String getCutID() {
        return cutID;
    }

    public void setCutID(String cutID) {
        this.cutID = cutID;
    }

    public String getOderDate() {
        return oderDate;
    }

    public void setOderDate(String oderDate) {
        this.oderDate = oderDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<OrderDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderDetails> items) {
        this.items = items;
    }
}
