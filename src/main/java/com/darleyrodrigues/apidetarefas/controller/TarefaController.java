package com.darleyrodrigues.apidetarefas.controller;

import com.darleyrodrigues.apidetarefas.model.Tarefa;
import com.darleyrodrigues.apidetarefas.service.TarefaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public Tarefa salvar(@RequestBody Tarefa tarefa){
        return tarefaService.salvarTarefa(tarefa);
    }

    @GetMapping("{id}")
    public Tarefa buscarPorId(@PathVariable ("id") UUID id){
        return tarefaService.buscarPorId(id);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable ("id") UUID id){
        tarefaService.deletar(id);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable ("id") UUID id,
                          @RequestBody Tarefa tarefa){
        tarefaService.atualizar(id, tarefa);
    }

    @GetMapping
    public List<Tarefa> listarTodas(){
        return tarefaService.listarTodas();
    }
}
