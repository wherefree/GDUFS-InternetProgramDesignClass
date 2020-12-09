/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author gdufs
 */
public interface RMIClientService extends Remote{  

   //定义本地刷新聊天窗口的方法，该方法供远程服务器调用

   public void showMessageToClient(String msg)throws RemoteException;

}