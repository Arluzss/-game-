package Window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.Socket;

import javax.swing.JPanel;
import Client.Running;
import Entity.Player.Player;
import Window.KeyBoard.KeyHandler;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;
    private Running running;
    private KeyHandler keyH;
    private double drawnInterval = 1000000000 / 60;
    private double nextDrawn = System.nanoTime() + drawnInterval;

    // TODO deixar a quantidade com base no numero de jogadores recebidos do pacote
    public GamePanel(Socket socketConn) {
        running = new Running(socketConn);
        keyH = new KeyHandler();
        initComponents();
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
        List<Player> players = new ArrayList<>(running.getPlayer());
        players.forEach((pla) -> {
            try {
                pla.draw(g2D);
            } catch (NullPointerException e) {
                System.err.println("ta vazio");
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

}