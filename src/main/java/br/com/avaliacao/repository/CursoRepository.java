package br.com.avaliacao.repository;

import br.com.avaliacao.model.CursoModel;
import br.com.avaliacao.model.domain.CursoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Long> {

    @Query(value = "select c.id, c.categoria_model_codigo, c.quantidade_aluno_por_turma, c.descricao_assunto, c.data_inicio, c.data_termino from curso c", nativeQuery = true)
    public List<CursoModel> buscarPeriodo();

}
