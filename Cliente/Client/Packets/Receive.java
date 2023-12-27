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
        packetReceive = new DatagramPacket(new byte[500], 500);
    }

    public PlayerCords[] receivePacket() throws IOException {
        socket.receive(packetReceive);
    
        byte[] dados = packetReceive.getData();
        inputByte = new ByteArrayInputStream(dados);
        inputStream = new ObjectInputStream(inputByte);
        try {
            return (PlayerCords[]) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                packetReceive.setData(new byte[500]);
                inputStream.close();
            }
        }
        return null;
    }
}
