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
public class TestQueryCustByTel {
    public static void main(String[] arg){
        CustomerDB custDb = TestCreateCustT.conn();
        System.out.println(custDb.queryCustByTel("12345688").toString());
    }
}
