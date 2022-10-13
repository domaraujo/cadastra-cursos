package br.com.avaliacao.service.impl;

import br.com.avaliacao.model.CursoModel;
import br.com.avaliacao.model.domain.CursoDomain;
import br.com.avaliacao.repository.CursoRepository;
import br.com.avaliacao.service.CursoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<CursoModel> listarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public CursoModel buscarPorId(Long id) {
        Optional<CursoModel> curso = cursoRepository.findById(id);
        return curso.orElse(null);
    }

    @Override
    public CursoModel salvar(CursoDomain cursoDomain) {
        return cursoRepository.save(modelMapper.map(cursoDomain, CursoModel.class));
    }

    @Override
    public CursoModel atualizarCurso(CursoDomain cursoDomain) {
        return cursoRepository.save(modelMapper.map(cursoDomain, CursoModel.class));
    }

    @Override
    public void excluirPorId(Long id) {
        cursoRepository.deleteById(id);
    }


}
