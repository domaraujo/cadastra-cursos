package br.com.avaliacao.model.domain;

import br.com.avaliacao.model.CategoriaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CursoDomain {
    private Long id;
    private String descricaoDoAssunto;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private Integer quantidadeAlunoPorTurma;
    private CategoriaModel categoriaModel;


}
