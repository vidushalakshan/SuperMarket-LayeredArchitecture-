package bo.custom.impl;

import bo.custom.ItemBO;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.Item;
import entity.Items;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
   ItemDao itemDao = new ItemDaoImpl();

   @Override
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
       ArrayList<Item>allItem=new ArrayList<>();
       ArrayList<Items> all = itemDao.getAll();
       for (Items i : all) {
           allItem.add(new Item(i.getItemmcode(),i.getDescription(),i.getPacksize(),i.getUniteprice(),i.getQtyonhand()));
       }
       return allItem;
   }

    @Override
    public Items searchItem(String itemID) throws SQLException, ClassNotFoundException {
        return itemDao.search(itemID);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDao.delete(id);
    }

    @Override
    public boolean addItem(Item i) throws SQLException, ClassNotFoundException {
        return itemDao.add(new Items(i.getItemmcode(),i.getDescription(),i.getPacksize(),i.getUniteprice(),i.getQtyonhand()));
    }
}
