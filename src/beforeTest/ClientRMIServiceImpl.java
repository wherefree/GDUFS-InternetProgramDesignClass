/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beforeTest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author K01
 */
public class ClientRMIServiceImpl extends UnicastRemoteObject   implements ClientRMIService{
     public ClientRMIServiceImpl( )throws RemoteException{ }
      public  String reportResult(String msg){
         String ans1;
          if(msg.charAt(0)=='#'){
               char[] ans=new char[10000];
                int n=msg.length();
            for(int i=0;i<msg.length();i++){
                  if(msg.charAt(i)!='#'){
                      n=n-i;
                      for(;i<msg.length();i++){
                          ans[n]=msg.charAt(i);
                          n--;
                      }
                  }
              }
            ans1=String.valueOf(ans);
           
          }else {
              int ans=0;
              for(int i=0;i<msg.length();i++){
                  if(msg.charAt(i)!='*'){
                      for(;i<msg.length();i++){
                          ans+=msg.charAt(i)-'0';
                      }
                  }
              }
            ans1=Integer.toString(ans);
          }
          System.out.println("20181002828 古镇豪 "+ans1);
          return "20181002828 古镇豪 "+ans1;
      }
}
