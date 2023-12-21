package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {


    @Override
    public void orderOderDetail() {
        String sql = (" SELECT * FROM Orders o RIGHT JOIN OrderDetails od ON o.oid = od.oid");

        try {
            ResultSet rst = SQLUtil.execute(sql);

            while (rst.next()){

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
