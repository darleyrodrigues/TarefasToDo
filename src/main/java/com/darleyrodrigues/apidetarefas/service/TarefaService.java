package com.darleyrodrigues.apidetarefas.service;

import com.darleyrodrigues.apidetarefas.model.Tarefa;
import com.darleyrodrigues.apidetarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TarefaService {

    @Autowired
    public TarefaRepository tarefaRepository;

    public Tarefa salvarTarefa(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    public Tarefa buscarPorId(UUID id){
        return tarefaRepository.findById(id).orElse(null);
    }

    public void deletar(UUID id){
        tarefaRepository.deleteById(id);
    }

    public void atualizar(UUID id, Tarefa tarefa){
        tarefa.setId(id);
        tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodas(){
        return tarefaRepository.findAll();
    }
}
