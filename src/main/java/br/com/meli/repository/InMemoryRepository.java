package br.com.meli.repository;

import br.com.meli.entity.BaseEntity;

import java.util.*;

public class InMemoryRepository<T extends BaseEntity, U> implements Repository<T, Long> {
    private final Map<Long, T> elementos;
    private Long availablePrimaryKey;

    public InMemoryRepository() {
        elementos = new HashMap<>();
        availablePrimaryKey = 0L;
    }

    @Override
    public void adicionar(T elemento) {
        elemento.setId(availablePrimaryKey);
        elementos.put(availablePrimaryKey, elemento);
        availablePrimaryKey++;
    }

    @Override
    public boolean remover(Long id) {
        T elementoRemovido = elementos.remove(id);
        return elementoRemovido != null;
    }

    @Override
    public void atualizar(Long id, T elementoAtualizado) {
        Optional<T> optionalElemento = buscar(id);

        if (optionalElemento.isEmpty()) {
            System.out.println("Elemento não encontrado");
            return;
        }

        elementos.put(id, elementoAtualizado);
    }

    @Override
    public Optional<T> buscar(Long id) {
        return Optional.ofNullable(elementos.get(id));
    }

    @Override
    public List<T> extrairTodos() {
        return new ArrayList<>(elementos.values());
    }
}
