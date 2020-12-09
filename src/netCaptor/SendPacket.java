/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netCaptor;

import java.io.IOException;
import java.net.InetAddress;
import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

/**
 *
 * @author gdufs
 */
public class SendPacket {
    
    public static void main(String args[]) throws IOException{
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        JpcapSender sender=JpcapSender.openDevice(devices[0]);
       TCPPacket tcp=new TCPPacket(8000,8008,56,78,false,false,false,false,true,false,true,true,200,10);
       tcp.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100, IPPacket.IPPROTO_TCP, InetAddress.getByName("192.168.207.146"), InetAddress. getByName ("202.116.195.81"));
       tcp.data=("20181003000 吴佩毅").getBytes("GB2312");
       EthernetPacket ether=new EthernetPacket();
       ether.src_mac=new byte[]{(byte)68,(byte)55,(byte)230,(byte)197,(byte)197,(byte)135};
       ether.dst_mac=new byte[]{(byte)00,(byte)17,(byte)93,(byte)156,(byte)148,(byte)00};
       ether.frametype=EthernetPacket.ETHERTYPE_IP;//设置成以太网链路层
       tcp.datalink=ether;//把链路层头加入到TCP包中
       sender.sendPacket(tcp);
    }
}
