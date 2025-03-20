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
public class TestEditRecord {
    static CustomerDB custDb;
    public static void main(String[] arg){
        custDb = TestCreateCustT.conn();
        System.out.println(edit("1", "New Peter", "88654321", 81));
    }
    
    public static String edit(String id, String name, String tel, int age){
        if(custDb.editRecord(id, name, tel, age)){
            return "Customer "+id+" is edited.";
        } else {
            return "Customer "+id+ "is not edited.";
        }
    }
}
