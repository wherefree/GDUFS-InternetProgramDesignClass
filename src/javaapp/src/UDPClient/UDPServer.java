/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDPClient;

import socketTCP.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author gdufs
 */
public class UDPServer {

  private int port=8088;

  private DatagramSocket serverSocket;
 
 

  public UDPServer() throws IOException {

    serverSocket = new DatagramSocket(8088);//开启8008号监听端口。

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

 

 

  public void service() {//支持多用户并发

    while (true) {

      try {
          //接收客户端发送的数据
          byte[] bytes = new byte[1024];
          
          //准备一个空数据包
          DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
          //接收一个到达本端口的数据报，并记录对方的地址信息
          serverSocket.receive(packet);//阻塞语句
          String info = new String(bytes, 0, packet.getLength(), "GB2312");
          
//          String msg = new String(packet.getData(), 0, packet.getLength(), "GB2312");

          //本地监控台显示接收的信息
          System.out.println("New income: "+serverSocket.getInetAddress() + ">" + info);
          
           //定义客户端地址，端口号，数据
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            
            String msg = "20181002928 陈嘉怡" + new Date().toString() + info;
            
          //给原包填充新的数据（目的地址信息已隐含在包中）
//          packet.setData(echo(msg).getBytes("GB2312"));
            byte[] data = msg.getBytes("GB2312");
            packet = new DatagramPacket(data, data.length, address, port);
            
          serverSocket.send(packet);
          


      }catch (IOException e) {

         e.printStackTrace();

      }

    }

  }

 

  public static void main(String args[])throws IOException {

    new UDPServer().service();

  }

}

 