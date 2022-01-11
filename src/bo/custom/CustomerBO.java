package bo.custom;

import bo.SuperBO;
import dto.Customer;
import entity.Customers;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException ;

    public boolean addCustomer(Customer c1) throws SQLException, ClassNotFoundException ;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(Customer c1) throws SQLException, ClassNotFoundException;

    public Customers searchCustomer(String customerId) throws SQLException, ClassNotFoundException;
}
