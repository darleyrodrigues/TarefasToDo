package com.darleyrodrigues.apidetarefas.service;

import com.darleyrodrigues.apidetarefas.model.Tarefa;
import com.darleyrodrigues.apidetarefas.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarTarefa() {
        // Arrange
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Teste de tarefa JUnit com mockito");
        tarefa.setDescricao("Descrição para o teste");
        tarefa.setDataCriacao(LocalDate.now());
        tarefa.setConcluida(false);

        // Configura o mock do repositório
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);

        // Act
        Tarefa resultado = tarefaService.salvarTarefa(tarefa);

        // Assert
        assertNotNull(resultado);
        assertEquals("Teste de tarefa JUnit com mockito", resultado.getTitulo());
    }

    @Test
    void deveBuscarTarefaPorId() {
        // Arrange
        UUID id = UUID.randomUUID();
        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setTitulo("Buscar tarefa por ID");
        tarefa.setDescricao("Descrição da tarefa");
        tarefa.setDataCriacao(LocalDate.now());
        tarefa.setConcluida(false);

        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));

        // Act
        Tarefa resultado = tarefaService.buscarPorId(id);

        // Assert
        assertEquals(id, resultado.getId());
        assertEquals("Buscar tarefa por ID", resultado.getTitulo());
    }

    @Test
    void deveDeletar() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        tarefaService.deletar(id);

        // Assert
        verify(tarefaRepository).deleteById(id);
    }

    @Test
    void deveListarTodasAsTarefas() {
        // Arrange
        Tarefa t1 = new Tarefa();
        t1.setId(UUID.randomUUID());
        t1.setTitulo("Tarefa 1");
        t1.setDescricao("Descrição 1");
        t1.setDataCriacao(LocalDate.now());
        t1.setConcluida(false);

        Tarefa t2 = new Tarefa();
        t2.setId(UUID.randomUUID());
        t2.setTitulo("Tarefa 2");
        t2.setDescricao("Descrição 2");
        t2.setDataCriacao(LocalDate.now());
        t2.setConcluida(true);

        List<Tarefa> tarefas = List.of(t1, t2);

        when(tarefaRepository.findAll()).thenReturn(tarefas);

        // Act
        List<Tarefa> resultado = tarefaService.listarTodas();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("Tarefa 1", resultado.get(0).getTitulo());
        assertEquals("Tarefa 2", resultado.get(1).getTitulo());
    }

    @Test
    void deveBuscarPorStatus() {
        // Arrange
        Tarefa t1 = new Tarefa();
        t1.setId(UUID.randomUUID());
        t1.setTitulo("Concluída 1");
        t1.setDescricao("Tarefa concluída");
        t1.setDataCriacao(LocalDate.now());
        t1.setConcluida(true);

        Tarefa t2 = new Tarefa();
        t2.setId(UUID.randomUUID());
        t2.setTitulo("Concluída 2");
        t2.setDescricao("Outra tarefa concluída");
        t2.setDataCriacao(LocalDate.now());
        t2.setConcluida(true);

        List<Tarefa> concluidas = List.of(t1, t2);

        when(tarefaRepository.findByConcluida(true)).thenReturn(concluidas);

        // Act
        List<Tarefa> resultado = tarefaService.buscarPorStatus(true);

        // Assert
        assertEquals(2, resultado.size());
        assertTrue(resultado.get(0).getConcluida());
        assertTrue(resultado.get(1).getConcluida());
    }

    @Test
    void deveOrdenarTarefasPorDataEntrega() {
        // Arrange
        Tarefa t1 = new Tarefa();
        t1.setId(UUID.randomUUID());
        t1.setTitulo("Antiga");
        t1.setDescricao("desc");
        t1.setDataCriacao(LocalDate.now());
        t1.setConcluida(false);
        t1.setDataEntrega(LocalDate.of(2024, 1, 1));

        Tarefa t2 = new Tarefa();
        t2.setId(UUID.randomUUID());
        t2.setTitulo("Nova");
        t2.setDescricao("desc");
        t2.setDataCriacao(LocalDate.now());
        t2.setConcluida(false);
        t2.setDataEntrega(LocalDate.of(2024, 12, 31));

        List<Tarefa> ordenadas = List.of(t1, t2);

        when(tarefaRepository.findAllByOrderByDataEntregaAsc()).thenReturn(ordenadas);

        // Act
        List<Tarefa> resultado = tarefaService.ordenarPorDataEntrega();

        // Assert
        assertEquals("Antiga", resultado.get(0).getTitulo());
        assertEquals("Nova", resultado.get(1).getTitulo());
    }
}
