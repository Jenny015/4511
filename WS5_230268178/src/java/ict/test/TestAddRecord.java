 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;
import ict.db.CustomerDB;

/**
 *
 * @author tmlib
 */
public class TestAddRecord {
    static CustomerDB custDb;
    public static void main(String[] arg){
        custDb = TestCreateCustT.conn();
        System.out.println(add("1", "Peter", "12345688", 18));
        System.out.println(add("2", "Nancy", "12345678", 21));
        System.out.println(add("3", "Panda", "98765432", 17));
    }
    
    public static String add(String id, String name, String tel, int age){
        if(custDb.addRecord(id, name, tel, age)){
            return name+" is added";
        } else {
            return name+ "is not added";
        }
    }
}
