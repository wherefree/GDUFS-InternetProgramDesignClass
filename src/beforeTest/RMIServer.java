/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beforeTest;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author K01
 */
public class RMIServer {
    public static void main( String args[] ) throws RemoteException, MalformedURLException{
       //第一步 注册并监听1099端口(这是RMI的默认端口，就像HTTP的默认端口是80）
       LocateRegistry.createRegistry(1099);
       //第二步 实例化服务器的远程对象
       ClientRMIService service = new ClientRMIServiceImpl();
       //第三步 用助记符来对外发布远程对象
       Naming.rebind("RMIService", service);//service远程对象对外公布的服务名字是：RMIService
       System.out.println( "服务器发布了1个Java RMI远程对象" );

  }
}
