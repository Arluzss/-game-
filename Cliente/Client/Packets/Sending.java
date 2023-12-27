package Client.Packets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Sending {
    private DatagramSocket socket;
    private DatagramPacket packetSending;
    private ObjectOutputStream outputStream;
    private ByteArrayOutputStream outputByte;
    private int PORT = 2009;

    public Sending() throws SocketException {
        System.out.println("PORTA " + PORT + " PARA ENVIAR PACOTES");
        socket = new DatagramSocket(PORT);
    }

    public void startStreamByte() throws IOException {
        outputByte = new ByteArrayOutputStream();
        outputStream = new ObjectOutputStream(outputByte);
    }

    public void sendEncapsulation(InetAddress address, int o) {
        try {
            outputStream.writeInt(o);
            outputStream.flush();
            byte[] byteArray = outputByte.toByteArray();
            packetSending = new DatagramPacket(byteArray, byteArray.length, address, 2008);
    
            if (packetSending != null) {
                socket.send(packetSending);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}