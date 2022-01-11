package bo;

import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.ItemBOImpl;
import bo.custom.impl.OrderBOImpl;
import bo.custom.impl.PurchaseOrderBOImpl;
import dao.custom.impl.OrderDaoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {

    }

    public static  BOFactory getBoFactory(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PURCHASEORDER,ORDER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case ITEM:
                return new ItemBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case PURCHASEORDER:
                return new PurchaseOrderBOImpl();
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }
}
