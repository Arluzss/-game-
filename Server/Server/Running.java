package Server;

import java.io.IOException;
import java.net.SocketException;
import Entity.Player.Player;
import Entity.Player.PlayerCords;
import Server.Packets.Receive;
import Server.Packets.Sending;

public class Running {
    private Thread t1;
    private Thread t2;
    private Thread t3;
    private Conn conn;
    private Player[] players;
    private PlayerCords[] playerCords;
    private Receive receive;
    private Sending sending;

    public Running(Conn conn) throws SocketException {
        this.conn = conn;
        t1 = new Thread(newConn);
        t2 = new Thread(playerSendRun);
        t3 = new Thread(playerRunAndReceive);
        receive = new Receive();
        sending = new Sending();
        players = new Player[2];
        playerCords = new PlayerCords[2];
    }

    public void startRun() {
        t1.start();
        t2.start();
        t3.start();
    }

    Runnable newConn = () ->{
        while (true) {  
            try {
                if(conn.connection() && Player.getID() < 2){
                    players[Player.getID()] = new Player(conn.getAddress());
                    Thread.sleep(1500);
                }
            } catch (IOException ee) {
                ee.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable playerSendRun = () ->{
        while (true) {
            for (int i = 0; i < players.length; i++) {
                try {
                    playerCords[i] = players[i].getCords();
                    sending.startStreamByte();
                    sending.sendEncapsulation(playerCords, players[i]);
                    Thread.sleep(0,500);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Runnable playerRunAndReceive = () ->{
        while(true){
            for (int i = 0; i < players.length; i++) {
                try {
                    if(players[i].getAddress().equals(receive.receivePacket())){
                        players[i].keyBoard(receive.getInputStream().readInt());
                    }
                    Thread.sleep(0,500);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
