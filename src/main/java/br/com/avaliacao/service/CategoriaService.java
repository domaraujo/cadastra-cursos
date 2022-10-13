package br.com.avaliacao.service;


import br.com.avaliacao.model.CategoriaModel;
import br.com.avaliacao.model.domain.CategoriaDomain;

import java.util.List;

public interface CategoriaService {

    List<CategoriaModel> listarCategorias();

    CategoriaModel salvar(CategoriaDomain categoriaDomain);

    CategoriaModel buscarPorId(Long codigo);

    CategoriaModel atualizarCategoria(CategoriaDomain categoriaDomain);

    void excluirPorId(Long codigo);


}
