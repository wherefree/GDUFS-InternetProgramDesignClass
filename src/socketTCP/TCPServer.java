/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketTCP;
import java.io.*;
import java.net.*;
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

 

  public void service() {//单客户版本,即每次只能同时和一个客户建立通信连接。

    while (true) {

      Socket socket=null;

      try {

        socket = serverSocket.accept();

         //阻塞语句，监听并等待客户发起连接，有连接请求就生成一个套接字。

        System.out.println("New income: "+socket.getInetAddress());

       

 

       OutputStream socketOut = socket.getOutputStream();

       PrintWriter pw=new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);

           //获取网络输出字节流地址,并装饰成网络字符流。    

      InputStream socketIn = socket.getInputStream();

      BufferedReader br=new BufferedReader(new InputStreamReader(socketIn,"GB2312"));

         //获取网络输入字节流地址,并装饰成网络字符流。

 

        String msg;

      while ((msg = br.readLine())!= null) //阻塞语句，从输入流中读入一行字符串。

        {

             pw.println("来自服务器:"+msg);//向输出流中输出一行字符串。

             //pw.println("来自服务器2:"+msg);

            

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
