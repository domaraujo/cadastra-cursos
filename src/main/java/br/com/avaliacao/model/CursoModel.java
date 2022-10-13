package br.com.avaliacao.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name="curso")
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "descricao_assunto")
    private String descricaoDoAssunto;
    @NotNull
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;
    @NotNull
    @Column(name = "data_termino", nullable = false)
    private LocalDate dataTermino;
    @Column(name = "quantidade_aluno_por_turma")
    private Integer quantidadeAlunoPorTurma;
    @NotNull
    @ManyToOne()
    private CategoriaModel categoria = new CategoriaModel();
}
