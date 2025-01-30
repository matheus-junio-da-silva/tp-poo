package User;

import classes.dragoesIntegracoes.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class GamePanel {
    private JPanel gamePanel;
    private JTextArea gameTextArea;
    private TreinadorHandler treinadorHandler;
    private DragaoHandler dragaoHandler;
    private JScrollPane scrollPane;
    private JPanel bottomPanel;

    public GamePanel(JFrame frame, CardLayout cardLayout) {
        // Faz o frame ocupar toda a tela
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Carregar a imagem de fundo
        gamePanel = new BackgroundPanel("/img/historinha.jpg");
        gamePanel.setLayout(new BorderLayout());

        // Configuração da área de texto
        gameTextArea = new JTextArea();
        gameTextArea.setFont(new Font("Arial", Font.PLAIN, 15)); // Texto maior
        gameTextArea.setEditable(false);
        gameTextArea.setLineWrap(true);
        gameTextArea.setWrapStyleWord(true);
        gameTextArea.setOpaque(false);
        gameTextArea.setBackground(new Color(0, 0, 0, 0));
        gameTextArea.setForeground(Color.WHITE);

        // Criando JScrollPane para permitir rolagem
        scrollPane = new JScrollPane(gameTextArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel que segura a JTextArea e se redimensiona
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Configurar painel de botões
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setOpaque(false);

        JButton domesticarButton = new JButton("Domesticar");
        JButton fugirButton = new JButton("Fugir");
        JButton interagirButton = new JButton("Interagir com Dragão");

        buttonPanel.add(domesticarButton);
        buttonPanel.add(fugirButton);
        buttonPanel.add(interagirButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona painel inferior ao gamePanel
        gamePanel.add(bottomPanel, BorderLayout.SOUTH);

        // Ajustar dinamicamente o tamanho da área de texto
        gamePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int alturaJanela = gamePanel.getHeight();
                int alturaTexto = (int) (alturaJanela * 0.25); // 25% da altura da janela
                scrollPane.setPreferredSize(new Dimension(gamePanel.getWidth(), alturaTexto));
                bottomPanel.setPreferredSize(new Dimension(gamePanel.getWidth(), alturaTexto + 50)); // Ajusta espaço dos botões
                bottomPanel.revalidate();
            }
        });

        treinadorHandler = new TreinadorHandler(gameTextArea);
        dragaoHandler = new DragaoHandler(gameTextArea);

        // Eventos dos botões
        domesticarButton.addActionListener(e -> {
            adicionarTexto("Tentando domesticar um dragão...");
            dragaoHandler.domar(treinadorHandler.getTreinador());
        });
        fugirButton.addActionListener(e -> {
            adicionarTexto("Você tenta fugir do dragão...");
            dragaoHandler.fugir();
        });
        interagirButton.addActionListener(e -> {
            if (treinadorHandler.getTreinador().getListaDragoes().isEmpty()) {
                adicionarTexto("Você não tem dragões para interagir.");
            } else {
                Dragao dragao = treinadorHandler.getTreinador().getListaDragoes().get(0);
                adicionarTexto("Interagindo com o dragão: " + dragao.getNome());
                dragaoHandler.interagirComDragao(dragao);
            }
        });
    }

    // Método para adicionar texto sem resetar o histórico
    private void adicionarTexto(String texto) {
        gameTextArea.append(texto + "\n");
        gameTextArea.setCaretPosition(gameTextArea.getDocument().getLength()); // Rola para o final automaticamente
    }

    public JPanel getPanel() {
        return gamePanel;
    }

    // Classe para exibir a imagem de fundo e ajustá-la automaticamente
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            java.net.URL imgURL = getClass().getResource(imagePath);
            if (imgURL != null) {
                this.backgroundImage = new ImageIcon(imgURL).getImage();
            } else {
                System.err.println("Erro: Imagem não encontrada -> " + imagePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return Toolkit.getDefaultToolkit().getScreenSize(); // Ajusta ao tamanho da tela
        }
    }
}
