package br.com.avaliacao.model.domain;

import br.com.avaliacao.model.CategoriaModel;
import br.com.avaliacao.model.CursoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDomain {
    private Long codigo;
    private String descricao;

}
