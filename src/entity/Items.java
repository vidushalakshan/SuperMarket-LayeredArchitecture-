package entity;

public class Items {
    private String itemmcode;
    private String description;
    private String packsize;
    private String uniteprice;
    private int qtyonhand;

    public Items() {
    }


    public Items(String itemmcode, String description, String packsize, String uniteprice, int qtyonhand) {
        this.setItemmcode(itemmcode);
        this.setDescription(description);
        this.setPacksize(packsize);
        this.setUniteprice(uniteprice);
        this.setQtyonhand(qtyonhand);
    }

    public String getItemmcode() {
        return itemmcode;
    }

    public void setItemmcode(String itemmcode) {
        this.itemmcode = itemmcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPacksize() {
        return packsize;
    }

    public void setPacksize(String packsize) {
        this.packsize = packsize;
    }

    public String getUniteprice() {
        return uniteprice;
    }

    public void setUniteprice(String uniteprice) {
        this.uniteprice = uniteprice;
    }

    public int getQtyonhand() {
        return qtyonhand;
    }

    public void setQtyonhand(int qtyonhand) {
        this.qtyonhand = qtyonhand;
    }
}
