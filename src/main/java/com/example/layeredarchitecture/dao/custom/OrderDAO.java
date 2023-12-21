package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrderDAO extends CrudDAO<OrderDTO> {

    boolean checkOrderExist(String orderId);

    /*String generateNewOrderId() throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    boolean existId(String orderId) throws SQLException, ClassNotFoundException;*/


}
