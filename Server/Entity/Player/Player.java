package Entity.Player;

import java.io.IOException;
import java.net.InetAddress;

public class Player extends PlayerAttributes{
    private static int ID = 0;
    private final int ID2;
    private InetAddress address;
    private PlayerCords cords;
    private float positionX = 0f;
    private float positionY = 0f;
    private float positionAttackX = 0f;
    private float positionAttackY = 0f;
    private Boolean attack;

    public Player(InetAddress address) {
        cords = new PlayerCords(ID);
        this.address = address;
        ID2 = ID;
        ID++;
    }

    public void runningCoords() throws IOException {
        positionY += velocityY;
        positionX += velocityX;
        positionAttackY = positionY;
        cords.setX(positionX);
        cords.setY(positionY);

        if (HEIGHT + velocityY + positionY >= 450) {
            velocityY = 0;
        } else {
            velocityY += gravity;
        }
    }

    public void play(int keyCode) throws IOException {
        runningCoords();
        switch (keyCode) {
            case 68:
                this.velocityX = 4;
                positionAttackX = positionX;
                cords.setLeftOrRight(true);
                break;
            case 65:
                this.velocityX = -4;
                positionAttackX = positionX - 100;
                cords.setLeftOrRight(false);
                break;
            case 32:
                if (positionY > 250) {
                    this.velocityY = -8;
                }
                break;
            case 74:
                this.setAttack(true);
                break;
            case 75:
                System.out.println("defendeu");
                break;
            default:
                this.velocityX = 0;
                break;
        }
    }

    public void verifyColision(Player[] players) throws InterruptedException {
        for (int i = 0; i < players.length; i++) {
            try {
                if(players[i] != null){
                if (this.getID2() != players[i].getID2() && this.getTimeAttack() <= 0) {

                    if (playerAttackColision(players[i]) && this.getAttack()) {
                        this.playerDemage(players[i]);
                        this.setAttack(false);
                        this.setTimeAttack();
                        System.out.println("acertou");

                    } else if (this.getAttack()) {
                        this.setAttack(false);
                        this.setTimeAttack();
                    }
                }
            }
                Thread.sleep(0, 1);
            } catch (NullPointerException e) {
                System.out.println("ERRO AO VERIFICAR COLISAO");
            }
        }
    }

    private Boolean playerAttackColision(Player p) {
        return this.positionAttackX + this.attackX >= p.positionX
                && this.positionAttackX <= p.positionX + p.WIDTH
                && this.positionAttackY + this.attackY >= p.positionY
                && this.positionAttackY <= p.positionY + p.HEIGHT;
    }

    private void playerDemage(Player p){
        p.getCords().setLife(p.getCords().getLife() - 5);
    }

    public void setTimeAttack() {
        timeAttack = 510;
    }

    public int getTimeAttack() {
        timeAttack--;
        return timeAttack;
    }

    public PlayerCords getCords() {
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