package Entity.Player;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
    private final int ID;
    private static int COUNT = 0;
    private final int WIDTH = 200;
    private final int HEIGHT = 160;
    private PlayerCords cords;
    private Image image;
    public Player() {
        this.ID = COUNT;
        COUNT++;
    }

    public void draw(Graphics2D g) throws NullPointerException{
        System.out.println("Cords: " + cords.getX() + " " + cords.getY() + " " + cords.getLeftOrRight() + " " + cords.getAttack());
        if(cords.getLeftOrRight() == null){
            System.out.println("Cords nulo" + getID());
        }
        if(cords.getLeftOrRight() && !cords.getAttack()){
            image = new ImageIcon("Entity/Player/Images/galoDireita.png").getImage();
        }
        if (!cords.getLeftOrRight() && !cords.getAttack()) {
            image = new ImageIcon("Entity/Player/Images/galoEsquerda.png").getImage();
        }
        if(cords.getAttack() && cords.getLeftOrRight()){
            image = new ImageIcon("Entity/Player/Images/galoAtaqueEsquerda.png").getImage();
        }
        if(cords.getAttack() && !cords.getLeftOrRight()){
            image = new ImageIcon("Entity/Player/Images/galoAtaqueDireita.png").getImage();
        }
        g.drawImage(image, cords.getX(), cords.getY(), WIDTH, HEIGHT, null);
    }

    public void setCords(PlayerCords cords) {
        this.cords = cords;
    }

    public int getID() {
        return this.ID;
    }
}