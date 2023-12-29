package Window;

import javax.swing.JFrame;

public class WindowA extends JFrame {
    
    public WindowA(GamePanel gamePanel) {
        initComponents();
        this.add(gamePanel);
        this.pack();

    }

    private void initComponents() {
        this.setTitle("Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(0, 0);
        this.setVisible(true);
    }
}
