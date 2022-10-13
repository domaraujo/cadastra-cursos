package br.com.avaliacao.service;


import br.com.avaliacao.model.CursoModel;
import br.com.avaliacao.model.domain.CursoDomain;

import java.util.List;

public interface CursoService {

    List<CursoModel>  listarCursos();

    CursoModel salvar(CursoDomain cursoDomain);

    CursoModel buscarPorId(Long id);

    CursoModel atualizarCurso(CursoDomain cursoDomain);

    void excluirPorId(Long id);

}
