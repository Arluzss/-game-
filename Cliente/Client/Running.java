package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Client.Packets.Receive;
import Client.Packets.Sending;
import Entity.Player.Player;
import Window.KeyBoard.KeyHandler;

public class Running {
    private Receive receive;
    private Sending send;
    private Thread t1;
    private Thread t2;
    private List<Player> players;
    private Socket socketConn;

    public Running(Socket socketConn) {
        this.socketConn = socketConn;
        players = new ArrayList<Player>();
        players.add(new Player());
        startNet();
        threadStart();
    }

    private void startNet(){
        try {
            receive = new Receive();
            send = new Sending();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void threadStart() {
        t1 = new Thread(sendRunnable);
        t2 = new Thread(receiveRunnable);
        t1.start();
        t2.start();
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
            } catch (IOException | InterruptedException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPlayer() {
        List<Player> playerCopy = new ArrayList<>(players);
        int playerLength = playerCopy.size();
        if(playerLength != receive.getPlayerLength()){
            playerLength = receive.getPlayerLength();
            players.add(new Player());
        }

    }

    public List<Player> getPlayer() {
        return players;
    }
}
