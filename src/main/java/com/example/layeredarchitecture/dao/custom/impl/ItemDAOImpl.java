package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<ItemDTO> getAllItem = new ArrayList<>();

        while (rst.next()) {
            ItemDTO itemDTO = new ItemDTO(rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand"));

            getAllItem.add(itemDTO);
        }
        return getAllItem;


    }

    @Override
    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException {

     return    SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),
                dto.getDescription(), dto.getUnitPrice(),dto.getQtyOnHand());

       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");

        pstm.setString(1, dto.getCode());
        pstm.setString(2, dto.getDescription());
        pstm.setBigDecimal(3, dto.getUnitPrice());
        pstm.setInt(4, dto.getQtyOnHand());

        return pstm.executeUpdate() > 0;*/
    }

    @Override
    public void update(ItemDTO dto) throws SQLException, ClassNotFoundException {

        SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                dto.getDescription(), dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());

        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, dto.getDescription());
        pstm.setBigDecimal(2, dto.getUnitPrice());
        pstm.setInt(3, dto.getQtyOnHand());
        pstm.setString(4, dto.getCode());
        pstm.executeUpdate();*/

    }

    @Override
    public void delete(String code) throws SQLException, ClassNotFoundException {

        SQLUtil.execute("DELETE FROM Item WHERE code=?",code);

       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        pstm.executeUpdate();*/
    }

    @Override
    public boolean exists(String code) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT code FROM Item WHERE code=?", code);
        return rst.next();

        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
      //  Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException {


       // Connection connection = DBConnection.getDbConnection().getConnection();
       /* PreparedStatement pstm =SQLUtil.execute("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");*/

        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",newItemCode + "");
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"),
                rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

        return item;
    }

    @Override
    public ArrayList<ItemDTO> loadAllItemIds() throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/

        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<ItemDTO> getAllItemIds = new ArrayList<>();

        while (rst.next()) {
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString("code"), rst.getString("description"),
                    rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand")

            );

            getAllItemIds.add(itemDTO);
        }

        return getAllItemIds;
    }

    @Override
    public ItemDTO findItem(String newItemCode) throws SQLException, ClassNotFoundException {

        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
       */

        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",newItemCode + "");
        rst.next();

        ItemDTO item = new ItemDTO(
                newItemCode + "", rst.getString("description"),
                rst.getBigDecimal("unitPrice"),
                rst.getInt("qtyOnHand")
        );

        return item;

    }

    @Override
    public boolean updateItemQuantity(List<OrderDetailDTO> orderDetail) throws SQLException, ClassNotFoundException {

        int count = 0;

        for (OrderDetailDTO detail : orderDetail) {
            ItemDTO item = findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            /*Connection connection = DBConnection.getDbConnection().getConnection();

            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
            pstm.setString(1, item.getDescription());
            pstm.setBigDecimal(2, item.getUnitPrice());
            pstm.setInt(3, item.getQtyOnHand());
            pstm.setString(4, item.getCode());

            pstm.executeUpdate();*/

            SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                    item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getCode());
            count++;

        }
        return count == orderDetail.size();

    }
}
