package br.com.avaliacao.model.domain;

import br.com.avaliacao.model.CursoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDomain {
    private Long id;

    public CursoDomain(CursoModel cursoModel) {
        this.id = cursoModel.getId();
    }

    public static List<CursoDomain> convert(List<CursoModel> cursoModel) {
        return cursoModel.stream().map(CursoDomain::new).collect(Collectors.toList());

    }
}
