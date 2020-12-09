/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author gdufs
 */
public class PropertyReader {
    static private Properties ps;
    static{
        ps=new Properties();
        try{
            InputStream in=PropertyReader.class.getResourceAsStream("db.conf");
            ps.load(in);
            in.close();
        }catch(Exception e){}
    }
    public static String get(String key){
        return (String)ps.get(key);
    }
}
