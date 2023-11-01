import java.io.IOException;

import Server.Conn;
import Server.Running;

/**
 * Principal
 */
public class Principal {

    public static void main(String[] args) {
        try {
            Conn conn = new Conn();
            Running run = new Running(conn);
            run.startRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}