package Window;

import java.io.IOException;

import javax.swing.JFrame;

import Client.Conn;
import Entity.Player.Player;
import Window.KeyBoard.KeyHandler;

public class MainScreen extends JFrame {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    Player p1;
    Player p2;
    private KeyHandler keyH;
    Conn conn;

    public MainScreen() {
        try {
            p1 = new Player();
            p2 = new Player();
            conn = new Conn(p1, p2);
            keyH = new KeyHandler();
            initComponents();
            addPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    private void initComponents() {
        setTitle("MainScreen");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyH);
        setFocusable(true);
    }

    private void addPlayer() {
       add(p1);
       add(p2);
    }

}