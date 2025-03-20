/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.bean.CustomerBean;
import ict.db.CustomerDB;
import java.util.ArrayList;

/**
 *
 * @author 230268178
 */
public class TestQueryCustByName {
    public static void main(String[] arg){
        CustomerDB custDb = TestCreateCustT.conn();
        ArrayList<CustomerBean> custs = custDb.queryCustByName("a");
        for(CustomerBean cust : custs){
            System.out.println(cust.toString()+"\n");
            
        } 
    }
}
