/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketTCP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author gdufs
 */
public class FileRead {
    InputStream in;
    OutputStream out;
    public FileRead() throws IOException{
        String fName="F:\\互联网程序设计\\test.txt";
        in=new FileInputStream(fName);
        //int b=in.read();
        //System.out.println(b);
        
        BufferedReader br=new BufferedReader(new InputStreamReader(in,"GB2312"));
        String msg=br.readLine();
        System.out.println(msg);
    }

    /**
     *
     * @throws IOException
     */
    public static void main(String []args) throws IOException{
        new FileRead();
    } 
}
