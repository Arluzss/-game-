package Entity.Player;

import java.io.IOException;
import java.net.InetAddress;

public class Player {
    private InetAddress address;
    private PlayerCords cords;
    private static int ID = 0;
    private float x = 0f;
    private float y = 0f;
    private float velocityX = 0f;
    private float velocityY = 0f;
    private float gravity = 0.2f;
    private final int HEIGHT = 150;
    
    public Player(InetAddress address) {
        cords = new PlayerCords(ID);
        this.address = address;
        ID++;
    }

    public void runningCoords() throws IOException {
        y += velocityY;
        x += velocityX;
        cords.setX(x);
        cords.setY(y);

        if (HEIGHT + velocityY + y >= 500) {
            velocityY = 0;
        } else {
            velocityY += gravity;
        }
    }

    public void keyBoard(int keyCode) throws IOException{
        runningCoords();
        switch (keyCode) {
            case 68:
                this.velocityX = 4;
                System.out.println("direito");
                break;
            case 65:
                this.velocityX = -4;
                System.out.println("esquerdo");
                break;
            case 32:
                this.velocityY = -8;
                System.out.println("pulouu?");
                break;
            default:
                this.velocityX = 0;
                break;
        }
    }

    public PlayerCords getCords() {
        return cords;
    }

    public static int getID() {
        return ID;
    }

    public InetAddress getAddress(){
        return address;
    }

}