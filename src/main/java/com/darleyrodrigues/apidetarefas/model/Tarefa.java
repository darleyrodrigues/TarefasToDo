package com.darleyrodrigues.apidetarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 1, max = 100)
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @Size(min = 1, max = 150)
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "A data da criação não pode ser nula")
    private LocalDate dataCriacao;

    @NotNull(message = "O status de conclusão deve ser informado")
    private Boolean concluida;

    @NotNull(message = "A data de entrega é obrigatória")
    private LocalDate dataEntrega;
}
