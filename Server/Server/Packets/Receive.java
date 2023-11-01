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
    private ByteArrayInputStream inputByte;
    private int PORT = 2008;

    public Receive() throws SocketException{
        System.out.println("PORTA " + PORT + " PARA RECEBER PACOTES");
        socket = new DatagramSocket(PORT);
    }

    public InetAddress receivePacket() throws IOException{
        byte[] buffer = new byte[1024];
        packetReceive = new DatagramPacket(buffer, buffer.length);
        socket.receive(packetReceive);
        byte[] dados = packetReceive.getData();
        inputByte = new ByteArrayInputStream(dados);
        inputStream = new ObjectInputStream(inputByte);
        return packetReceive.getAddress();
    }

    public ObjectInputStream getInputStream(){
        return inputStream;
    }
}
