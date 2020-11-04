/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketTCP;

import static com.oracle.jrockit.jfr.Transition.From;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author gdufs
 */
public class TCPThreadServer {

  private int port=8008;

  private ServerSocket server;

  private ExecutorService executorService;  //线程池

   

  public TCPThreadServer() throws IOException {

 

server = new ServerSocket(port); //打开服务器端口

 

   executorService= Executors.newFixedThreadPool(30);//在池中创建30个线程

   System.out.println("多用户服务器启动");

  }

 

  public void service() {//主进程，用于接收客户、分配线程给客户

    while (true) {

      Socket socket=null;

      try {

        socket = server.accept(); //监听客户请求.

        executorService.execute(new Handler(socket));//接受一个客户,从线程池中拿出一个线程处理该客户.   
        
      }catch (IOException e) {  }

    }

  }

 

  public static void main(String args[])throws IOException {

    new TCPThreadServer().service();

  }

}

 

//定义内部类，实现和客户对话功能

class Handler implements Runnable{

  private Socket socket;

 

  public Handler(Socket socket) throws IOException{

    this.socket=socket;
    OutputStream out = socket.getOutputStream();
       PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"GB2312"),true); 
       pw.println("20171003161 余思涵");

  }

 

  public void run(){//覆盖线程体

    try {

       

      InputStream in = socket.getInputStream();
        BufferedReader br =new BufferedReader(new InputStreamReader(in,"GB2312"));

       OutputStream out = socket.getOutputStream();
       PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"GB2312"),true);  

 

      String msg = null;

      while (( msg = br.readLine())!= null) {       

        pw.println("From ThreadServer: "+msg);//send to client.
        System.out.println("来自访客："+msg);

        

      }

    }catch (IOException e) {

       e.printStackTrace();

    }finally {

       try{

         if(socket!=null)socket.close();

       }catch (IOException e) { }

    }

  }

}

