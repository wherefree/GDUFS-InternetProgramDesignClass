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
public interface ClientRMIService extends Remote{
   public  String reportResult(String msg) throws RemoteException;
}
