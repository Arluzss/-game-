package Entity.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends JPanel {
    private final int ID;
    private static int COUNT = 0;
    private int WIDTH = 250;
    private int HEIGHT = 160;
    private JPanel rangeAttackLeft;
    private JPanel rangeAttackRight;
    private JPanel playerSLA;
    private JPanel playerHP;
    private Image image;

    public Player() {
        this.ID = COUNT;
        COUNT++;
        initPlayer();
    }

    private void initPlayer() {
        image = new ImageIcon("Entity/Player/Images/galoDireita.png").getImage();
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        makePlayerStats();
        add(playerHP);
    }

    private void makePlayerStats() {
        // rangeAttackLeft = new JPanel();
        // rangeAttackRight = new JPanel();
        // playerSLA = new JPanel();
        playerHP = new JPanel();
        playerHP.setSize(130, 50);
        playerHP.setBackground(Color.GREEN);
        playerHP.setLocation(55, 0);
        // playerSLA.setSize(50, 110);
        // playerSLA.setOpaque(true);
        // playerSLA.setLocation(95, 50);
        // rangeAttackLeft.setSize(145, 110 / 2);
        // rangeAttackLeft.setLocation(0, 50);
        // rangeAttackLeft.setOpaque(false);
        // rangeAttackRight.setSize(145, 110 / 2);
        // rangeAttackRight.setLocation(105, 50);
        // rangeAttackRight.setOpaque(false);
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(image, 75, 32, getWidth() - 120, getHeight(), this);
    }

    public int getID() {
        return this.ID;
    }

    private Image leftOrRight(Boolean leftOrRight) {
        if (leftOrRight) {
            return new ImageIcon("Entity/Player/Images/galoDireita.png").getImage();
        } else {
            return new ImageIcon("Entity/Player/Images/galoEsquerda.png").getImage();
        }
    }

    public void runningCoords(PlayerCords cords) throws InterruptedException {
        try {
            if (cords != null) {
                if (cords.getID() == this.ID) {
                    setLocation(cords.getX(), cords.getY());
                    image = leftOrRight(cords.getLeftOrRight());
                    playerHP.setSize(cords.getLife(), 50);
                    System.out.println("ID: " + cords.getID() + " X: " + cords.getX() + " Y: " + cords.getY() + " LeftOrRight: " + cords.getLeftOrRight() + " Life: " + cords.getLife());
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getStackTrace());
        }
    }
}