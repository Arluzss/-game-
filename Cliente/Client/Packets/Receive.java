package Client.Packets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import Entity.Player.PlayerCords;

public class Receive {

    private DatagramPacket packetReceive;
    private DatagramSocket socket;
    private ObjectInputStream inputStream;
    private ByteArrayInputStream inputByte;
    private int PORT = 2007;

    public Receive() throws SocketException{
        System.out.println("PORTA " + PORT + " PARA RECEBER PACOTES");
        socket = new DatagramSocket(PORT);
    }

    public PlayerCords[] receivePacket() throws IOException{
        byte[] buffer = new byte[1024];
        packetReceive = new DatagramPacket(buffer, buffer.length);
        socket.receive(packetReceive);

        byte[] dados = packetReceive.getData();
        inputByte = new ByteArrayInputStream(dados);
        inputStream = new ObjectInputStream(inputByte);
        try {
            PlayerCords[] p = (PlayerCords[]) inputStream.readObject();
            return p;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
