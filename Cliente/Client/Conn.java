package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import Client.Packets.Receive;
import Client.Packets.Sending;
import Entity.Player.Player;
import Window.GamePanel;
import Window.KeyBoard.KeyHandler;
import Window.OtherWindow.AddIpScreen;

public class Conn {
    
    private static String ip;
    private static int port;
    private Socket socketConn;
    private Receive receive;
    private Sending send;
    private Thread t1;
    private Thread t2;
    private ArrayList<Player> players;
    private GamePanel gamePanel;
    private int playerLength;

    public Conn(GamePanel gamePanel) throws UnknownHostException, IOException {
        if (ip != null && port > 1023 && port <= 65535) {
            this.gamePanel = gamePanel;
            players = new ArrayList<Player>();
            netStart();
            threadStart();
        } else {
            new AddIpScreen();
        }
    }
    
    private void threadStart(){
        t1 = new Thread(sendRunnable);
        t2 = new Thread(receiveRunnable);
        t1.start();
        t2.start();
    }

    public void netStart() {
        try{
            socketConn = new Socket(ip, port);
            receive = new Receive();
            send = new Sending();
            players.add(new Player());
        }catch(IOException e){
            e.printStackTrace();
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
                addPlayer();
                for (Player p : copiaPlayers) {
                    p.setCords(receive.receivePacket()[copiaPlayers.indexOf(p)]);
                    Thread.sleep(0, 300);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPlayer() {
        if(playerLength != receive.getPlayerLength()){
            playerLength = receive.getPlayerLength();
            players.add(new Player());
            gamePanel.setPlayers(players);
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