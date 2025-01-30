package classes.dragoesIntegracoes;

import java.util.ArrayList;
import java.util.Scanner;

public class Treinador {
    private String nome;
    private ArrayList<Dragao> listaDragoes;
    private int numDragoes;

    Scanner scanner = new Scanner(System.in);

    public Treinador(String nome) {
        this.nome = nome;
        this.numDragoes = 0;
        listaDragoes = new ArrayList<>();
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Dragao> getListaDragoes() {
        return listaDragoes;
    }
    public void setListaDragoes(ArrayList<Dragao> listaDragoes) {
        this.listaDragoes = listaDragoes;
    }
    
    public int getNumDragoes() {
        return numDragoes;
    }
    public void setNumDragoes(int numDragoes) {
        this.numDragoes = numDragoes;
    }

    public void adicionarDragaoLista(Dragao dPH, ArrayList<Dragao> listaDragoes) {
    }
}
