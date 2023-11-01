package Entity.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Player extends JPanel {
    private int WIDTH = 50;
    private int HEIGHT = 110;
    private final int ID;
    private static int COUNT = 0;

    public Player() {
        this.ID = COUNT;
        COUNT++;
        System.out.println(this.ID);
        initPlayer();
    }

    private void initPlayer() {
        setSize(WIDTH, HEIGHT);
        setOpaque(true);
        setBackground(Color.RED);
    }

    public int getID() {
        return this.ID;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D G2D = (Graphics2D) g;
    }

    public void runningCoords(PlayerCords[] cords) throws InterruptedException {
        for (int i = 0; i < cords.length; i++) {
            if (cords[i].getID() == this.ID && cords[i] != null) {
                System.out.println("id packet: " + cords[i].getID() + " id daqui: " + ID);
                setLocation(cords[i].getX(), cords[i].getY());      
            }
        }
    }
}