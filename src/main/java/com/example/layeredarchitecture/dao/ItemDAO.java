package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO {
     ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException ;
     boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;
     void updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;
     void deleteItem(String code) throws SQLException, ClassNotFoundException;
     boolean existItem(String code) throws SQLException, ClassNotFoundException ;
     String generateNewId() throws SQLException, ClassNotFoundException ;

     ItemDTO searchItem(String newItemCode) throws SQLException, ClassNotFoundException;
     ArrayList<ItemDTO> loadAllItemIds() throws SQLException, ClassNotFoundException;
     ItemDTO findItem(String newItemCode) throws SQLException, ClassNotFoundException;

     boolean updateItemQuantity(List<OrderDetailDTO> orderDetail) throws SQLException, ClassNotFoundException;



}
