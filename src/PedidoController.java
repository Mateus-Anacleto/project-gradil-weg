import java.time.LocalDateTime;
import java.util.*;

public class PedidoController {

    private final List<Pedido> pedidosConfirmados = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private static final double[] ALTURAS = {1.03, 1.53, 2.03};
    private static final String[] CORES = {"Sem pintura", "Branca", "Preta", "Verde"};

    public void executar() {
        while (true) {
            mostrarMenuPrincipal();
        }
    }

    private void mostrarMenuPrincipal() {
        try {
            System.out.println("\n--- Sistema de Pedidos de Cercas Gradil ---");
            System.out.println("1. Fazer novo pedido");
            System.out.println("2. Listar pedidos confirmados");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> processarPedido();
                case 2 -> listarPedidosConfirmados();
                case 3 -> sairDoSistema();
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        }catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número válido.");
            scanner.nextLine();
        }
    }

    private void processarPedido() {
        double comprimento = 0;

        while (true) {
            try {
                System.out.print("Digite o comprimento total da cerca (em metros): ");
                comprimento = scanner.nextDouble();
                if (comprimento <= 0) {
                    throw new IllegalArgumentException("O comprimento deve ser maior que zero!\n");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.\n");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        double altura = selecionarAltura();
        String cor = selecionarCor();

        ResumoPedido resumo = calcularComponentes(comprimento, altura);

        exibirResumoPedido(resumo);

        if (confirmarAcao("\nDeseja confirmar o pedido? (s/n): ")) {
            pedidosConfirmados.add(new Pedido(resumo.getComprimentoVendido(), altura, cor, LocalDateTime.now()));
            System.out.println("Pedido confirmado com sucesso!");
        } else {
            System.out.println("Pedido não confirmado.");
        }
    }

    private void listarPedidosConfirmados() {
        System.out.println("\n****** Pedidos Confirmados ******");
        if (pedidosConfirmados.isEmpty()) {
            System.out.println("Nenhum pedido confirmado até o momento.");
        } else {
            pedidosConfirmados.stream()
                    .sorted(Comparator.comparing(Pedido::getData).reversed())
                    .forEach(System.out::println);
        }
    }

    private void sairDoSistema() {
        System.out.println("Encerrando o sistema. Até mais!");
        System.exit(0);
    }

    private double selecionarAltura() {
        while (true) {
            try {
                System.out.println("\nEscolha a altura da cerca:");
                for (int i = 0; i < ALTURAS.length; i++) {
                    System.out.printf("%d. %.2f metros\n", i + 1, ALTURAS[i]);
                }
                System.out.print("Digite o número correspondente à altura desejada: ");
                int opcao = scanner.nextInt();

                if (opcao >= 1 && opcao <= ALTURAS.length) {
                    return ALTURAS[opcao - 1];
                } else {
                    System.out.println("Opção inválida. Tente novamente!");
                }
            }catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
                scanner.nextLine();
            }
        }
    }

    private String selecionarCor() {
        while (true) {
            try {
                System.out.println("\nEscolha a cor da cerca:");
                for (int i = 0; i < CORES.length; i++) {
                    System.out.printf("%d. %s\n", i + 1, CORES[i]);
                }
                System.out.print("Digite o número correspondente à cor desejada: ");
                int opcao = scanner.nextInt();

                if (opcao >= 1 && opcao <= CORES.length) {
                    return CORES[opcao - 1];
                } else {
                    System.out.println("Opção inválida. Tente novamente!");
                }
            }catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
                scanner.nextLine();
            }
        }
    }

    private ResumoPedido calcularComponentes(double comprimento, double altura) {
        double comprimentoVendido = Math.ceil(comprimento / 2.5) * 2.5;
        int numTelas = (int) (comprimentoVendido / 2.5);
        int numPostes = numTelas + 1;
        int fixadoresPorTela = altura == 1.03 ? 3 : altura == 1.53 ? 4 : 6;
        int numFixadores = numTelas * fixadoresPorTela;
        int numParafusos = numPostes * 4;

        return new ResumoPedido(comprimento, comprimentoVendido, numTelas, numPostes, numFixadores, numParafusos, altura);
    }

    private void exibirResumoPedido(ResumoPedido resumo) {
        System.out.println("\n=============== Resumo do Pedido ===============");
        System.out.println("Comprimento solicitado: " + resumo.getComprimentoSolicitado() + " metros");
        System.out.println("Comprimento vendido: " + resumo.getComprimentoVendido() + " metros");
        if (resumo.getDiferencaComprimento() > 0) {
            System.out.println("Diferença de comprimento: " + resumo.getDiferencaComprimento() + " metros");
        }
        System.out.println("\nQuantidade de Componentes:");
        System.out.println("Telas: " + resumo.getNumTelas());
        System.out.println("Postes: " + resumo.getNumPostes());
        System.out.println("Fixadores: " + resumo.getNumFixadores());
        System.out.println("Parafusos: " + resumo.getNumParafusos());
        System.out.println("================================================");

        System.out.println("\nDesenho Modelo:");
        System.out.println("\t\t\t\t\t\t                              ▲\n" +
                "▓----------------▓----------------▓----------------▓  │\n" +
                "▓----------------▓----------------▓----------------▓  │\n" +
                "▓----------------▓----------------▓----------------▓  \n" +
                "▓----------------▓----------------▓----------------▓  Altura: " + resumo.getAltura() +"m\n"+
                "▓----------------▓----------------▓----------------▓\n" +
                "▓----------------▓----------------▓----------------▓  │\n" +
                "▓----------------▓----------------▓----------------▓  │\n" +
                "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀  ▼\n" +
                "◀─────────────── Comprimento: "+ resumo.getComprimentoVendido()+"m\t────────────────▶");

    }

    private boolean confirmarAcao(String mensagem) {
        System.out.print(mensagem);
        String resposta = scanner.next();
        return resposta.equalsIgnoreCase("s");
    }
}