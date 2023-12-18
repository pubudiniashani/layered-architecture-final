package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    boolean checkOrderExist(String orderId);
    boolean saveOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetails);
    String generateNewOrderId() throws SQLException, ClassNotFoundException;


}
