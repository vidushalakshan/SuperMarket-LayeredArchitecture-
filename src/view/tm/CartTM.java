package view.tm;

public class CartTM {
    private String id;
    private String description;
    private int qty;
    private double unitePrice;
    private double total;

    public CartTM() {
    }

    public CartTM(String id, String description, int qty, double unitePrice, double total) {
        this.id = id;
        this.description = description;
        this.qty = qty;
        this.unitePrice = unitePrice;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(double unitePrice) {
        this.unitePrice = unitePrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", unitePrice=" + unitePrice +
                ", total=" + total +
                '}';
    }
}
