package Entity.Player;

import java.io.IOException;
import java.net.InetAddress;

public class Player extends PlayerAttributes {
    private final int ID2;
    private static int ID = 0;
    private PlayerInfo cords;
    private InetAddress address;
    private final float WALKING_SPEED = 0.80f;
    private final float JUMPING_SPEED = 5.5f;
    private Boolean attack = false;

    public Player(InetAddress address) {
        this.cords = new PlayerInfo(ID);
        this.address = address;
        this.ID2 = ID;
        ID++;
    }

    public void runningCoords() throws IOException {
        this.positionY += velocityY;
        this.positionX += velocityX;
        this.positionAttackY = positionY;
        this.cords.setX(positionX);
        this.cords.setY(positionY);

        if (HEIGHT + velocityY + positionY >= 850) {
            velocityY = 0;
        } else {
            velocityY += GRAVITY;
        }
    }

    public void play(int keyCode) throws IOException {
        runningCoords();
        switch (keyCode) {
            case 68:
                this.velocityX = WALKING_SPEED;
                this.positionAttackX = positionX;
                this.cords.setLeftOrRight(true);
                break;
            case 65:
                this.velocityX = -WALKING_SPEED;
                positionAttackX = positionX - 100;
                this.cords.setLeftOrRight(false);
                break;
            case 32:
                if (positionY > 350) {
                    this.velocityY = -JUMPING_SPEED;
                }
                break;
            case 74:
                if(this.getTimeAttack() <= 0){
                    this.setAttack(true);
                    this.cords.setAttack(true);
                }
                break;
            case 75:
                System.out.println("defendeu");
                break;
            default:
                this.velocityX = 0;
                break;
        }
    }

    public void verifyColision(Player[] players) {
        for (int i = 0; i < players.length; i++) {

            if (this == players[i]) continue;
    
            if (this.getAttack() && this.playerAttackColision(players[i])) {
                this.playerDemage(players[i]);
                this.setAttack(false);
                this.cords.setAttack(false);
                this.setTimeAttack(1510);
            }

            if(this.getAttack()){
                this.setAttack(false);
                this.cords.setAttack(false);
            }

            if (this.getTimeAttack() > 0) {
                this.setTimeAttack(this.getTimeAttack() - 1);
            } else if (this.getAttack()) {
                this.setTimeAttack(1510);
            }

        }
    }

    private Boolean playerAttackColision(Player p) {
        return this.positionAttackX + this.attackX >= p.positionX
                && this.positionAttackX <= p.positionX + p.WIDTH
                && this.positionAttackY + this.attackY >= p.positionY
                && this.positionAttackY <= p.positionY + p.HEIGHT;
    }

    private void playerDemage(Player p) {
        p.getCords().setLife(p.getCords().getLife() - 50);
    }

    public PlayerInfo getCords() {
        return cords;
    }

    public static int getIDs() {
        return ID;
    }

    public int getID2() {
        return ID2;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAttack(Boolean attack) {
        this.attack = attack;
    }

    public Boolean getAttack() {
        return attack;
    }
}