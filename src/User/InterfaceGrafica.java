package User;

import javax.swing.*;
import java.awt.*;

public class InterfaceGrafica {
    public static void main(String[] args) {
        new InterfaceGrafica().iniciar();
    }

    public void iniciar() {
        JFrame frame = new JFrame("Academia de Dragões");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new CardLayout());

        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();

        // Painéis da aplicação
        JPanel menuPanel = new MenuPrincipal(frame, cardLayout).getPanel();
        JPanel gamePanel = new GamePanel(frame, cardLayout).getPanel();

        // Adiciona os painéis ao frame
        frame.add(menuPanel, "Menu");
        frame.add(gamePanel, "Game");

        // Exibição da janela
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}