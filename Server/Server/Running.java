package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import Entity.Player.Player;
import Entity.Player.PlayerInfo;
import Server.Packets.Receive;
import Server.Packets.Sending;

public class Running {
    private Thread t1;
    private Thread t2;
    private Thread t3;
    private Thread t4;
    private Conn conn;
    private PlayerInfo[] playerCords;
    private Receive receive;
    private Sending sending;
    private List<Player> players2;
    private final int QTD = 2;

    public Running(Conn conn) throws SocketException {
        this.conn = conn;
        t1 = new Thread(newConn);
        t2 = new Thread(playerSendRun);
        t3 = new Thread(playerRunAndReceive);
        t4 = new Thread(playerActions);
        receive = new Receive();
        sending = new Sending();
        playerCords = new PlayerInfo[QTD];
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
                    if (player != null) {
                        sending.startStreamByte();
                        sending.sendEncapsulation(playerCords, player);
                        playerCords[player.getID2()] = player.getCords();
                    }
                    Thread.sleep(1000 / 180);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
        }
    };

    Runnable playerActions = () -> {
        while (true) {
            List<Player> copiaPlayers = new ArrayList<>(players2);
            for (Player player : copiaPlayers) {
                try {
                    player.verifyColision(copiaPlayers.toArray(new Player[copiaPlayers.size()]));
                    Thread.sleep(0, 10);
                } catch (InterruptedException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Runnable playerRunAndReceive = () -> {
        while (true) {
            List<Player> copiaPlayers = new ArrayList<>(players2);
            Player jogadorEncontrado = null;

            try {
                InetAddress enderecoRecebido = receive.receivePacket();

                jogadorEncontrado = copiaPlayers.stream().filter(player -> player.getAddress().
                equals(enderecoRecebido)).
                findFirst().orElse(null);

                if (jogadorEncontrado != null)
                    jogadorEncontrado.play(receive.getKeyCode());
            } catch (IOException e) {
                e.printStackTrace(); // Apenas um exemplo, imprime o rastreamento da pilha
            }

        }
    };

}
