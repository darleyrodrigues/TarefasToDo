package com.darleyrodrigues.apidetarefas.repository;

import com.darleyrodrigues.apidetarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
}
