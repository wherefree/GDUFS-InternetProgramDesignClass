package UDPClient;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gdufs
 */

public class UDPClient {

  private int remotePort;

  private InetAddress  remoteIP;

  private DatagramSocket socket; //UDP套接字

 

  public UDPClient(String ip,String port)throws IOException{

      this.remotePort=Integer.parseInt(port);

      this.remoteIP=InetAddress.getByName(ip);

    

      //创建一个UDP套接字，与本地任意一个未使用的UDP端口绑定

      socket=new DatagramSocket();

      //或创建一个本地固定的UDP端口号，通常用于服务器

      //socket=new DatagramSocket(6006);      

  }

 

  //定义数据的发送方法

  public void send(String msg){

    try {

        byte[] outputData=msg.getBytes("GB2312");//发送的数据字节化

        //构建一个数据报文。 

        DatagramPacket outputPacket=new DatagramPacket(outputData,outputData.length,remoteIP,remotePort);

        socket.send(outputPacket);  //给remoteIP发送数据报

    } catch (IOException ex) { }

  }

 

  //定义数据的接收方法

  public String receive(){//throws IOException{

    String msg;

     //先准备一个空数据报文

    DatagramPacket inputPacket=new DatagramPacket(new byte[512],512);

    try {

     socket.receive(inputPacket);//阻塞语句，有数据就装包，以装完数据或装满包为此.

       //从报文中取出字节数据并装饰成字符数据.

       msg=new String(inputPacket.getData(),0,inputPacket.getLength(),"GB2312");

      } catch (IOException ex) {

        msg=null;

      }

    return msg;

  }

 

  public void close(){

    if(socket!=null) 

       socket.close();//释放本地占用的UDP端口.

  }

}