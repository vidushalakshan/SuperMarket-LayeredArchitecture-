package dao.custom;

import dao.CrudDao;
import dto.OrderForm;
import dto.Order;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao extends CrudDao<Orders,String> {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException;
    public ArrayList<OrderForm> getAllOrders() throws SQLException, ClassNotFoundException;
}
