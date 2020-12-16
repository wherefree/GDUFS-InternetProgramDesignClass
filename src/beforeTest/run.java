/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beforeTest;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author K01
 */
public class run {
     public static void main( String args[] ) throws NotBoundException, MalformedURLException, RemoteException{
         RMIService2 service;
          String url="rmi://202.116.195.81:1099/";
         service=(RMIService2)Naming.lookup(url+"RMIService2");
          service.getMessage("192.168.226.67:1099", "RMIService");
     }
}
