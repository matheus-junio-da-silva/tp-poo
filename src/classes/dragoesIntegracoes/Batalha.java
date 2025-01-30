package classes.dragoesIntegracoes;

import java.util.Random;

public class Batalha {
    private final Dragao atacante;
    private final Dragao defensor;
    private final Random random;

    public Batalha(Dragao atacante, Dragao defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.random = new Random();
    }

    public void iniciarBatalha() {
        System.out.println("A batalha começou entre " + atacante.getNome() + " e " + defensor.getNome() + "!");

        while (atacante.getVida() > 0 && defensor.getVida() > 0) {
            executarTurno(atacante, defensor);
            if (defensor.getVida() > 0) {
                executarTurno(defensor, atacante);
            }
        }

        if (atacante.getVida() > 0) {
            System.out.println(atacante.getNome() + " venceu a batalha!");
        } else {
            System.out.println(defensor.getNome() + " venceu a batalha!");
        }
    }

    private void executarTurno(Dragao atacante, Dragao defensor) {
        System.out.println("\n" + atacante.getNome() + " está atacando " + defensor.getNome() + "!");

        int esquivaChance = random.nextInt(100);
        int limiteEsquiva = 15 + (defensor.getVelocidade() * 25 / 100); // 15% a 40%

        if (esquivaChance < limiteEsquiva) {
            System.out.println(defensor.getNome() + " esquivou do ataque!");
            return;
        }

        int dano = atacante.getAtaque() - (defensor.getDefesa() / 2);
        if (dano <= 0) dano = 1; // Dano mínimo de 1

        defensor.recuperarVida(-dano);
        System.out.println(atacante.getNome() + " causou " + dano + " de dano em " + defensor.getNome());
    }

    public boolean tentarRecuo(Dragao dragao) {
        System.out.println(dragao.getNome() + " está tentando recuar da batalha!");

        int chanceRecuo = random.nextInt(100);
        if (chanceRecuo < 50) {
            System.out.println(dragao.getNome() + " conseguiu recuar com sucesso!");
            return true;
        } else {
            System.out.println(dragao.getNome() + " falhou em recuar!");
            return false;
        }
    }
}
