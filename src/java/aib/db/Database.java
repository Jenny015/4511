/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aib.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 230268178
 */
public class Database {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public Database(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        //System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }
    
    public boolean doAuthenticate(String empID, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        
        try {
            cnnct = getConnection();
            System.out.println(cnnct);
            String preQueryStatement = "SELECT * FROM emp "
                                     + "WHERE empID = ? AND pwd = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, empID);
            pStmnt.setString(2, pwd);
            System.out.println(pStmnt);
            ResultSet rs = pStmnt.executeQuery();
            if(rs.next()){
                isValid = true;
            }
            pStmnt.close();
            cnnct.close();
        }catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        
        return isValid;
    }
    
    /* For reference
    public boolean addRecord(String CustId, String Name, String Tel, int Age){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO CUSTOMER VALUES (?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, CustId);
            pStmnt.setString(2, Name);
            pStmnt.setString(3, Tel);
            pStmnt.setInt(4, Age);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public CustomerBean queryCustByID(String id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        CustomerBean cb = null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER WHERE CUSTID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if(rs.next()){
                cb = new CustomerBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex= ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return cb;
    }
    
    public ArrayList<CustomerBean> queryCustByName(String name){
        ArrayList<CustomerBean> custs = new ArrayList<CustomerBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        CustomerBean cb = null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER WHERE NAME LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "%"+name+"%");
            ResultSet rs = pStmnt.executeQuery();
            while(rs.next()){
                cb = new CustomerBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                custs.add(cb);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex= ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return custs;
    }
    
    public ArrayList<CustomerBean> selectAll(){
        ArrayList<CustomerBean> custs = new ArrayList<CustomerBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        CustomerBean cb = null;
        try{
            cnnct = getConnection();
            pStmnt = cnnct.prepareStatement("SELECT * FROM CUSTOMER");
            ResultSet rs = pStmnt.executeQuery();
            while(rs.next()){
                cb = new CustomerBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                custs.add(cb);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex= ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return custs;
    }
    public boolean delRecord(String CustId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM CUSTOMER WHERE CUSTID = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, CustId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean editRecord(String CustId, String name, String tel, int age){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE CUSTOMER SET NAME = ?, TEL = ?, AGE = ? WHERE CUSTID = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, tel);
            pStmnt.setInt(3, age);
            pStmnt.setString(4, CustId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }*/
}
