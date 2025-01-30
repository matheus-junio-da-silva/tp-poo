package interfaces;

import ExceptionClasses.ComidaInvalidaException;
import ExceptionClasses.StatusInvalidoException;

public interface InteracoesDragao {
    void alimentar(String comida) throws ComidaInvalidaException;
    void recuperarVida(int quantidade);
    void alterarStatus(String tipo, int quantidade) throws StatusInvalidoException;
    void brincar();
}
