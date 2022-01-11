package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDao;
import dto.OrderForm;
import dto.Order;
import entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean add(Orders order) throws SQLException, ClassNotFoundException {
        return CrudUtil.excuteUpdate("INSERT INTO `Order` VALUES(?,?,?,?)",order.getOderID(),order.getCutID(),order.getOderDate(),order.getCost());
    }

    @Override
    public Orders search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.excuteUpdate("DELETE FROM Item WHERE code=?", id);
    }

    @Override
    public boolean update(Orders order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {


        ResultSet rst = CrudUtil.excuteQuery("SELECT * FROM `order`");
        ArrayList<Orders> pay=new ArrayList<>();
        while (rst.next()) {
            pay.add(new Orders(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            ));

        }
        return pay;
    }

    @Override
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.excuteQuery("SELECT orderId FROM `Order` WHERE orderId=?", oid);
        return rst.next();
    }

    @Override
    public ArrayList<OrderForm> getAllOrders() throws SQLException, ClassNotFoundException {
         ResultSet rst = CrudUtil.excuteQuery("SELECT * FROM `order`");
        ArrayList<OrderForm> pay=new ArrayList<>();
        while (rst.next()) {
            pay.add(new OrderForm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            ));

        }
        return pay;
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.excuteQuery("SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1");
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<10){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }
        }else{
            return "O-001";
        }
    }
}
