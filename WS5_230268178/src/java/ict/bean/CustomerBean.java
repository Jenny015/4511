/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author 230268178
 */
public class CustomerBean {
    private String custId;
    private String name;
    private String tel;
    private int age;
    
    public CustomerBean(){}
    public CustomerBean(String id, String name, String tel, int age){
        custId = id;
        this.name = name;
        this.tel = tel;
        this.age = age;
    }
    
    public String getCustId(){ return custId;}
    public String getName(){ return name;}
    public String getTel(){ return tel;}
    public int getAge(){ return age;}
    
    public void setCustId(String custId){ this.custId = custId;}
    public void setName(String name){this.name = name;}
    public void setTel(String tel){this.tel = tel;}
    public void setAge(int age){this.age = age;}
    
    public String toString(){
        return "ID: "+custId+"\nName: "+name+"\nTel: "+tel+"\nAge: "+age;
    }
}
