package br.com.avaliacao.service.impl;

import br.com.avaliacao.model.CursoModel;
import br.com.avaliacao.model.domain.CursoDomain;
import br.com.avaliacao.repository.CursoRepository;
import br.com.avaliacao.service.CursoService;
import br.com.avaliacao.service.exception.DataInicioNaoPermitidaException;
import br.com.avaliacao.service.exception.IllegalArgumentException;
import br.com.avaliacao.service.exception.PeriodoNaoPermitidoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<Integer> listaPeriodosCadastrados = new ArrayList<>();
    public List<Integer> listaCadastrados = new ArrayList<>();

    public List<CursoModel> listarCursos() {
        return cursoRepository.findAll();
    }


    @Override
    public CursoModel buscarPorId(Long id) {
        Optional<CursoModel> curso = cursoRepository.findById(id);
        return curso.orElseThrow(()-> new IllegalArgumentException("Curso não cadastrado"));
    }

    @Override
    public CursoModel cadastrarCurso(CursoDomain cursoDomain) {
        verificarDataInicio(cursoDomain);
        verificarPeriodoCurso(cursoDomain);
        return cursoRepository.save(modelMapper.map(cursoDomain, CursoModel.class));
    }

    @Override
    public CursoModel atualizarCurso(CursoDomain cursoDomain) {
        verificarDataInicio(cursoDomain);
        verificarPeriodoCurso(cursoDomain);
        return cursoRepository.save(modelMapper.map(cursoDomain, CursoModel.class));
    }

    @Override
    public void excluirPorId(Long id) {
        cursoRepository.deleteById(id);
    }


    @Override
    public void verificarDataInicio( CursoDomain cursoDomain) {
        if(cursoDomain.getDataInicio().isBefore(LocalDate.now()))
        throw new DataInicioNaoPermitidaException("Data de início menor que a data atual.");
    }

    @Override
    public void verificarPeriodoCurso( CursoDomain cursoDomain) {
        int dataInicio = Math.toIntExact(cursoDomain.getDataInicio().toEpochDay());
        int dataTermino = Math.toIntExact(cursoDomain.getDataTermino().toEpochDay());
        List<Integer> periodoCurso = IntStream.range(dataInicio, dataTermino + 1).boxed()
                .collect(Collectors.toList());

        List<CursoDomain>  listaPeriodosCadastrados = (cursoRepository.buscarPeriodo()).stream()
                .map(x -> modelMapper.map(x, CursoDomain.class)).collect(Collectors.toList());

        for (CursoDomain list: listaPeriodosCadastrados) {
            int dataInicioDB = Math.toIntExact(list.getDataInicio().toEpochDay());
            int dataTerminoDB = Math.toIntExact(list.getDataTermino().toEpochDay());
            listaCadastrados.addAll (IntStream.range(dataInicioDB, dataTerminoDB + 1).boxed().collect(Collectors.toList()));
        }

        for (Integer periodCurso : periodoCurso) {
            for (Integer compara : listaCadastrados) {
                if(periodCurso.equals(compara))
                    throw new PeriodoNaoPermitidoException("Existe(m) curso(s) planejado(s) dentro do período informado.");
            }
        }

    }

}






