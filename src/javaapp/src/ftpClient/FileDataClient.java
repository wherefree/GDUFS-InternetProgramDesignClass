/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFileChooser;

/**
 *
 * @author gdufs
 */
public class FileDataClient {
    private Socket dataSocket=null;
    public FileDataClient(String ip,String port) throws IOException{
         dataSocket=new Socket(ip,Integer.parseInt(port)-1);

    }
    public String fileGet(String fName) throws IOException{

boolean flag=false;

 

     if(dataSocket!=null){//自定义变量dataSocket

       byte[] buff=new byte[1024*2];//用来缓冲接收的字节数据

        

        //(1)文件保存对话框.

 

        JFileChooser chooser=new JFileChooser();

        File saveFile=new File(fName);

        chooser.setSelectedFile(saveFile);

        int stat=chooser.showSaveDialog(null);

        if(stat==JFileChooser.APPROVE_OPTION)

         saveFile=chooser.getSelectedFile();

       else

         saveFile=null;

     

        if(saveFile!=null){

           FileOutputStream fileOut=new FileOutputStream(saveFile); //新建本地空文件.

 

       InputStream socketIn= dataSocket.getInputStream();//网络字节输入流

       OutputStream socketOut=dataSocket.getOutputStream();//网络字节数出流

 

       //(2)发送请求的文件名，字符串读写功能

       PrintWriter pw=new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);

       pw.println(fName);

 

        //(3)接收服务器的数据文件，字节读写功能

           int len=socketIn.read(buff);//读一块到缓冲区.

             while(len!=-1){

                fileOut.write(buff,0,len);//写一块到文件.

                len=socketIn.read(buff);

                flag=true;

             }

     //(4)文件传输完毕，关闭数据套接字。

         fileOut.close();

         //JOptionPane.showMessageDialog(null, "文件接收完毕.");

          dataSocket.close();

          if(flag)

                return "文件下载成功.";

            else{

                saveFile.delete();

                return "文件名错误或文件下载失败.";

               }

           }else{

                 dataSocket.close();

            return "本地文件创建失败.";

           }

       }else

        return "服务器连接失败.";

}


    
}
