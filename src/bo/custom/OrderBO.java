package bo.custom;

import bo.SuperBO;
import dto.OrderForm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public ArrayList<OrderForm> getAllOrder() throws SQLException, ClassNotFoundException;

    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException;
}
