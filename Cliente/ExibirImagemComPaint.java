import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExibirImagemComPaint extends JFrame {

    private Image imagem;

    public ExibirImagemComPaint() {
        // Configuração básica da janela
        setTitle("Exibir Imagem com Paint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Carrega a imagem usando ImageIcon
        imagem = new ImageIcon("Entity/Player/Images/galoDireita.png").getImage();

        // Cria um JPanel personalizado
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenha a imagem no JPanel
                g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Adiciona o JPanel à janela
        add(panel);

        // Ajusta automaticamente o tamanho da janela com base no tamanho da imagem
        pack();

        // Centraliza a janela na tela
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Cria e exibe a janela
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ExibirImagemComPaint().setVisible(true);
            }
        });
    }
}
