package br.com.meli;

import br.com.meli.entity.Cliente;
import br.com.meli.repository.InMemoryRepository;
import br.com.meli.service.Supermercado;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Cliente, Long> repository = new InMemoryRepository<>();
        Supermercado supermercado = new Supermercado(repository);

        Cliente joao = Cliente.builder()
                .nome("João")
                .sobrenome("Augusto").build();

        supermercado.registrarCliente(joao);
        supermercado.imprimirClientes();
        supermercado.imprimirCliente(0L);
        supermercado.excluirCliente(0L);



    }
}
