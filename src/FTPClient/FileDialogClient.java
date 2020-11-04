/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FTPClient;
import socketTCP.*;
import java.net.*;
import java.io.*;

 
/**
 *
 * @author gdufs
 */
public class FileDialogClient {
    private Socket socket=null;

  

  //用于字节流和字符流之间转换用的变量.

  private PrintWriter pw;

  private BufferedReader br;

 

  public FileDialogClient(String ip,String port) throws IOException{

     

    socket=new Socket(ip,Integer.parseInt(port));

     //主动向服务器发起连接,实现TCP中三次握手的过程。

     //若不成功(网络问题,地址错误,服务器资源紧张等),抛出错误，其错误信息交由调用者处理。

     //若成功,做下面两件事情。   

    

     OutputStream socketOut = socket.getOutputStream();

     pw=new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);

     //得到网络输出字节流地址,并装饰成网络输出字符流

    

     InputStream socketIn = socket.getInputStream();

     br=new BufferedReader(new InputStreamReader(socketIn,"GB2312"));

     //得到网络输入字节流地址,并装饰成网络输入字符流

  }

 

  public void send(String msg){

        pw.println(msg);

        //输出字符流，由Socket调用系统底层函数，经网卡发送字节流。

   }

 

  public String receive(){

  String msg;

      try {

          msg = br.readLine();

        //从网络输入字符流中读信息,每次只能接收一行信息.

      //若不够一行(无行结束符),该语句阻塞(阻塞语句),直到条件满足,程序才往下运行

      } catch (IOException ex) { msg=null;  }

  

    return msg;

  }

 

  public void close() {

     try {

      if(socket!=null) 

         socket.close();//实现四次握手断开.

    } catch (IOException ex) {  }

  }

 

   //模块内测试与运行，需先运行TCPServer。

  public static void main(String args[]) throws IOException{

    FileDialogClient tcp=new FileDialogClient("127.0.0.1","8008");

    tcp.send("123456789");//发送一行字符串

    System.out.println(tcp.receive());//接收一行字符串并在屏幕上显示

    tcp.close();     

  }
}
