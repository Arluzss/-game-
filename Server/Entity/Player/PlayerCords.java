package Entity.Player;

import java.io.Serializable;

public class PlayerCords implements Serializable{
    private static final long serialVersionUID = 123456789L;
    private final int ID;
    private int x;
    private int y;
    private Boolean leftOrRight;
    private int life = 130;
    private boolean attack = false;

    public PlayerCords(int ID){
        this.ID = ID;
    }

    public int getX() {
        return x;
    }

    public void setX(float x) {
        this.x = (int) x;
    }

    public int getY() {
        return y;
    }

    public void setY(float y) {
        this.y = (int) y;
    }

    public int getID(){
        return ID;
    }

    public Boolean getLeftOrRight() {
        return leftOrRight;
    }

    public void setLeftOrRight(Boolean leftOrRight) {
        this.leftOrRight = leftOrRight;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }
    
    public boolean getAttack() {
        return attack;
    }
    
    public int getLife() {
        return life;
    }
    
    @Override
    public String toString() {
        return "PlayerCords [ID=" + ID + ", x=" + x + ", y=" + y + "]";
    }

   
}
