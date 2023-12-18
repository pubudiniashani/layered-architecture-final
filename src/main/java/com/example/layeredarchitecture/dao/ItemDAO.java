package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

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



}
