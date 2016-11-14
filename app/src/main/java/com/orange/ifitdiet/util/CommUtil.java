package com.orange.ifitdiet.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by 廖俊瑶 on 2016/10/30.
 */

public class CommUtil {
    private Context context;
    private DatagramSocket socket;

    public CommUtil(Context context) {
        //接收方创建步骤：
        //创建一个DatagramSocket对象，并指定监听的端口号
        this.context = context;
        try {
            socket = new DatagramSocket(9990);
            receiveData();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public String receiveData() {

        //使用receive方法接收发送方所发送的数据,同时这也是一个阻塞的方法
        String receive = null;
        while (true) {
            try {
                //创建一个byte数组用于接收
                byte data[] = new byte[1024];
                //创建一个空的DatagramPacket对象
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
                //得到发送过来的数据
                socket.receive(datagramPacket);
                receive = new String(datagramPacket.getData(), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            Log.e("WIFI传来的数据", receive);
            Toast.makeText(context, receive, Toast.LENGTH_SHORT).show();
        }
        return receive;
    }

    public ArrayList<String> getConnectIp() {
        ArrayList<String> connectIpList = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/proc/net/arp"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" +");
                if (splitted != null && splitted.length >= 4) {
                    String ip = splitted[0];
                    connectIpList.add(ip);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectIpList;
    }
}
