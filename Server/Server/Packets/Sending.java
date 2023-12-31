package Server.Packets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import Entity.Player.Player;
import Entity.Player.PlayerInfo;

public class Sending {
    private DatagramSocket socket;
    private DatagramPacket packetSending;
    private ObjectOutputStream outputStream;
    private ByteArrayOutputStream outputByte;
    private int PORT = 2006;

    public Sending() throws SocketException {
        System.out.println("PORTA " + PORT + " PARA ENVIAR PACOTES");
        socket = new DatagramSocket(PORT);
    }

    public void startStreamByte() throws IOException {
        outputByte = new ByteArrayOutputStream();
        outputStream = new ObjectOutputStream(outputByte);
    }

    public void sendEncapsulation(PlayerInfo[] players, Player p) {
        try {
            outputStream.writeObject(players);
            outputStream.flush();
            byte[] byteArray = outputByte.toByteArray();
            packetSending = new DatagramPacket(byteArray, byteArray.length, p.getAddress(), 2007);
            socket.send(packetSending);
            outputStream.close();
        } catch (IOException | NullPointerException e) {
            // System.out.println(e.getMessage());
        }
    }
}