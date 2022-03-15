package br.com.meli.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <T, U> {
    void adicionar(T elemento);
    boolean remover(U parametro);
    void atualizar(U parametro, T elementoAtualizado);
    Optional<T> buscar(U parametro);
    List<T> extrairTodos();
}
