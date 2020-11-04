/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketTCP;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author gdufs
 */
public class FileCopyer {
    FileInputStream fileRead;
    FileOutputStream fileWrite;
    
    public FileCopyer(String fName1,String fName2) throws FileNotFoundException{
        fileRead=new FileInputStream(fName1);
        fileWrite=new FileOutputStream(fName2,true);
        
    }
    public void StartCopy() throws IOException{
        byte[]buff=new byte[1024*2];
        int len=fileRead.read(buff);
        while(len!=-1){
            fileWrite.write(buff, 0, len);
            len=fileRead.read(buff);
            
        }
        fileRead.close();
        fileWrite.close();
        
    }
     public static void main(String args[])throws IOException {

    FileCopyer filecopy=new FileCopyer("C:\\Users\\gdufs\\Desktop\\text1.txt","C:\\Users\\gdufs\\Desktop\\text2.txt");
    filecopy.StartCopy();

  }
    
}
