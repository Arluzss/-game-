package Server;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import Entity.Player.Player;
import Entity.Player.PlayerCords;
import Server.Packets.Receive;
import Server.Packets.Sending;

public class Running {
    private Thread t1;
    private Thread t2;
    private Thread t3;
    private Thread t4;
    private Conn conn;
    private Player[] players;
    private PlayerCords[] playerCords;
    private Receive receive;
    private Sending sending;
    ArrayList<Player> players2;
    private final int QTD = 4;

    public Running(Conn conn) throws SocketException {
        this.conn = conn;
        t1 = new Thread(newConn);
        t2 = new Thread(playerSendRun);
        t3 = new Thread(playerRunAndReceive);
        t4 = new Thread(playerActions);
        receive = new Receive();
        sending = new Sending();
        // players = new Player[QTD];
        playerCords = new PlayerCords[QTD];
        players2 = new ArrayList<Player>();
    }

    public void startRun() {
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    Runnable newConn = () -> {
        while (true) {
            try {
                if (conn.connection() && Player.getIDs() < QTD) {
                    players2.add(new Player(conn.getAddress()));
                    Thread.sleep(1500);
                    // players[Player.getIDs()] = new Player(conn.getAddress());
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable playerSendRun = () -> {
        while (true) {
            List<Player> copiaPlayers = new ArrayList<>(players2);
            for (Player player : copiaPlayers)
                try {
                    playerCords[player.getID2()] = player.getCords();
                    sending.startStreamByte();
                    sending.sendEncapsulation(playerCords, player);
                    Thread.sleep(0, 10);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            // if (players[0] != null) {
            // playerCords[0] = players[0].getCords();
            // sending.startStreamByte();
            // sending.sendEncapsulation(playerCords, players[0]);
            // }
            // if (players[1] != null) {
            // playerCords[1] = players[1].getCords();
            // sending.startStreamByte();
            // sending.sendEncapsulation(playerCords, players[1]);
            // }
            // if (players[2] != null) {
            // playerCords[2] = players[2].getCords();
            // sending.startStreamByte();
            // sending.sendEncapsulation(playerCords, players[2]);
            // }
            // if (players[3] != null) {
            // playerCords[3] = players[3].getCords();
            // sending.startStreamByte();
            // sending.sendEncapsulation(playerCords, players[3]);
            // }
        }
    };

    Runnable playerActions = () -> {
        while (true) {
            List<Player> copiaPlayers = new ArrayList<>(players2);
            for (Player player : copiaPlayers) {
                try {
                    player.verifyColision(players2.toArray(new Player[copiaPlayers.size()]));
                } catch (InterruptedException | NullPointerException eee) {
                    eee.printStackTrace();
                }
            }
            // players[0].verifyColision(players);
            // players[1].verifyColision(players);
        }
    };

    Runnable playerRunAndReceive = () -> {
        while (true) {
            List<Player> copiaPlayers = new ArrayList<>(players2);
            for (Player player : copiaPlayers) {
                try {
                    if(player.getAddress().equals(receive.receivePacket())){
                        player.play(receive.getKeyCode());
                    }
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            // if (players[0] != null &&
            // players[0].getAddress().equals(receive.receivePacket())) {
            // players[0].play(receive.getKeyCode());
            // }
            // if (players[1] != null &&
            // players[1].getAddress().equals(receive.receivePacket())) {
            // players[1].play(receive.getKeyCode());
            // }
            // if (players[2].getAddress().equals(receive.receivePacket())) {
            // players[2].play(receive.getInputStream());
            // }
            // if (players[3].getAddress().equals(receive.receivePacket())) {
            // players[3].play(receive.getInputStream());
            // }
        }
    };

}
