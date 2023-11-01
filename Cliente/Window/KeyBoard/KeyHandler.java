package Window.KeyBoard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public static int keyCode = 0;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
        if(e.getKeyCode() == KeyEvent.VK_A){
            keyCode = e.getKeyCode();
            System.out.println("left pressed");
        }

        if(e.getKeyCode() == KeyEvent.VK_D){
            keyCode = e.getKeyCode();
            System.out.println("rigth pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCode = 0;
    }
}
