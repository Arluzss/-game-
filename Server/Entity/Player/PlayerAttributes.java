package Entity.Player;

/**
 * PlayerAttributes
 */
public class PlayerAttributes {

    protected int timeAttack = 510;
    protected float velocityX = 0f;
    protected float velocityY = 0f;
    protected int attackX = 150;
    protected int attackY = 55;
    protected float positionX = 0f;
    protected float positionY = 0f;
    protected float positionAttackX = 0f;
    protected float positionAttackY = 0f;
    protected final float GRAVITY = 0.1f;
    protected final int WIDTH = 50;
    protected final int HEIGHT = 150;

    public void setTimeAttack(int timeAttack) {
        this.timeAttack = timeAttack;
    }

    public int getTimeAttack() {
        return timeAttack;
    }

    public int getActualTimeAttack() {
        return timeAttack;
    }
}