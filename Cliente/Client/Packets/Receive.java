package Client.Packets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import Entity.Player.PlayerInfo;

public class Receive {

    private DatagramPacket packetReceive;
    private DatagramSocket socket;
    private ObjectInputStream inputStream;
    private ByteArrayInputStream inputByte;
    private int PORT = 2007;
    private int playerLength;

    public Receive() throws SocketException{
        System.out.println("PORTA " + PORT + " PARA RECEBER PACOTES");
        socket = new DatagramSocket(PORT);
        packetReceive = new DatagramPacket(new byte[500], 500);
    }

    public PlayerInfo[] receivePacket() throws IOException, NullPointerException {
        socket.receive(packetReceive);
    
        byte[] dados = packetReceive.getData();
        inputByte = new ByteArrayInputStream(dados);
        inputStream = new ObjectInputStream(inputByte);

        try {
            PlayerInfo[] players = (PlayerInfo[]) inputStream.readObject();
            playerLength = players.length;
            return players;
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

    public int getPlayerLength() {
        return playerLength;
    }
}
