/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;

/**
 *
 * @author gdufs
 */
public interface RMIServerService3  extends Remote{
    HashSet<RMIClientService>  onLine=new HashSet<RMIClientService>();
            //客户和服务器单对单对话的远程方法. 
        public String send(String msg) throws RemoteException;
        //客户加入群组的远程方法，其中在name中包含自己的学号和姓名
        public String addClientToOnLine(RMIClientService client,String name) throws RemoteException;
        //客户退出群组的远程方法
        public String delClientFromOnLine(RMIClientService client) throws RemoteException;
        //客户在群组中聊天的远程方法
        public void sendMessageToServer(String msg)throws RemoteException;
}
