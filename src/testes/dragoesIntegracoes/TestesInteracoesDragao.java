package testes.dragoesIntegracoes;

import ExceptionClasses.ComidaInvalidaException;
import ExceptionClasses.StatusInvalidoException;
import classes.dragoesIntegracoes.Dragao;

public class TestesInteracoesDragao {
    public static void main(String[] args) {
        try {
            Dragao dragao = new Dragao("Furia da Noite");
            dragao.alimentar("Peixe");
            dragao.brincar();
            dragao.alterarStatus("fome", -20);
            dragao.recuperarVida(15);
            System.out.println("Vida: " + dragao.getVida());
            System.out.println("Fome: " + dragao.getFome());
            System.out.println("Felicidade: " + dragao.getFelicidade());
        } catch (ComidaInvalidaException | StatusInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
