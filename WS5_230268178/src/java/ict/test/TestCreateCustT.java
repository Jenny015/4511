/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;
import ict.db.CustomerDB;
/**
 *
 * @author 230268178
 */
public class TestCreateCustT {
    public static void main(String[] arg){
        CustomerDB custDb = conn();
        custDb.createCustTable();
        
    }
    public static CustomerDB conn(){
        String url = "jdbc:mysql://localhost:3306/ITP4511_db";
        String username = "root";
        String password = "";
        return new CustomerDB(url, username, password);
    }
}
