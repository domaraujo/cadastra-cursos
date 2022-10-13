package br.com.avaliacao.service;


import br.com.avaliacao.model.CursoModel;
import br.com.avaliacao.model.domain.CursoDomain;

import java.util.List;

public interface CursoService {

    List<CursoModel>  listarCursos();

    CursoModel buscarPorId(Long id);

    CursoModel cadastrarCurso(CursoDomain cursoDomain);

    CursoModel atualizarCurso(CursoDomain cursoDomain);

    void excluirPorId(Long id);

    void verificarDataInicio( CursoDomain cursoDomain);

    void verificarPeriodoCurso( CursoDomain cursoDomain);


}
