package UDP;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Date;

public class UDPServer {
    private DatagramSocket serverSocket;
    private int port = 8008;
    private static final int MAX_PACKET_SIZE = 512;
    public UDPServer() throws IOException {
        serverSocket = new DatagramSocket(8008);
        System.out.println("服务器启动监听在 " + port + " 端口");
    }
    public void Service() {

        while (true) {
            byte[] bytes = new byte[1024];
            DatagramPacket datagramPacket =  new DatagramPacket(bytes,bytes.length);
            try {
                serverSocket.receive(datagramPacket);
                System.out.println("New connection accepted： " + serverSocket.getInetAddress());
                String msg = null;
                //此处程序阻塞，每次从输入流中读入一行字符串
                if ((msg = new String(datagramPacket.getData(),
                        0,datagramPacket.getLength(),"GB2312") )!= null) {
                    //如果客户发送的消息为"bye"，就结束通信
                    if (msg.equals("bye")) {
                        //向输出流中输出一行字符串,远程客户端可以读取该字符串
                        String msg1;
                        msg1 = "链接结束";
                        byte[] sendbyte = msg1.getBytes("GB2312");
                       DatagramPacket datagramPacket1 = new DatagramPacket(sendbyte,sendbyte.length,datagramPacket.getAddress(),datagramPacket.getPort());
                        serverSocket.send(datagramPacket1);
                        System.out.println("客户端离开");
                        break; //结束循环
                    }
                    //向输出流中输出一行字符串,远程客户端可以读取该字符串
                    if (msg.equals("hello"))
                    {
                        String msghello;
                        msghello = "From服务器:欢迎使用本UDP服务器";
                        byte[] sendbyte0 = msghello.getBytes("GB2312");
                        DatagramPacket datagramPacket0 = new DatagramPacket(sendbyte0,sendbyte0.length,datagramPacket.getAddress(),datagramPacket.getPort());
                        serverSocket.send(datagramPacket0);
                    }
                    else {
                        String msg1;
                        msg1 = "20181002828&古镇豪&"+new Date().toString()+"&"+ msg;
                        byte[] sendbyte = msg1.getBytes("GB2312");
                        DatagramPacket datagramPacket1 = new DatagramPacket(sendbyte, sendbyte.length, datagramPacket.getAddress(), datagramPacket.getPort());
                        serverSocket.send(datagramPacket1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException{
        new UDPServer().Service();
    }
}
