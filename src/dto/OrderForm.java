package dto;

public class OrderForm {
    private String oderID;
    private String cutID;
    private String oderDate;
    private double cost;

    public OrderForm() {
    }

    public OrderForm(String oderID, String cutID, String oderDate, double cost) {
        this.setOderID(oderID);
        this.setCutID(cutID);
        this.setOderDate(oderDate);
        this.setCost(cost);
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

    @Override
    public String toString() {
        return "OrderForm{" +
                "oderID='" + oderID + '\'' +
                ", cutID='" + cutID + '\'' +
                ", oderDate='" + oderDate + '\'' +
                ", cost=" + cost +
                '}';
    }
}
