package bo.custom;

import bo.SuperBO;
import dto.Customer;
import dto.Item;
import dto.Order;
import entity.Customers;
import entity.Items;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseOrderBo extends SuperBO {
    public boolean placeOrder(Order order) throws SQLException, ClassNotFoundException;
    String getOrderId() throws SQLException, ClassNotFoundException;

    List<String> loadItemIDS() throws SQLException, ClassNotFoundException;

    List<String> loadCustomerIds() throws SQLException, ClassNotFoundException;

    Customers searchCustomer(String customerId) throws SQLException, ClassNotFoundException;

    Items searchItems(String itemCode) throws SQLException, ClassNotFoundException;
}
