/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beforeTest;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author K01
 */
public interface RMIService2 extends Remote{
     public String getMessage(String url,String serviceName) throws RemoteException;
}
