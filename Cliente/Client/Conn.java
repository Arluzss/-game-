package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import Client.Packets.Receive;
import Client.Packets.Sending;
import Entity.Player.Player;
import Window.KeyBoard.KeyHandler;
import Window.OtherWindow.AddIpScreen;

public class Conn implements Runnable{

    private Socket socketConn;
    private Receive receive;
    private Sending send;
    private static String ip;
    private static int port;
    private Thread t1;
    private Player p1;
    private Player p2;

    public Conn(Player p1, Player p2) throws UnknownHostException, IOException {
        if(ip != null && port > 1023 && port <= 65535){
            socketConn = new Socket(ip, port);
            receive = new Receive();
            send = new Sending();
            this.p1 = p1;
            this.p2 = p2;
            t1 = new Thread(this); 
            t1.start();
        }else{
            new AddIpScreen();
        }
    }

    public void sla() {
        while (true) {
            try {
                send.startStreamByte();
                send.sendEncapsulation(socketConn.getInetAddress(), KeyHandler.keyCode);
                p1.runningCoords(receive.receivePacket());
                p2.runningCoords(receive.receivePacket());
                Thread.sleep(0,500);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        Conn.ip = ip;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Conn.port = port;
    }

    @Override
    public void run() {
        sla();
    }

}