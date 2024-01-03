package Entity.Player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
    private final int ID;
    private static int COUNT = 0;
    private final int WIDTH = 200;
    private final int HEIGHT = 160;
    private PlayerInfo cords;
    private Image image;
    
    public Player() {
        this.ID = COUNT;
        COUNT++;
    }

    public void draw(Graphics2D g) throws NullPointerException{
        lifePLayer(g);
        movePlayerDirection();
    }

    private void movePlayerDirection(){
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
            image = new ImageIcon("Entity/Player/Images/galoAtaqueDireita.png").getImage();
        }
        if(cords.getAttack() && !cords.getLeftOrRight()){
            image = new ImageIcon("Entity/Player/Images/galoAtaqueEsquerda.png").getImage();
        }
    }

    private void lifePLayer(Graphics2D g){
        g.drawImage(image, cords.getX(), cords.getY(), WIDTH, HEIGHT, null);
        g.setColor(Color.GREEN);
        if(cords.getLife() > 0){
            g.fillRect(cords.getX() , cords.getY() - 50, cords.getLife(), HEIGHT /4);
            System.out.println(cords.getLife());
        }
    }

    public void setCords(PlayerInfo cords) {
        this.cords = cords;
    }

    public int getID() {
        return this.ID;
    }
}