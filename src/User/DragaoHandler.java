package User;

import classes.dragoesIntegracoes.*;
import javax.swing.*;
import java.util.Random;

public class DragaoHandler {
    private JTextArea gameTextArea;
    private Dragao dragaoSelvagem;

    public DragaoHandler(JTextArea gameTextArea) {
        this.gameTextArea = gameTextArea;
        criarDragaoSelvagem();
    }

    private void criarDragaoSelvagem() {
        try {
            Dragao dragao = new Dragao("Furia da Noite");
            dragaoSelvagem = dragao.dragaoSelvagem();
            gameTextArea.append("Um dragão selvagem apareceu: " + dragaoSelvagem.getNome() + "!\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar dragão: " + e.getMessage());
            e.printStackTrace();
            gameTextArea.append("Erro ao criar dragão selvagem. Usando dragão padrão.\n");
        }
    }

    public void domar(Treinador treinador) {
        Domar domar = new Domar(dragaoSelvagem, gameTextArea);
        if (domar.tentarDomar()) {
            treinador.getListaDragoes().add(dragaoSelvagem);
            treinador.setNumDragoes(treinador.getNumDragoes() + 1);
            gameTextArea.append("Parabéns! Você domou o dragão: " + dragaoSelvagem.getNome() + "!\n");
            interagirComDragao(dragaoSelvagem);
        } else {
            gameTextArea.append("Você falhou em domar o dragão.\n");
        }
    }

    void interagirComDragao(Dragao dragao) {
        String[] opcoes = {"Alimentar", "Alterar Status", "Brincar", "Mostrar Status", "Voltar ao menu"};
        while (true) {
            int escolha = JOptionPane.showOptionDialog(null, "O que deseja fazer com seu dragão?", "Interagir com Dragão",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            gameTextArea.setText(""); // Limpa o texto da JTextArea

            switch (escolha) {
                case 0:
                    try {
                        String comida = JOptionPane.showInputDialog(null, "Escolha a comida (Peixe, Frango, Rocha):");
                        dragao.alimentar(comida);
                        gameTextArea.append("Você alimentou o dragão com " + comida + ".\n");
                    } catch (Exception e) {
                        gameTextArea.append(e.getMessage() + "\n");
                    }
                    break;
                case 1:
                    try {
                        String status = JOptionPane.showInputDialog(null, "Escolha o status (fome, felicidade):");
                        int quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade:"));
                        dragao.alterarStatus(status, quantidade);
                        gameTextArea.append("Você alterou o status do dragão.\n");
                    } catch (Exception e) {
                        gameTextArea.append(e.getMessage() + "\n");
                    }
                    break;
                case 2:
                    dragao.brincar();
                    gameTextArea.append("Você brincou com o dragão!\n");
                    break;
                case 3:
                    gameTextArea.append("Status do Dragão:\n");
                    gameTextArea.append("Nome: " + dragao.getNome() + "\n");
                    gameTextArea.append("Espécie: " + dragao.getNomeEspecie() + "\n");
                    gameTextArea.append("Vida: " + dragao.getVida() + "\n");
                    gameTextArea.append("Fome: " + dragao.getFome() + "\n");
                    gameTextArea.append("Felicidade: " + dragao.getFelicidade() + "\n");
                    gameTextArea.append("Domesticado: " + (dragao.isDomesticado() ? "Sim" : "Não") + "\n");
                    break;
                case 4:
                    return; // Voltar ao menu
                default:
                    gameTextArea.append("Opção inválida. Tente novamente.\n");
            }
        }
    }

    public void fugir() {
        gameTextArea.setText(""); // Limpa o texto da JTextArea
        String[] mortes = {
            "Caiu de um penhasco e morreu.",
            "O dragão te engoliu.",
            "O dragão te carbonizou.",
            "Bateu em uma árvore e desmaiou."
        };
        gameTextArea.append("Vocês fugiram como covardes!\n" + mortes[new Random().nextInt(mortes.length)] + "\n");
    }
}