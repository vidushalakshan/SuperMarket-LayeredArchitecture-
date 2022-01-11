package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailsDao;
import dto.OrderDetails;
import entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean add(OrderDetail orderDetails) throws SQLException, ClassNotFoundException {
      /* return CrudUtil.excuteUpdate("INSERT INTO `Order Detail` VALUES(?,?,?,?)", orderDetails.getItemCode(), orderDetails.getOderID(), orderDetails.getQtyForSell(), orderDetails.getUnitPrice());*/
        return CrudUtil.excuteUpdate("INSERT INTO `Order Detail` VALUES(?,?,?,?)");
    }

    @Override
    public OrderDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
