package br.com.meli;

import br.com.meli.entity.Cliente;
import br.com.meli.entity.Fatura;
import br.com.meli.entity.Item;
import br.com.meli.enums.OperacoesDisponiveis;
import br.com.meli.repository.InMemoryRepository;
import br.com.meli.service.SupermercadoService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static List<Item> getItensConsole() {
        List<Item> itens = new LinkedList<>();

        while (true) {
            System.out.println("Digite o nome do produto:");
            String nome = SCANNER.next();

            System.out.println("Digite o código do produto:");
            String codigo = SCANNER.next();

            System.out.println("Quantidade comprada:");
            int quantidade = SCANNER.nextInt();

            System.out.println("Digite o preço unitário:");
            BigDecimal precoUnitario = SCANNER.nextBigDecimal();

            Item item = Item.builder()
                    .nome(nome)
                    .quantidade(quantidade)
                    .precoUnitario(precoUnitario)
                    .codigo(codigo)
                    .build();

            itens.add(item);

            System.out.println("Item adicionado com sucesso");
            System.out.println("Digite 0 para sair ou qualquer outro número para cadastrar mais produtos");

            int opcao = SCANNER.nextInt();

            if (opcao == 0) {
                break;
            }
        }

        return itens;
    }

    public static Cliente getClienteConsole() {
        System.out.println("Digite o nome do cliente:");
        String nome = SCANNER.next();
        System.out.println("Digite o sobrenome do cliente:");
        String sobrenome = SCANNER.next();

        return Cliente.builder()
                .nome(nome)
                .sobrenome(sobrenome).build();
    }

    public static Fatura getFaturaConsole() {
        Cliente cliente = getOrCreateClienteConsole();

        System.out.println("Adicionar itens à fatura:");
        List<Item> itens = getItensConsole();

        return Fatura.builder().cliente(cliente)
                .itens(itens).build();
    }

    private static Cliente getOrCreateClienteConsole() {
        Cliente cliente = new Cliente();

        System.out.println("O cliente já está cadastrado?");
        System.out.println("1 - Sim\n2- Não");

        int opcao = SCANNER.nextInt();

        if (opcao == 1) {
            System.out.println("Digite o id do cliente:");
            long idCliente = SCANNER.nextLong();
            cliente.setId(idCliente);
        } else {
            cliente = getClienteConsole();

        }
        return cliente;
    }

    private static boolean direcionarOpcaoUsuario(SupermercadoService supermercadoService) {
        int opcaoEscolhida = SCANNER.nextInt();

        switch (opcaoEscolhida) {
            case 1:
                Cliente cliente = getClienteConsole();
                supermercadoService.registrarCliente(cliente);
                break;
            case 2:
                supermercadoService.imprimirClientes();
                break;
            case 3:
                System.out.println("Digite o ID do cliente que deseja excluir:");
                supermercadoService.excluirCliente(SCANNER.nextLong());
                break;
            case 4:
                System.out.println("Digite o ID do cliente que deseja buscar");
                supermercadoService.imprimirCliente(SCANNER.nextLong());
                break;
            case 5:
                Fatura fatura = getFaturaConsole();
                supermercadoService.registrarFatura(fatura);
                break;
            case 6:
                supermercadoService.imprimirFaturas();
                break;
            case 7:
                System.out.println("Finalizando execução");
                return true;
            default:
                System.out.println("Opção inválida tente novamente!");
                break;
        }
        return false;
    }

    private static void executarMenuInterativo(SupermercadoService supermercadoService) {
        while (true) {
            System.out.println("Digite o número da operação que deseja realizar:");
            Arrays.stream(OperacoesDisponiveis.values())
                    .forEach(operacao -> System.out.printf("%d - %s", operacao.getOpcao(), operacao.getLabel()));

            if (direcionarOpcaoUsuario(supermercadoService)) return;
        }
    }

    public static void main(String[] args) {
        InMemoryRepository<Cliente, Long> clienteRepository = new InMemoryRepository<>();
        InMemoryRepository<Fatura, Long> faturaRepository = new InMemoryRepository<>();
        SupermercadoService supermercadoService = new SupermercadoService(clienteRepository, faturaRepository);

        try {
            executarMenuInterativo(supermercadoService);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SCANNER.close();
        }
    }

}
