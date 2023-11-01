package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Conn {

    private ServerSocket ServerSocket;
    private InetAddress address;
    private Socket conn;
    private final int PORT = 2001;

    public Conn() throws IOException {
        ServerSocket = new ServerSocket(PORT);
        System.out.println("PORTA " + PORT + " PARA CONEXÃO CRIADA");
    }

    public Boolean connection() throws IOException {
        System.out.println("esperando uma nova conexão");
        conn = ServerSocket.accept();
        address = conn.getInetAddress();
        System.out.println("ip conectado: " + conn.getInetAddress().getHostAddress());
        return true;
    }

    public InetAddress getAddress() {
        return address;
    }

}
