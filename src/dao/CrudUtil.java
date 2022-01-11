package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private static PreparedStatement test(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            pstm.setObject(i+1,args[i]);
        }

        return pstm;
    }
    public static ResultSet excuteQuery(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = test(sql, args);
        return pstm.executeQuery();
    }

    public static boolean excuteUpdate(String sql,Object... args) throws SQLException, ClassNotFoundException {
        return test (sql, args).executeUpdate()>0;

    }
}
