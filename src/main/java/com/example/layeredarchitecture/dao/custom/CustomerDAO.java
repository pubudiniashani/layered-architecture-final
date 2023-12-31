package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO> {
    /* ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
     boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
     void updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
     void deleteCustomer(String id) throws SQLException, ClassNotFoundException ;
     boolean existsCustomer(String id) throws SQLException, ClassNotFoundException ;
     String generateNewId() throws SQLException, ClassNotFoundException;

     CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException;*/

     ArrayList<CustomerDTO> loadAllCustomerIds() throws SQLException, ClassNotFoundException;
}
