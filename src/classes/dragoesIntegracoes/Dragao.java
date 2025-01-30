package classes.dragoesIntegracoes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import ExceptionClasses.ComidaInvalidaException;
import ExceptionClasses.StatusInvalidoException;
import interfaces.InteracoesDragao;

public class Dragao extends Especie implements InteracoesDragao {
    private String nome;
    private int vida;
    private int fome;
    private int felicidade;
    private boolean domesticado;
    private final int sexo; // 0 = macho, 1 = femea
        
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    
    // Construtor
    public Dragao(String nomeEspecie) {
        super(nomeEspecie);
        this.domesticado = false;
        this.sexo = random.nextInt(2);
        this.iniciaStats();
    }
    public final void iniciaStats() {
        this.setNome(this.getNomeEspecie());
        this.setVida(100);
        this.setFome(100);
        this.setFelicidade(100);
    }

    public Dragao dragaoSelvagem() {
        String novoDragaoEspecie = sorteiaEspecie();
    
        System.out.println("Um " + novoDragaoEspecie + " selvagem aparecesse.\n");
    
        return new Dragao(novoDragaoEspecie);
    }  

    // Map de comidas e efeitos por espécie
    private static final Map<String, Map<String, Integer>> efeitosComida = new HashMap<>();
    static {
        Map<String, Integer> furiaDaNoiteEfeitos = new HashMap<>();
        furiaDaNoiteEfeitos.put("Peixe", 20);
        furiaDaNoiteEfeitos.put("Frango", 10);
        furiaDaNoiteEfeitos.put("Rocha", -10);

        Map<String, Integer> gronckleEfeitos = new HashMap<>();
        gronckleEfeitos.put("Rocha", 20);
        gronckleEfeitos.put("Frango", 5);
        gronckleEfeitos.put("Peixe", -5);

        Map<String, Integer> nadderMortalEfeitos = new HashMap<>();
        nadderMortalEfeitos.put("Peixe", 10);
        nadderMortalEfeitos.put("Frango", 15);
        nadderMortalEfeitos.put("Rocha", -5);
    
        Map<String, Integer> pesadeloMonstruosoEfeitos = new HashMap<>();
        pesadeloMonstruosoEfeitos.put("Frango", 20);
        pesadeloMonstruosoEfeitos.put("Peixe", 10);
        pesadeloMonstruosoEfeitos.put("Rocha", -10);
    
        Map<String, Integer> zipperarrepianteEfeitos = new HashMap<>();
        zipperarrepianteEfeitos.put("Peixe", 15);
        zipperarrepianteEfeitos.put("Rocha", 10);
        zipperarrepianteEfeitos.put("Frango", -10);
    
        // Adiciona os mapas ao map principal
        efeitosComida.put("Furia da Noite", furiaDaNoiteEfeitos);
        efeitosComida.put("Gronckle", gronckleEfeitos);
        efeitosComida.put("Nadder Mortal", nadderMortalEfeitos);
        efeitosComida.put("Pesadelo Monstruoso", pesadeloMonstruosoEfeitos);
        efeitosComida.put("Zipperarrepiante", zipperarrepianteEfeitos);
    }

    @Override
    public void alimentar(String comida) throws ComidaInvalidaException {
        Map<String, Integer> efeitos = efeitosComida.get(this.getNomeEspecie());
        if (efeitos != null && efeitos.containsKey(comida)) {
            int efeito = efeitos.get(comida);
            this.recuperarVida(efeito);
            try {
                this.alterarStatus("fome", 10);
                this.alterarStatus("felicidade", 5);
            } catch (StatusInvalidoException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new ComidaInvalidaException("Este dragão não come " + comida);
        }
    }

    @Override
    public void recuperarVida(int quantidade) {
        this.vida += quantidade;
        if (this.vida > 100) {
            this.vida = 100;
        } else if (this.vida < 0) {
            this.vida = 0;
        }
    }

    @Override
    public void alterarStatus(String tipo, int quantidade) throws StatusInvalidoException {
        switch (tipo.toLowerCase()) {
            case "fome":
                this.fome += quantidade;
                if (this.fome > 100) this.fome = 100;
                if (this.fome < 0) {
                    this.fome = 0;
                    this.recuperarVida(-10);
                }
                break;
            case "felicidade":
                this.felicidade += quantidade;
                if (this.felicidade > 100) this.felicidade = 100;
                if (this.felicidade < 0) this.felicidade = 0;
                break;
            default:
                throw new StatusInvalidoException("Tipo de status inválido: " + tipo);
        }
    }

    @Override
    public void brincar() {
        try {
            this.alterarStatus("fome", -5);
        } catch (StatusInvalidoException e) {
            System.out.println(e.getMessage());
        }
        try {
            this.alterarStatus("felicidade", 10);
        } catch (StatusInvalidoException e) {
            System.out.println(e.getMessage());
        }
        this.recuperarVida(-2);
    }

    public void mostrarStatus() {
        System.out.println("Status do Dragão:");
        System.out.println("Nome: " + this.nome);
        System.out.println("Espécie: " + this.getNomeEspecie());
        System.out.println("Vida: " + this.vida);
        System.out.println("Fome: " + this.fome);
        System.out.println("Felicidade: " + this.felicidade);
        System.out.println("Domesticado: " + (this.domesticado ? "Sim" : "Não"));
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getFome() {
        return fome;
    }
    public void setFome(int fome) {
        this.fome = fome;
    }

    public int getFelicidade() {
        return felicidade;
    }
    public void setFelicidade(int felicidade) {
        this.felicidade = felicidade;
    }

    public boolean isDomesticado() {
        return domesticado;
    }
    public void setDomesticado(boolean domesticado) {
        this.domesticado = domesticado;
    }

    public int getSexo() {
        return sexo;
    }
}
