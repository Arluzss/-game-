package Entity.Player;

import java.io.Serializable;

public class PlayerCords implements Serializable{
    private static final long serialVersionUID = 123456789L;
    private final int ID;
    private int x;
    private int y;

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

    @Override
    public String toString() {
        return "PlayerCords [ID=" + ID + ", x=" + x + ", y=" + y + "]";
    }

}
