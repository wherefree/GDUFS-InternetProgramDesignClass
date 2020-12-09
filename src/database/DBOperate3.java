/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.*;
import java.sql.*;

/**
 *
 * @author gdufs
 */
public class DBOperate3{

 private final ConnectionProvider provider;
 private Connection con=null;
 private Statement stmt=null;
 private ResultSet rs=null;

public DBOperate3(ConnectionProvider provider){
    this.provider=provider;    
  }
  public void addStudent(String sNo,String sName,int age,String sClass) throws SQLException, UnsupportedEncodingException{
     
       con=provider.getConnection();
       stmt=con.createStatement();
       sName=new String(sName.getBytes("GB2312"),"ISO-8859-1");
       sClass=new String(sClass.getBytes("GB2312"),"ISO-8859-1");
       String sql="insert into STUDENTS(NO,NAME,AGE,CLASS) values("
               + "'"+sNo+"'"+"," + "'"+sName+"'"+"," +age+"," + "'"+sClass+"'"+")";

      stmt.execute(sql);
    
      closeStatement();
      closeConnection();
    
  }
  
 //存二进制大数据文件到数据库表的Clob字段中.
  public void saveClobToDatabase(String fileName)throws Exception{
    con=provider.getConnection();
    PreparedStatement stmtp=con.prepareStatement("insert into ACLOB(ID,FILE) values(?,?) ");
    stmtp.setLong(1,1);
    FileInputStream fin=new FileInputStream(fileName);
    InputStreamReader reader=new InputStreamReader(fin);
    stmtp.setCharacterStream(2,reader,fin.available());
    stmtp.executeUpdate();
    reader.close();
    stmtp.close();
  }

  public void deleteStudent(String name) throws SQLException{
     try{
      con=provider.getConnection();
      stmt=con.createStatement();
      String sql="delete from CUSTOMERS where NAME='"+name+"'";
      stmt.execute(sql);
    }finally{
       closeStatement();
       closeConnection();
    }
  }

  public void printAllStudents() throws SQLException, UnsupportedEncodingException{
    
    try{
      con=provider.getConnection();
      stmt=con.createStatement();
      //查询记录
      rs= stmt.executeQuery("SELECT NO,NAME,AGE,CLASS from STUDENTS");
      //输出查询结果
      while (rs.next()){
        String sNo = rs.getString(1);
        String sName = new String((rs.getString(2)).getBytes("ISO-8859-1"),"GB2312");
        int age = rs.getInt(3);
        String sClass = rs.getString(4);

        //打印所显示的数据
        System.out.println("no="+sNo+",name="+sName+",age="+age+",address="+sClass);

      }
    }finally{
        closeAll();
    }
  }
public ResultSet DatabaseSet()throws SQLException, UnsupportedEncodingException{
    
      con=provider.getConnection();
      stmt=con.createStatement();
      //ID NO NAME AGE CLASS IP 
      String ID,NO,NAME,CLASS,IP;
      int AGE;
      ID="20181002828";
      NO="20181002828";
      NAME="古镇豪";
      AGE=20;
      CLASS="网络安全1801";
      IP="192.168.207.146";
      /*NAME=new String(NAME.getBytes("GB2312"),"ISO-8859-1");
      CLASS=new String(CLASS.getBytes("GB2312"),"ISO-8859-1");
      ID=new String(ID.getBytes("GB2312"),"ISO-8859-1");
      NO=new String(NO.getBytes("GB2312"),"ISO-8859-1");
      IP=new String(IP.getBytes("GB2312"),"ISO-8859-1");*/
      String sql="insert into peoples2(ID,NO,NAME,AGE,CLASS,IP) value('"+ID+"','"+NO+"','"+NAME+"','"+AGE+"','"+CLASS+"','"+IP+"') ";
      //sql=new String(sql.getBytes("GB2312"),"ISO-8859-1");
      System.out.println(sql);
      stmt.execute(sql);
      rs=stmt.executeQuery("select * from peoples2");
      ResultSetMetaData fields=rs.getMetaData();//会返回该表的字段信息
      int n=fields.getColumnCount();//会返回表中字段的数目
      for(int i=1;i<=n;i++){
          String s1=fields.getColumnLabel(i);
          System.out.print(s1+" ");
      }
      System.out.print('\n');
      while(rs.next()){
          for(int i=1;i<=n;i++){
              System.out.print(rs.getString(i)+" ");
             
          } 
          System.out.print('\n');
      }
      //查询记录
//      rs= stmt.executeQuery("SELECT NO,NAME,AGE,CLASS from STUDENTS");
      //输出查询结果

   return rs;
}

public void closeAll( ){
    try{
      if(rs!=null)rs.close();
      if(stmt!=null)stmt.close();
      if(con!=null)con.close();
    }catch(SQLException e){}
  }

private void closeResultSet( ){
    try{
      if(rs!=null)rs.close();
    }catch(SQLException e){}
  }

  private void closeStatement(){
    try{
      if(stmt!=null)stmt.close();
    }catch(SQLException e){}
  }

  private void closeConnection(){
    try{
      if(con!=null)con.close();
    }catch(SQLException e){}
  }

  public static void main(String args[])throws Exception{

    DBOperate3 tester=new DBOperate3(new ConnectionProvider());
    tester.DatabaseSet();
    //tester.addStudent("2009","小王五",40,"软件工程");
    //tester.printAllStudents();
  //  tester.deleteCustomer("小王");
  }
}
/*
TABLE mypeopledb null peoples1
TABLE mypeopledb null peoples2
TABLE mypeopledb null teachers
TABLE studentdb null courses
TABLE studentdb null students
TABLE studentdb null teachers
TABLE studentdb1 null courses
TABLE studentdb1 null students
TABLE studentdb1 null teachers
TABLE studentdb3 null students
TABLE studentdb3 null teachers
TABLE sys null sys_config
*/