package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean checkOrderExist(String orderId) {
        return false;
    }


    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/

        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-",
                "")) + 1)) : "OID-001";
    }

    @Override
    public OrderDTO search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

   /* public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm;
        connection.setAutoCommit(false);
        stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderId);
        stm.setDate(2, Date.valueOf(orderDate));
        stm.setString(3, customerId);

        if (stm.executeUpdate() != 1) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        OrderDetailDAOImpl orderDetailDAO = new OrderDetailDAOImpl();
        boolean isOrderDetailSaved = orderDetailDAO.saveOrderDetails(orderDetails,orderId);

        if(!isOrderDetailSaved){
            connection.rollback();
            connection.setAutoCommit(true);
            return true;
        }

        *//*Connection connection = null;

        boolean isSaved = false;

        ArrayList<OrderDetailDTO> dto = new ArrayList<>();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

        try {
            connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Orders (oid, date, customerID) VALUES (?,?,?)");
            connection.setAutoCommit(false);
            stm.setString(1, orderId);
            stm.setDate(2, Date.valueOf(orderDate));
            stm.setString(3, customerId);
            isSaved = stm.executeUpdate() > 0;
            if (isSaved) {
                connection.commit();
                return false;

            } else {
                connection.rollback();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

        return isSaved;*//*
        return false;
    }
*/



    @Override
    public boolean exists(String orderId) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);

      /*  Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
            stm.setString(1, orderId);*/
            /*if order id already exist*/

            if (rst.next()){
                return false;
            }
            return true;
    }

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {

       return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",orderDTO.getOrderId(),
                orderDTO.getOrderDate(),orderDTO.getCustomerId());

        /*Connection connection = DBConnection.getDbConnection().getConnection();

       PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderDTO.getOrderId());
        stm.setDate(2, Date.valueOf(orderDTO.getOrderDate()));
        stm.setString(3, orderDTO.getCustomerId());
*/
       /* if (stm.executeUpdate() != 1) {
          //  connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
*/
      //  return stm.executeUpdate() > 0;

    }

    @Override
    public void update(OrderDTO dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

}
