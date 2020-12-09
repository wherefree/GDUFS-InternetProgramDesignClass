/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;

/**
 *
 * @author gdufs
 */
public class RMIServerServiceImpl {
    //默认构造方法

  public RMIServerServiceImpl( )throws RemoteException{ }

 

  public String send(String msg)throws RemoteException{

       return "来自20171000000 张三服务器的返回："+msg;

}


//第二个send()方法在这里省略 

public String send(String yourNo,byte[] yourName) throws RemoteException{
     return yourNo+" "+yourName.toString();
  //这是一段在服务器端实现的课堂实验记分程序
}
}
