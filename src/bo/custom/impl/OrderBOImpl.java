package bo.custom.impl;

import bo.custom.OrderBO;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderForm;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public ArrayList<OrderForm> getAllOrder() throws SQLException, ClassNotFoundException {
        return orderDao.getAllOrders();
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDao.delete(id);
    }

    @Override
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException {
        return orderDao.ifOrderExist(oid);
    }
}
