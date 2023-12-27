package Server.Packets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Receive {

    private DatagramPacket packetReceive;
    private DatagramSocket socket;
    private ObjectInputStream inputStream;
    byte[] buffer;
    private ByteArrayInputStream inputByte;
    private int PORT = 2008;
    private int i;

    public Receive() throws SocketException {
        System.out.println("PORTA " + PORT + " PARA RECEBER PACOTES");
        socket = new DatagramSocket(PORT);
        buffer = new byte[1024];
        packetReceive = new DatagramPacket(buffer, buffer.length);
    }

    public InetAddress receivePacket() throws IOException {
        try {
            socket.receive(packetReceive);
            byte[] dados = packetReceive.getData();
            inputByte = new ByteArrayInputStream(dados);
            inputStream = new ObjectInputStream(inputByte);
            i = inputStream.readInt();
            inputStream.close();
            return packetReceive.getAddress();
        } finally {
            packetReceive.setData(new byte[1024]);
        }
    }

    public int getKeyCode() throws IOException {
        return i;
    }
}
