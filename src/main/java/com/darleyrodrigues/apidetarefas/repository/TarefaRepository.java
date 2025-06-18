package com.darleyrodrigues.apidetarefas.repository;

import com.darleyrodrigues.apidetarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
    List<Tarefa> findByConcluida(Boolean concluida);
    List<Tarefa> findAllByOrderByDataEntregaAsc();
}
