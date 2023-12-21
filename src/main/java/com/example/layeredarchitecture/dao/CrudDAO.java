package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException ;
    void update(T dto) throws SQLException, ClassNotFoundException ;
    void delete(String id) throws SQLException, ClassNotFoundException ;
    boolean exists(String id) throws SQLException, ClassNotFoundException ;
    String generateNewId() throws SQLException, ClassNotFoundException;

    T search(String newValue) throws SQLException, ClassNotFoundException;
}
