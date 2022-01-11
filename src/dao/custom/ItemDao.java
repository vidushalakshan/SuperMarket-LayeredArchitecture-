package dao.custom;

import dao.CrudDao;
import dto.Item;
import entity.Items;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends CrudDao<Items,String> {
    public List<String> loadItemIDS() throws SQLException, ClassNotFoundException;
}
