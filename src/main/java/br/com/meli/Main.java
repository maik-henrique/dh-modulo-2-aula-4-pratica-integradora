package br.com.meli;

import br.com.meli.entity.BaseEntity;
import br.com.meli.repository.InMemoryRepository;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<BaseEntity, Long> in = new InMemoryRepository<>();
        in.buscar(2l);
    }
}
