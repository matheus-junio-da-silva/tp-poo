package classes.dragoesIntegracoes;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

abstract class Especie {
    protected final String nomeEspecie;
    protected final int idEspecie;

    protected int ataque;
    protected int defesa;
    protected int velocidade;

    Random random = new Random();

    // CONSTRUTOR
    protected Especie(String nomeEspecie) {
        this.nomeEspecie = nomeEspecie;
        switch(nomeEspecie) {
            case "Furia da Noite": {
                ataque = 15;
                defesa = 18;
                velocidade = 20;
                idEspecie = 1;
                break;
            }
            case "Nadder Mortal": {
                ataque = 10;
                defesa = 16;
                velocidade = 8;
                idEspecie = 2;
                break;
            }
            case "Pesadelo Monstruoso": {
                ataque = 15;
                defesa = 12;
                velocidade = 16;
                idEspecie = 3;
                break;
            }
            case "Zipperarrepiante": {
                ataque = 18;
                defesa = 10;
                velocidade = 10;
                idEspecie = 4;
                break;
            }
            case "Gronckle": {
                ataque = 8;
                defesa = 20;
                velocidade = 4;
                idEspecie = 5;
                break;
            }
            default: throw new IllegalArgumentException("Especie desconhecida: " + nomeEspecie);
        }
    }

    // SORTEIA ESPECIE
    protected String escolheIndiceEspecie(List<String> lista) {
        int indice = random.nextInt(lista.size());
        return lista.get(indice);
    }
    protected String sorteiaEspecie() {
        List<String> especies = Arrays.asList("Furia da Noite", "Nadder Mortal", "Pesadelo Monstruoso", "Zipperarrepiante", "Gronckle");
        String classe = escolheIndiceEspecie(especies);
        return classe;
    }
    

    // GETTERS E SETTERS
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getVelocidade() {
        return velocidade;
    }
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public String getNomeEspecie() {
        return nomeEspecie;
    }

    public int getIdEspecie() {
        return idEspecie;
    }
}
