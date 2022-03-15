package br.com.meli.service;

import br.com.meli.entity.Cliente;
import br.com.meli.repository.Repository;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class Supermercado {
    private final Repository<Cliente, Long> clienteRepository;

    public void registrarCliente(Cliente cliente) {
        clienteRepository.adicionar(cliente);
    }

    public void imprimirClientes() {
        List<Cliente> clientes = clienteRepository.extrairTodos();
        System.out.println("Imprimindo lista de clientes cadastrados");
        clientes.forEach(System.out::println);
    }

    public void excluirCliente(Long id) {
        boolean isClienteRemovido = clienteRepository.remover(id);

        if (isClienteRemovido) {
            System.out.println("Cliente removido da base de dados com sucesso");
            return;
        }

        System.out.println("Cliente não encontrado");
    }

    public void imprimirCliente(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.buscar(id);

        if (clienteOptional.isEmpty()) {
            System.out.println("Cliente não encontrado");
            return;
        }
        System.out.println("Cliente encontrado. Dados:");
        Cliente cliente = clienteOptional.get();
        System.out.println(cliente.toString());
    }



}
