package br.com.avaliacao.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="curso")
public class CursoModel implements Serializable {
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
    private CategoriaModel categoriaModel;


}
