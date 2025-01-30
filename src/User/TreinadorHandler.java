package User;

import classes.dragoesIntegracoes.*;
import javax.swing.*;

public class TreinadorHandler {
    // essa classe é responsável por criar um treinador 
    //e verificar se o arquivo existe
    private Treinador treinador;
    private JTextArea gameTextArea;

    public TreinadorHandler(JTextArea gameTextArea) {
        this.gameTextArea = gameTextArea;
        criarTreinador();
    }

    private void criarTreinador() {
        try {
            int tentativas = 0;
            while (tentativas < 3) {
                System.out.println("Tentando criar treinador - tentativa " + (tentativas + 1));
                String nome = JOptionPane.showInputDialog(null, "Digite seu nome:");
                System.out.println("Nome digitado: " + nome);
                
                if (nome == null || nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome inválido. Tente novamente.");
                    tentativas++;
                } else {
                    try {
                        System.out.println("Criando novo treinador com nome: " + nome);
                        treinador = new Treinador(nome);
                        
                        System.out.println("Verificando arquivo...");
                        Arquivo arquivo = new Arquivo();
                        try {
                            arquivo.verificarArquivo(treinador);
                            System.out.println("Arquivo verificado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao verificar arquivo: " + e.getMessage());
                            e.printStackTrace();
                        }
                        
                        gameTextArea.append("Bem-vindo, " + nome + "! Sua jornada começou.\n");
                        System.out.println("Treinador criado com sucesso");
                        return;
                    } catch (Exception e) {
                        System.out.println("Erro ao criar treinador: " + e.getMessage());
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao criar treinador: " + e.getMessage());
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Número máximo de tentativas atingido.");
        } catch (Exception e) {
            System.out.println("Erro crítico: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro crítico: " + e.getMessage());
        }
    }

    public Treinador getTreinador() {
        return treinador;
    }
}
