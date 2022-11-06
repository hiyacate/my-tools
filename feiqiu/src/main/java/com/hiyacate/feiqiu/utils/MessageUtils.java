package com.hiyacate.feiqiu.utils;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author hiya
 * @description
 * @date 2022-10-16 19:00
 */
public class MessageUtils {
    public static void main(String[] args) throws IOException {
        //飞秋的数据格式
        String str = "1:100:Jack:dell:32:hello 你好啊";
        byte[] bytes = str.getBytes("GBK");
        File file = new File("./pom.xml");
        long fileSize = file.length();
        if(fileSize>Integer.MAX_VALUE){
            System.out.println("file too big...");
            return;
        }
        byte[] buffer = null;
        try(FileInputStream fi = new FileInputStream(file)){
            buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead = 0;
            while(offset < buffer.length && (numRead=fi.read(buffer, offset, buffer.length - offset))>=0){
                offset += numRead;
            }

            if(offset != buffer.length){
                throw new IOException("Could not completely read file"+file.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Socket sck = new Socket("192.168.0.106", 2425);
        OutputStream os = sck.getOutputStream();
        os.write(buffer);
        sck.close();
        //发送的管道
        DatagramSocket ds = new DatagramSocket();
        //数据包
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.0.106"), 2425);
        ds.send(dp);
    }

}

