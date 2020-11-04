/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaMail;

import java.util.Base64;

/**
 *
 * @author gdufs
 */
public class BASE64Encoder {
         public static void main(String args[]){

    String username="";

    String password= "";

  

   username =Base64.getEncoder().encodeToString(username.getBytes());

   password =Base64.getEncoder().encodeToString(password.getBytes());

 

    System.out.println(username);//显示用户名的编码结果

    System.out.println(password);//显示密码的编码结果 

}
}
