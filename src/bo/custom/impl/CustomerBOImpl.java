package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;
import dto.Customer;
import entity.Customers;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
   private CustomerDao customerDao = new CustomerDaoImpl();

   @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
       ArrayList<Customer>allCustomer=new ArrayList<>();
       ArrayList<Customers> all = customerDao.getAll();
       for (Customers c1 : all) {
           allCustomer.add(new Customer(c1.getId(),c1.getTitle(),c1.getName(),c1.getAddress(),c1.getCity(),c1.getProvince(),c1.getPostalcode()));
       }
       return allCustomer;
   }

    @Override
    public boolean addCustomer(Customer c1) throws SQLException, ClassNotFoundException {
        return customerDao.add(new Customers(c1.getId(),c1.getTitle(),c1.getName(),c1.getAddress(),c1.getCity(),c1.getProvince(),c1.getPostalcode()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public boolean updateCustomer(Customer c1) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customers(c1.getId(),c1.getTitle(),c1.getName(),c1.getAddress(),c1.getCity(),c1.getProvince(),c1.getPostalcode()));
    }

    @Override
    public Customers searchCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDao.search(customerId);
    }
}
