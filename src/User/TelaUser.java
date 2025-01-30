    package User;

    import classes.dragoesIntegracoes.*;
    import java.util.Random;
    import java.util.Scanner;

   /* public class TelaUser {

        public static void Tela() {
            int flag = 0;  // Inicializamos flag para controlar o menu principal
            int escolha = 0;
            int mortes;
            int cont = 0;

            Random random = new Random();
            Scanner scan = new Scanner(System.in);

            // Exibe o menu até que o usuário escolha sair (flag = 3)
            while (flag != 3) {
                    System.out.println("Bem vindo à Academia de Dragões!");
                    System.out.println("Digite o que quer fazer:");
                    System.out.println("1. Jogar");
                    System.out.println("2. Sair");

                    flag = scan.nextInt(); // Lê a escolha do usuário

                    switch (flag) {
                        case 1: {
                            System.out.print("Digite seu nome para começarmos: ");
                            String nome = scan.next();
                            System.out.print("\n");

                            // Cria personagem
                            Treinador tPH = new Treinador(nome);
                            
                            // Check for existing save
                            Arquivo arquivo = new Arquivo();
                            arquivo.verificarArquivo(tPH);

                            System.out.println("______________________________________________________________________________________");
                            System.out.println("Começando jornada...");
                            System.out.println("______________________________________________________________________________________");

                            System.out.printf("Um belo dia, %s e soluco estavam andando pela misteriosa floresta de Berk, conhecido por abrigar dragões perigosos.\n", nome);
                            System.out.printf("De repente, %s ouviu um rugido ensurdecedor vindo das profundezas da floresta...\n", nome);
                            System.out.println("O que será que estava por vir?");
                            System.out.printf("%s não esperava que...\n", nome);

                            // Cria dragão Selvagem
                            Dragao PH = new Dragao("Furia da Noite");
                            Dragao Selvagem = PH.dragaoSelvagem();
                            System.out.println("______________________________________________________________________________________");

                            System.out.println("O que vai fazer?");
                            System.out.println("1. Domesticar");
                            System.out.println("2. Fugir");

                            escolha = scan.nextInt();
                            switch (escolha) {
                                case 1: {
                                    System.out.println("Voce decidiu tentar domar o dragão...");
                                    System.out.println("Se prepare");
                                    
                                    //Domar domar = new Domar(Selvagem);
                                    //boolean sucesso = domar.tentarDomar();
                                    
                                    if (sucesso) {
                                        System.out.println("______________________________________________________________________________________");
                                        System.out.println("Parabéns! Você domou o dragão!");
                                        tPH.getListaDragoes().add(Selvagem);
                                        tPH.setNumDragoes(tPH.getNumDragoes() + 1);
                                        
                                        
                                        // Interagir com o dragão após domá-lo
                                        while (true) {
                                            System.out.println("\nO que deseja fazer com seu dragão?");
                                            System.out.println("1. Alimentar");
                                            System.out.println("2. Alterar Status");
                                            System.out.println("3. Brincar");
                                            System.out.println("4. Mostrar Status");
                                            System.out.println("5. Voltar ao menu");
                                            
                                            int opcao = scan.nextInt();
                                            switch (opcao) {
                                                case 1:
                                                    try {
                                                        System.out.println("Escolha a comida (Peixe, Frango, Rocha): ");
                                                        String comida = scan.next();
                                                        Selvagem.alimentar(comida);
                                                        System.out.println("Você alimentou o dragão com " + comida);
                                                    } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                    break;
                                                case 2:
                                                    try {
                                                        System.out.println("Escolha o status (fome, felicidade): ");
                                                        String status = scan.next();
                                                        System.out.println("Digite a quantidade: ");
                                                        int quantidade = scan.nextInt();
                                                        Selvagem.alterarStatus(status, quantidade);
                                                        System.out.println("Você alterou o status do dragão.");
                                                    } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                    break;
                                                case 3:
                                                    Selvagem.brincar();
                                                    System.out.println("Você brincou com o dragão!");
                                                    break;
                                                case 4:
                                                    Selvagem.mostrarStatus();
                                                    break;
                                                case 5:
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida. Tente novamente.");
                                            }
                                            if (opcao == 5) {
                                                break;
                                            }
                                        }
                                    } else {
                                        System.out.println("______________________________________________________________________________________");
                                        System.out.println("Você falhou em domar o dragão!");
                                    }
                                    // Save progress
                                    arquivo.gravarArquivo(tPH);
                                    break;
                                }
                                case 2: {
                                    System.out.println("Voces fugiram como covarde!");
                                    System.out.printf("O %s te perseguiu pela floresta...\n", Selvagem.getNome());
                                    System.out.println("Voces corriam incansavelmente mas...");
                                    mortes = random.nextInt(4);
                                    switch (mortes) {
                                        case 0:
                                            System.out.println("Cairam do penhasco e morreu.");
                                            break;
                                        case 1:
                                            System.out.printf("O %s te engoliu.\n", Selvagem.getNome());
                                            break;
                                        case 2:
                                            System.out.printf("O %s te carbonizou.\n", Selvagem.getNome());
                                            break;
                                        case 3:
                                            System.out.println("Bateu numa árvore e desmaiou.");
                                            break;
                                    }
                                    System.out.println("Tente de novo o jogo!");
                                    System.out.println("______________________________________________________________________________________");
                                    break;
                                }
                            }
                            break;
                        }

                        case 2: {
                            cont++;
                            System.out.println("______________________________________________________________________________________");
                            System.out.println("Voce foi incoveniente escolha de novo!");
                            if (cont >= 3) {
                                System.out.println("Tu é chato hein, sai do jogo então!");
                                System.out.println("______________________________________________________________________________________");
                                flag = 3; // Encerra o programa após muitas tentativas
                            }
                            break;
                        }

                        default: {
                            System.out.println("Opção inválida. Tente novamente.");
                            System.out.println("______________________________________________________________________________________");
                        }
                    }
                }
            scan.close(); // Fecha o scanner para evitar vazamentos de recursos
        }
    }
*/