package br.com.meli.service;

import br.com.meli.entity.Cliente;
import br.com.meli.entity.Fatura;
import br.com.meli.repository.Repository;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Optional;

@Data
public class SupermercadoService {
    private final Repository<Cliente, Long> clienteRepository;
    private final Repository<Fatura, Long> faturaRepository;

    public void registrarCliente(Cliente cliente) {
        clienteRepository.adicionar(cliente);
    }

    public void imprimirClientes() {
        System.out.println("|--------- Imprimindo clientes cadastrados ---------|");
        System.out.println("Imprimindo lista de clientes cadastrados");
        clienteRepository.extrairTodos().forEach(System.out::println);
        System.out.println("|---------------------------------------------------|\n");
    }

    public void excluirCliente(Long id) {
        System.out.println("|--------- Excluindo cliente ---------|");
        boolean isClienteRemovido = clienteRepository.remover(id);

        if (isClienteRemovido) {
            System.out.println("Cliente removido da base de dados com sucesso");
        } else {
            System.out.println("Cliente não encontrado");
        }

        System.out.println("|--------------------------------------|\n");
    }

    public void imprimirCliente(Long id) {
        System.out.println("|--------- Buscando cliente ---------|");
        Optional<Cliente> clienteOptional = clienteRepository.buscar(id);

        if (clienteOptional.isEmpty()) {
            System.out.println("Cliente não encontrado");
            return;
        }
        System.out.println("Cliente encontrado. Dados:");
        Cliente cliente = clienteOptional.get();
        System.out.println(cliente.toString());
        System.out.println("|-------------------------------------|\n");
    }

    public void registrarFatura(Fatura fatura) {
        System.out.println("|--------- Registrando fatura ---------|");
        Cliente clienteFatura = fatura.getCliente();

        Optional<Cliente> clienteOptional = clienteRepository.buscar(clienteFatura.getId());

        if (clienteOptional.isEmpty()) {
            registrarCliente(clienteFatura);
            fatura.setCliente(clienteFatura);
            System.out.println("Cliente não encontrado na base de dados, novo registro criado.");
        }

        BigDecimal valorTotal = calcularTotalFatura(fatura);
        fatura.setTotal(valorTotal);
        faturaRepository.adicionar(fatura);

        System.out.println("|--------------------------------------|\n");
    }

    public void imprimirFaturas() {
        System.out.println("|--------- Imprimindo faturas ---------|");
        faturaRepository.extrairTodos().forEach(System.out::println);
        System.out.println("|--------------------------------------|\n");
    }

    private BigDecimal calcularTotalFatura(Fatura fatura) {
        return fatura.getItens().stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
