package dao.custom.impl;


import dao.CrudUtil;
import dao.custom.ItemDao;
import dto.Item;
import entity.Items;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean add(Items item) throws SQLException, ClassNotFoundException {
        return CrudUtil.excuteUpdate("INSERT INTO Item VALUES(?,?,?,?,?)",item.getItemmcode(),item.getDescription(),item.getPacksize(),
                item.getUniteprice(),item.getQtyonhand());
    }

    @Override
    public Items search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.excuteQuery("SELECT * FROM Item WHERE code=?", id);
        rst.next();
        return new Items(rst.getString(1),rst.getString(2),rst.getString(3),
                rst.getString(4),rst.getInt(5));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.excuteUpdate("DELETE FROM Item WHERE code=?",id);
    }

    @Override
    public boolean update(Items item) throws SQLException, ClassNotFoundException {
        return CrudUtil.excuteUpdate("UPDATE item SET description=? , packSize=? , unitPrice=? , qtyOnHand=? WHERE code=? ",item.getDescription(),item.getPacksize(),
                item.getUniteprice(),item.getQtyonhand(),item.getItemmcode());
    }

    @Override
    public ArrayList<Items> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.excuteQuery("SELECT * FROM item");
        ArrayList<Items> item=new ArrayList<>();
        while (rst.next()) {
            item.add(new Items(
                    rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5)
            ));

        }
        return item;
    }

    @Override
    public List<String> loadItemIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.excuteQuery("SELECT * FROM Item");
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;
    }
}
