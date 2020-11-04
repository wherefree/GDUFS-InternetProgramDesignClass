/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gdufs
 */
public class TCPServer {

  private int port=8008;

  private ServerSocket serverSocket;//服务器套接字。

 

  public TCPServer() throws IOException {

    serverSocket = new ServerSocket(8008);//开启8008号监听端口。

    System.out.println("服务器启动");

  }

 

  private PrintWriter putWriter(Socket socket)throws IOException{

    OutputStream socketOut = socket.getOutputStream();//获得输出流缓冲区的地址。

    return new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);

 

  }

  private BufferedReader getReader(Socket socket)throws IOException{

    InputStream socketIn = socket.getInputStream();//获得输入流缓冲区的地址

    return new BufferedReader(new InputStreamReader(socketIn,"GB2312"));

  }

 

 

  public void service() {//单客户版本,即每次只能同时和一个客户建立通信连接。

    while (true) {

      Socket socket=null;

      try {

     socket = serverSocket.accept();

         //阻塞语句，监听并等待客户发起连接，有连接请求就生成一个套接字。

     System.out.println("New income: "+socket.getInetAddress());

        //本地服务器观测台显示请求的用户信息。

        BufferedReader br =getReader(socket);//定义字符串输入流。

        PrintWriter pw = putWriter(socket);//定义字符串输出流。

 

        String msg;

        while ((msg = br.readLine())!= null) //阻塞语句，从输入流中读入一行字符串。

         {

             pw.println("来自服务器:"+msg);//向输出流中输出一行字符串。

             //pw.println("来自服务器2:"+msg);

             if (msg.equals("bye")) //如果客户发送的消息为“bye”，就结束通信

              break;

        }

      }catch (IOException e) {

         e.printStackTrace();

      }finally {

         try{

           if(socket!=null)

               socket.close();  //断开连接

         }catch (IOException e) {e.printStackTrace();}

      }

    }

  }

 

  public static void main(String args[])throws IOException {

    new TCPServer().service();

  }

}

 