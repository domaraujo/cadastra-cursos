package br.com.avaliacao.model.domain;

import br.com.avaliacao.model.CategoriaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDomain {
    private Long id;

    public CategoriaDomain(CategoriaModel categoriaModel) {
        this.id = categoriaModel.getId();
    }

    public static List<CategoriaDomain> convert(List<CategoriaModel> categoriaModel) {
        return categoriaModel.stream().map(CategoriaDomain::new).collect(Collectors.toList());
    }
}
