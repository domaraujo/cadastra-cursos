package br.com.avaliacao.model.domain;

import br.com.avaliacao.model.CursoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDomain {
    private Long codigo;
    private String descricao;
    private List<CursoModel> cursoModel;

}
