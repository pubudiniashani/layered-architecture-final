package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> getAllCustomer = new ArrayList<>();

        while (rst.next()){
         CustomerDTO customerDTO =  new CustomerDTO(rst.getString("id"),
                    rst.getString("name"), rst.getString("address"));
         //mehema tibboth antima customerdto eka wtrk ithuruwena nisa eeka arraylist ekakt dgtta

            getAllCustomer.add(customerDTO);
        }
        return getAllCustomer ;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {

       return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),
                dto.getName(),dto.getAddress());


        /*PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());

       return pstm.executeUpdate()> 0;*/
    }

    @Override
    public void update(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                dto.getId(), dto.getName(),dto.getAddress());


      /*  PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());

        pstm.executeUpdate();
*/
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

        SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);

       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        pstm.executeUpdate();*/
    }

    @Override
    public boolean exists(String id) throws SQLException, ClassNotFoundException {

      ResultSet rst = SQLUtil.execute("SELECT id FROM Customer WHERE id=?",id);
      return rst.next();

     /*  Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();*/

    }


    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }

    }

    @Override
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {



       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue + "");*/

        ResultSet rst =SQLUtil.execute("SELECT * FROM Customer WHERE id=?",newValue + "");
        rst.next();

        CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"),
                rst.getString("address"));
        return customerDTO;
     }

    @Override
    public ArrayList<CustomerDTO> loadAllCustomerIds() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/

        ResultSet rst =SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<CustomerDTO> idList = new ArrayList<>();
        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(rst.getString("id"),
                    rst.getString("name"), rst.getString("address"));
            idList.add(customerDTO);
        }
        return idList;

    }


}
