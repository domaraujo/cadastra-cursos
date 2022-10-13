package br.com.avaliacao.service.impl;

import br.com.avaliacao.model.CategoriaModel;
import br.com.avaliacao.model.domain.CategoriaDomain;
import br.com.avaliacao.repository.CategoriaRepository;
import br.com.avaliacao.service.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaModel> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaModel salvar(CategoriaDomain categoriaDomain) {
        return categoriaRepository.save(modelMapper.map(categoriaDomain,CategoriaModel.class));
    }

    @Override
    public CategoriaModel buscarPorId(Long codigo) {
        return categoriaRepository.findById(codigo).orElse(null);
    }

    @Override
    public CategoriaModel atualizarCategoria(CategoriaDomain categoriaDomain) {
        return categoriaRepository.save(modelMapper.map(categoriaDomain, CategoriaModel.class));
    }

    @Override
    public void excluirPorId(Long codigo) {
        categoriaRepository.deleteById(codigo);

    }
}
