package entity;

public class OrderDetail {
    private String itemCode;
    private String orderID;
    private int qtyForSell;
    private double unitPrice;


    public OrderDetail() {
    }


    public OrderDetail(String itemCode, String orderID, int qtyForSell, double unitPrice) {
        this.setItemCode(itemCode);
        this.setOrderID(orderID);
        this.setQtyForSell(qtyForSell);
        this.setUnitPrice(unitPrice);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
