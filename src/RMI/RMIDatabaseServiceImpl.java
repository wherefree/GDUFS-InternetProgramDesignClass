/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gdufs
 */
public class RMIDatabaseServiceImpl  extends UnicastRemoteObject implements RMIDatabaseService {
     public RMIDatabaseServiceImpl()throws RemoteException{ }

    public String insert(String no,String name,String sClass) throws RemoteException{

         try {
             Connection con=null;             
             con=new DBConnector().getConnection();
             Statement stmt = con.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(RMIDatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
         }


        }
}
