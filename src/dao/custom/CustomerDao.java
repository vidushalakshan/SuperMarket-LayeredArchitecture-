package dao.custom;

import dao.CrudDao;
import dto.Customer;
import entity.Customers;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao extends CrudDao<Customers,String> {
    public List<String> loadCustomerIds() throws SQLException, ClassNotFoundException;
}
