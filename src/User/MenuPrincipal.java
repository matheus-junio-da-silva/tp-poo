package User;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal {
    private JPanel menuPanel;

    public MenuPrincipal(JFrame frame, CardLayout cardLayout) {
        // Painel principal com fundo customizado
        menuPanel = new BackgroundPanel("/img/imagem1.png"); // Substitua pelo caminho correto
        menuPanel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("ACADEMIA DE DRAGÕES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 42));
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        menuPanel.add(titleLabel, BorderLayout.NORTH);

        // Painel inferior para botões
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        // Painel para o botão "Jogar"
        JPanel jogarPanel = new JPanel(new BorderLayout());
        jogarPanel.setOpaque(false);

        ImageIcon jogarIcon = loadImageIcon("/img/Jogar.png", 300, 100); // Ajuste conforme necessário
        JButton jogarButton = new JButton(jogarIcon);
        jogarButton.setBorderPainted(false);
        jogarButton.setContentAreaFilled(false);
        jogarButton.setFocusPainted(false);
        jogarButton.setOpaque(false);
        jogarButton.setPreferredSize(new Dimension(300, 100));

        // Fundo preto curvado para o botão "Jogar"
        JPanel fundoPretoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
            }
        };
        fundoPretoPanel.setOpaque(false);
        fundoPretoPanel.setPreferredSize(new Dimension(452, 150));
        fundoPretoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
        fundoPretoPanel.add(jogarButton);

        jogarPanel.add(fundoPretoPanel, BorderLayout.WEST);
        bottomPanel.add(jogarPanel, BorderLayout.WEST);

        // Botão "Sair" no canto inferior direito
        JPanel sairPanel = new JPanel(new BorderLayout());
        sairPanel.setOpaque(false);

        JButton sairButton = new JButton("SAIR");
        sairButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        sairButton.setForeground(Color.RED);
        sairButton.setBackground(Color.BLACK);
        sairButton.setFocusPainted(false);
        sairButton.setBorderPainted(false);
        sairButton.setOpaque(false);
        sairButton.setPreferredSize(new Dimension(80, 40));

        sairButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Até a próxima!");
            System.exit(0);
        });

        sairPanel.add(sairButton, BorderLayout.EAST);
        bottomPanel.add(sairPanel, BorderLayout.EAST);

        menuPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Evento do botão "Jogar"
        jogarButton.addActionListener(e -> {
            cardLayout.show(frame.getContentPane(), "Game");
        });
    }

    public JPanel getPanel() {
        return menuPanel;
    }

    // Método utilitário para carregar imagens com escala
    private ImageIcon loadImageIcon(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(path));
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem: " + path);
            return new ImageIcon();
        }
    }
}
