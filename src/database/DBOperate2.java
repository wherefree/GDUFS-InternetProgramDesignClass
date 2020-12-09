/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Statement;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gdufs
 */
public class DBOperate2 {
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, UnsupportedEncodingException{
        Class jdbcDriver=Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.DriverManager.registerDriver((Driver)jdbcDriver.newInstance());
        Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://202.116.195.81:3306/STUDENTDB1?serverTimezone=UTC","myuser1","mysql1");
        Statement stmt;
        stmt =con.createStatement();
        String sName=new String("古镇豪".getBytes("GB2312"),"ISO-8859-1");
        //String sName=new String("古镇豪");
        //String sClass=new String("网安1801".getBytes("GB2312"),"ISO-8859-1");
        String sClass=new String("1801");
        String sql;
        //sql="insert into STUDENTS (NO,NAME,AGE,CLASS)" +"VALUES ('20181002828','"+sName+"',20,'"+sClass+"')";
        //System.out.println(sql);
       //stmt.executeUpdate(sql);
       sql="insert into STUDENTS (NO,NAME,AGE,CLASS,IP) " +"VALUES ('20181002828','"+sName+"',20,'"+sClass+"', '"+"192.168.207.146"+"')";
        //stmt.executeUpdate(sql);
        ResultSet rs= stmt.executeQuery("SELECT NO,NAME,AGE,CLASS,IP from STUDENTS");//从表中查询
        while (rs.next()){
            String sNo2=rs.getString(1);
            String sName2 = rs.getString(2);//将数据库的字段值赋值给程序变量sName2
            // String sName2 = rs.getString(STUDENTS.NAME);//另一种表示
            int age2 = rs.getInt(3);
            String sClass2=rs.getString(4);
            String IP2=rs.getString(5);
            sName2= new String(sName2.getBytes("ISO-8859-1"),"GB2312");//数据库中汉字编码换装成操作系统表示的汉字编码
            //打印所显示的数据
            System.out.println("NO="+sNo2+",name="+sName2+",age="+age2+",class="+sClass2+",IP="+IP2);
   } 
    }
}
