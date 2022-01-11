package view.tm;

public class ItemTM {
     private String itemmcode;
     private String description;
     private String packsize;
     private String uniteprice;
     private String qtyonhand;

    public ItemTM(String itemmcode, String description, String packsize, String uniteprice, String qtyonhand) {
        this.itemmcode = itemmcode;
        this.description = description;
        this.packsize = packsize;
        this.uniteprice = uniteprice;
        this.qtyonhand = qtyonhand;
    }

    public ItemTM(String itemmcode, String description, String packsize, int qtyonhand, String uniteprice) {
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

    public String getQtyonhand() {
        return qtyonhand;
    }

    public void setQtyonhand(String qtyonhand) {
        this.qtyonhand = qtyonhand;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "itemmcode='" + itemmcode + '\'' +
                ", description='" + description + '\'' +
                ", packsize='" + packsize + '\'' +
                ", uniteprice='" + uniteprice + '\'' +
                ", qtyonhand='" + qtyonhand + '\'' +
                '}';
    }
}
