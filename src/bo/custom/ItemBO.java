package bo.custom;

import bo.SuperBO;
import dto.Item;
import entity.Items;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException;

    public Items searchItem(String itemID) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    public boolean addItem(Item i1) throws SQLException, ClassNotFoundException;
}
