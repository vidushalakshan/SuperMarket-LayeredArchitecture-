package bo.custom.impl;

import bo.custom.PurchaseOrderBo;
import dao.*;
import dao.custom.CustomerDao;
import dao.custom.ItemDao;
import dao.custom.OrderDao;
import db.DBConnection;
import dto.Customer;
import dto.Item;
import dto.Order;
import dto.OrderDetails;
import entity.Customers;
import entity.Items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PurchaseOrderBOImpl implements PurchaseOrderBo {
    private CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private OrderDao orderDao = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean placeOrder(Order order) throws SQLException, ClassNotFoundException {

        Connection con=null;

            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean b = CrudUtil.excuteUpdate("INSERT INTO `Order` VALUES(?,?,?,?)", order.getOderID(), order.getCutID(), order.getOderDate(), order.getCost());

            if (b){

                if (saveOrderDetail(order.getOderID(), order.getItems())){
                    con.commit();
                    return true;
                }else{
                    con.rollback();
                    return false;
                }
            }else{
                con.rollback();
                return false;
            }

    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {

         return orderDao.generateNewOrderId();
    }

    @Override
    public List<String> loadItemIDS() throws SQLException, ClassNotFoundException {
        return itemDao.loadItemIDS();
    }

    @Override
    public List<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDao.loadCustomerIds();
    }

    @Override
    public Customers searchCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDao.search(customerId);
    }

    @Override
    public Items searchItems(String itemCode) throws SQLException, ClassNotFoundException {
        return itemDao.search(itemCode);
    }

    private boolean saveOrderDetail(String orderId, ArrayList<OrderDetails> items) throws SQLException, ClassNotFoundException {
        for (OrderDetails temp : items
        ) {
            boolean stm = CrudUtil.excuteUpdate("INSERT INTO `Order Detail` VALUES(?,?,?,?)", temp.getItemCode(), orderId, temp.getQtyForSell(), temp.getUnitPrice());
            if (stm) {

                if (updateQty(temp.getItemCode(), temp.getQtyForSell())){

                }else{
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }
    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm =DBConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET qtyOnHand=(qtyOnHand-" + qty + ") WHERE code='" + itemCode + "'");
        return stm.executeUpdate()>0;
    }

}
