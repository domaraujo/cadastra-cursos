package br.com.avaliacao.service.impl;

import br.com.avaliacao.model.CategoriaModel;
import br.com.avaliacao.model.domain.CategoriaDomain;
import br.com.avaliacao.repository.CategoriaRepository;
import br.com.avaliacao.service.exception.IllegalArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@SpringBootTest
class CategoriaServiceImplTest {

    public static final Long CODIGO = 1L;
    private static final String CATEGORIA_NAO_CADASTRADA = "Categoria não cadastrada";
    public static final String DESCRICAO = "Programação";


    @InjectMocks
    CategoriaServiceImpl categoriaService;

    @Mock
    CategoriaModel categoriaModel;

    @Mock
    CategoriaDomain categoriaDomain;

    @Mock
    ModelMapper modelMapper;

    @Mock
    CategoriaRepository categoriaRepository;


    Optional<CategoriaModel> optionalCategoriaModel;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCategoria();
    }


    @Test
    void quandoListarCategoriasEntaoRetornarTodasCategoriasCadastradas() {
        when(categoriaRepository.findAll()).thenReturn(List.of(categoriaModel));

        List<CategoriaModel> response = categoriaService.listarCategorias();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(CategoriaModel.class, response.get(0).getClass());
    }

    @Test
    void quandoBuscarPorIdEntaoRetornarUmaCategoria() {
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(categoriaModel));

        CategoriaModel response = categoriaService.buscarPorId(CODIGO);

        assertNotNull(response);
        assertEquals(CategoriaModel.class, response.getClass());
    }

    @Test
    void quandoBuscarPorIdInexistenteEntaoRetornarObjectNotFound() {when(categoriaRepository.findById(Mockito
            .anyLong())).thenThrow(
            new IllegalArgumentException(CATEGORIA_NAO_CADASTRADA));
        try {
            categoriaService.buscarPorId(CODIGO);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals(CATEGORIA_NAO_CADASTRADA, ex.getMessage());
        }
    }

    @Test
    void quandoCadastrarCategoriaEntaoRetornarSucesso() {
        when(categoriaRepository.save(any())).thenReturn(categoriaModel);

        CategoriaModel response = categoriaService.salvar(categoriaDomain);

        assertNotNull(response);
        assertEquals(CategoriaModel.class, response.getClass());
        assertEquals(CODIGO, response.getCodigo());
    }


    @Test
    void quandoAtualizarCategoriaEntaoRetornarSucesso() {
        when(categoriaRepository.save(any())).thenReturn(categoriaModel);

        CategoriaModel response = categoriaService.atualizarCategoria(categoriaDomain);

        assertNotNull(response);
        assertEquals(CategoriaModel.class, response.getClass());
        assertEquals(DESCRICAO, response.getDescricao());
    }

    @Test
    void quandoExcluirPorIdEntaoRetornarSucesso() {
        when(categoriaRepository.findById(anyLong())).thenReturn(optionalCategoriaModel);
        doNothing().when(categoriaRepository).deleteById(anyLong());
        categoriaService.excluirPorId(CODIGO);
        verify(categoriaRepository, times(1)).deleteById(anyLong());
    }


    public void startCategoria() {
        categoriaDomain = new CategoriaDomain(CODIGO, DESCRICAO);
        categoriaModel = new CategoriaModel(CODIGO, DESCRICAO);
    }
}