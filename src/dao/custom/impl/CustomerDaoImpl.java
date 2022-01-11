package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDao;
import dto.Customer;
import entity.Customers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean add(Customers customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.excuteUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)",customer.getId(),customer.getTitle(),
                customer.getName(),customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalcode());
    }

    @Override
    public Customers search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.excuteQuery("SELECT * FROM Customer WHERE id=?", id);
        rst.next();
        return new Customers(rst.getString("id"),rst.getString("title"),rst.getString("name"),rst.getString("address"),rst.getString("city"),
                rst.getString("province"),rst.getString("postalcode"));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.excuteUpdate("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public boolean update(Customers customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.excuteUpdate("UPDATE Customer SET title=?, name=?, address=?, city=?,province=?,postalcode=?  WHERE id=?",customer.getTitle(),
                customer.getName(),customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalcode(),customer.getId());
    }

    @Override
    public ArrayList<Customers> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customers>allCustomer=new ArrayList<>();
        ResultSet rst = CrudUtil.excuteQuery("SELECT * FROM Customer");
        while (rst.next()) {
            allCustomer.add(new Customers(rst.getString("id"),rst.getString("title"),rst.getString("name"),rst.getString("address"),rst.getString("city"),
                    rst.getString("province"),rst.getString("postalcode")));
        }
        return allCustomer;
    }

    @Override
    public List<String> loadCustomerIds() throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.excuteQuery("SELECT * FROM Customer");
        List<String>ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }
        /*ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }

        return ids;
    }*/
}
