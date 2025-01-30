// Chamada no main: arquivo.verificarArquivo(<Treinador>);
package classes.dragoesIntegracoes;
import java.io.*;

public class Arquivo {
    public void verificarArquivo(Treinador tPH) {
        String diretorio = definirDiretorio(); // Obtem o diretório onde salvar/ler
        File arquivo = new File(diretorio, tPH.getNome()); // Cria referência ao arquivo
    
        if (arquivo.exists()) 
            lerArquivo(tPH); // Chama a função para leitura
        else
            gravarArquivo(tPH); // Chama a função para gravação
    }

    private String definirDiretorio() {
        String diretorioBase = System.getProperty("user.dir"); // Diretório atual
        File diretorio = new File(diretorioBase, "Save");
    
        // Verifica se o diretório existe, caso contrário tenta criá-lo
        if (!diretorio.exists()) {
            if (!diretorio.mkdir()) {
                System.out.println("Não foi possível criar o diretório: " + diretorio.getAbsolutePath());
            }
        }
        return diretorio.getAbsolutePath(); // Retorna o caminho absoluto do diretório
    }

    public void gravarArquivo(Treinador tPH) {
        // Define o diretório específico para o treinador
        String diretorioTreinador = definirDiretorio();
    
        String caminhoArquivo = diretorioTreinador + "\\" + tPH.getNome() + ".txt";
    
        // Abre o arquivo uma única vez
        try (BufferedWriter escrita = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Itera sobre os dragões do treinador e grava os dados
            for (Dragao dragao : tPH.getListaDragoes()) {
                System.out.println("Dragao " + dragao.getNome());
                escrita.write(dragao.getNomeEspecie() + "," + dragao.getNome() + "," + dragao.getVida() + "," + dragao.getFome() + "," + dragao.getFelicidade() + "," + dragao.isDomesticado());
                escrita.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro na escrita do arquivo: " + e.getMessage());
        }
    }
    

    public void lerArquivo (Treinador tPH) {
        String diretorioTreinador = definirDiretorio();

        String caminhoArquivo = diretorioTreinador + "\\" + tPH.getNome() + ".txt";

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String dragao = leitor.readLine();

            while (dragao != null) { 
                String[] fields = dragao.split(","); 

                Dragao dPH = new Dragao(fields[0]);

                dPH.setNome(fields[1]);
                dPH.setVida(Integer.parseInt(fields[2]));
                dPH.setFome(Integer.parseInt(fields[3]));
                dPH.setFelicidade(Integer.parseInt(fields[4]));
                dPH.setDomesticado(Boolean.parseBoolean(fields[5]));

               // tPH.adicionarDragaoLista(dPH, tPH.getListaDragoes());
                dragao = leitor.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo: " + e.getMessage());
        }
    }
}
