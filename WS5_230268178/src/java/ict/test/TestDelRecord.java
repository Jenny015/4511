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
public class TestDelRecord {
    static CustomerDB custDb;
    public static void main(String[] arg){
        custDb = TestCreateCustT.conn();
        System.out.println(delete("3"));
    }
    
    public static String delete(String id){
        if(custDb.delRecord(id)){
            return "Customer "+id+" is deleted.";
        } else {
            return "Customer "+id+ "is not deleted";
        }
    }
}
