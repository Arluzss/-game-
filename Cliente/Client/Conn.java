package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import Client.Packets.Receive;
import Client.Packets.Sending;
import Entity.Player.Player;
import Entity.Player.PlayerCords;
import Window.KeyBoard.KeyHandler;
import Window.OtherWindow.AddIpScreen;

public class Conn {

    private Socket socketConn;
    private Receive receive;
    private Sending send;
    private static String ip;
    private static int port;
    private Thread t1;
    private Thread t2;
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private ArrayList<Player> players;

    public Conn(Player p1, Player p2, Player p3, Player p4) throws UnknownHostException, IOException {
        if (ip != null && port > 1023 && port <= 65535) {
            players = new ArrayList<Player>();
            players.add(p1);
            players.add(p2);
            players.add(p3);
            players.add(p4);
            socketConn = new Socket(ip, port);
            receive = new Receive();
            send = new Sending();
            t1 = new Thread(sendRunnable);
            t2 = new Thread(receiveRunnable);
            t1.start();
            t2.start();
        } else {
            new AddIpScreen();
        }
    }

    Runnable sendRunnable = () -> {
        send();
    };

    Runnable receiveRunnable = () -> {
        receive();
    };

    public void send() {
        while (true) {
            try {
                send.startStreamByte();
                send.sendEncapsulation(socketConn.getInetAddress(), KeyHandler.keyCode);
                Thread.sleep(0, 300);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public void receive() {
        while (true) {
            try {
                List<Player> copiaPlayers = new ArrayList<>(players);
                for(Player p : copiaPlayers){
                    p.runningCoords(receive.receivePacket()[copiaPlayers.indexOf(p)]);
                }
                Thread.sleep(0, 300);
            } catch (InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.err.println("erro de bobão");
            } catch (IOException e) {
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

}