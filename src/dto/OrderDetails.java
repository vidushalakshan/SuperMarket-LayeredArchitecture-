package dto;

public class OrderDetails {
    private String itemCode;
    private double unitPrice;
    private int qtyForSell;

    public OrderDetails() {
    }

    public OrderDetails(String itemCode, double unitPrice, int qtyForSell) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qtyForSell = qtyForSell;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }
}
