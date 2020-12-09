/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import static RMI2.RMIServerService3.onLine;
import java.rmi.RemoteException;
import java.util.Iterator;

/**
 *
 * @author gdufs
 */
public class RMIServerService3Impl {
    String name;
    public String send(String msg) throws RemoteException{
        return null;
    }
    public String addClientToOnLine(RMIClientService client,String name) throws RemoteException{
        this.name=name;
        onLine.add(client);
        return name+"加入群聊 ";
    }
    public void sendMessageToServer(String msg)throws RemoteException{
        Iterator it= onLine.iterator();
        while(it.hasNext()){
            RMIClientService client=(RMIClientService)it.next();
            client.showMessageToClient(msg);
        }
    }


}
