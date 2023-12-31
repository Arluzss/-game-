package Window.OtherWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import Window.GamePanel;
import Window.WindowA;

public class AddIpScreen extends JFrame implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private JTextField ip;
    private JButton okButton;
    private JTextField port;

    public AddIpScreen() {
        initComponents();
    }

    private void initComponents() {
        setTitle("addAddress");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addText();
        addButtons();
    }

    private void addButtons() {
        okButton = new JButton("OK");
        add(okButton);
        okButton.setBounds(180, 350, 100, 100);
        okButton.addActionListener(this);
    }

    private void addText() {
        ip = new JTextField("26.30.244.54");
        port = new JTextField("2001");
        add(ip);
        add(port);
        ip.setBounds(100, 250, 200, 50);
        port.setBounds(320, 250, 100, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new WindowA(new GamePanel(new Socket(ip.getText(), Integer.parseInt(port.getText()))));
            this.setVisible(false);
        } catch (NumberFormatException | IOException e1) {
            e1.printStackTrace();
        }
    }
}