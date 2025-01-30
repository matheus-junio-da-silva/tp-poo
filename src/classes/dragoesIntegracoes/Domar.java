package classes.dragoesIntegracoes;

import javax.swing.*;
import java.util.Random;

public class Domar {
    private final Dragao dragao;
    private final Random random;
    private int tentativas;
    private static final int MAX_TENTATIVAS = 3;
    private JTextArea gameTextArea;

    public Domar(Dragao dragao, JTextArea gameTextArea) {
        this.dragao = dragao;
        this.random = new Random();
        this.tentativas = 0;
        this.gameTextArea = gameTextArea;
    }

    public boolean tentarDomar() {
        if (dragao.isDomesticado()) {
            gameTextArea.append("O dragão já está domesticado.\n");
            return true;
        }

        while (tentativas < MAX_TENTATIVAS && !dragao.isDomesticado()) {
            gameTextArea.append("\nTentativa " + (tentativas + 1) + " de " + MAX_TENTATIVAS + "\n");
            String[] opcoes = {"Oferecer comida", "Aproximar devagar", "Mostrar que não é ameaça"};
            int escolha = JOptionPane.showOptionDialog(null, "Escolha sua abordagem:", "Domar Dragão",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            int bonus = 0;
            switch (escolha) {
                case 0:
                    bonus = 5;
                    gameTextArea.append("Você oferece comida ao dragão...\n");
                    break;
                case 1:
                    bonus = 3;
                    gameTextArea.append("Você se aproxima lentamente...\n");
                    break;
                case 2:
                    bonus = 4;
                    gameTextArea.append("Você mostra suas mãos vazias...\n");
                    break;
                default:
                    gameTextArea.append("O dragão se assusta com seu movimento brusco!\n");
                    break;
            }

            int chanceDomar = random.nextInt(21) + bonus; // Chance de 0 a 20 + bonus
            if (chanceDomar > 15) {
                dragao.setDomesticado(true);
                gameTextArea.append("Parabéns! Você conseguiu domar o " + dragao.getNomeEspecie() + "\n");
                return true;
            } else {
                gameTextArea.append("O dragão ainda não confia em você. Tente novamente!\n");
                tentativas++;
            }
        }

        if (!dragao.isDomesticado()) {
            gameTextArea.append("O dragão fugiu! Você falhou em domá-lo.\n");
            return false;
        }
        return dragao.isDomesticado();
    }
}