package Window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.JPanel;
import Client.Conn;
import Entity.Player.Player;
import Window.KeyBoard.KeyHandler;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;
    private Conn conn;
    private List<Player> players;
    private KeyHandler keyH;
    private double drawnInterval = 1000000000 / 60;
    private double nextDrawn = System.nanoTime() + drawnInterval;

    // TODO deixar a quantidade com base no numero de jogadores recebidos do pacote
    public GamePanel() {
        try {
            players = new ArrayList<Player>();
            conn = new Conn(this);
            keyH = new KeyHandler();
            initComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    @Override
    // TODO lidar melhor com o NullPointerException
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        threadVelocity();
        Graphics2D g2D = (Graphics2D) g;
        players.forEach((pla) -> {
            try {
                pla.draw(g2D);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
        g2D.dispose();
    }

    private void threadVelocity() {
        double remaining = nextDrawn - System.nanoTime();
        remaining = remaining / 10000000;
        repaint();
        try {
            if (remaining < 0) {
                remaining = 0;
            }
            Thread.sleep((long) Math.round(remaining));
            nextDrawn += drawnInterval;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}