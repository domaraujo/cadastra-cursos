package br.com.avaliacao.service.impl;

import br.com.avaliacao.model.CategoriaModel;
import br.com.avaliacao.model.CursoModel;
import br.com.avaliacao.model.domain.CursoDomain;
import br.com.avaliacao.repository.CursoRepository;
import br.com.avaliacao.service.exception.DataInicioNaoPermitidaException;
import br.com.avaliacao.service.exception.IllegalArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CursoServiceImplTest {

    public static final long ID = 1L;
    public static final String DESCRICAO_DO_ASSUNTO = "Java";
    public static final int QUANTIDADE_ALUNO_POR_TURMA = 25;
    public static final String CURSO_NAO_CADASTRADO = "Curso não cadastrado";
    public static final String DATA_INICIO = "2023-11-01";
    private static final String DATA_TERMINO = "2023-11-04";
    private static final String DATA_INICIO_MENOR = "2021-11-01";


    @InjectMocks
    private  CursoServiceImpl cursoService;

    @Mock
    private CursoRepository cursoRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CursoModel cursoModel;
    @Mock
    private CursoDomain cursoDomain;

    private Optional<CursoModel> optionalCursoModel;





    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCurso();
    }

    @Test
    void quandoListarCursosEntaoRetornarTodosCursosCadastrados() {
        when(cursoRepository.findAll()).thenReturn(List.of(cursoModel));

        List<CursoModel> response = cursoService.listarCursos();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(CursoModel.class, response.get(0).getClass());


    }

    @Test
    void quandoBuscarPorIdEntaoRetornarUmCurso() {
        when(cursoRepository.findById(anyLong())).thenReturn(optionalCursoModel);

        CursoModel response = cursoService.buscarPorId(ID);

        assertNotNull(response);
        assertEquals(CursoModel.class, response.getClass());

    }

    @Test
    void quandoBuscarPorIdInexistenteEntaoRetornarObjectNotFound() {when(cursoRepository.findById(Mockito
            .anyLong())).thenThrow(
                new IllegalArgumentException(CURSO_NAO_CADASTRADO));
        try {
            cursoService.buscarPorId(ID);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals(CURSO_NAO_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void quandoCadastrarCursoEntaoRetornarSucesso() {
        when(cursoRepository.save(any())).thenReturn(cursoModel);

        CursoModel response = cursoService.cadastrarCurso(cursoDomain);

        assertNotNull(response);
        assertEquals(CursoModel.class, response.getClass());
        assertEquals(DESCRICAO_DO_ASSUNTO, response.getDescricaoDoAssunto());
        assertEquals(LocalDate.parse(DATA_INICIO), response.getDataInicio());
        assertEquals(LocalDate.parse(DATA_TERMINO), response.getDataTermino());
    }

    @Test
    void quandoCadastrarCursoComDataInicioMenorQueAtualEntaoRetornarDataInicioNaoPermitidaException() {
        when(cursoRepository.save(any())).thenThrow(
                new DataInicioNaoPermitidaException("Data de início menor que a data atual."));
        try {
            cursoService.cadastrarCurso(cursoDomain);
            cursoService.verificarDataInicio(cursoDomain);
        } catch (Exception ex) {
            assertEquals(DataInicioNaoPermitidaException.class, ex.getClass());
            assertEquals("Data de início menor que a data atual.", ex.getMessage());
        }

    }

    @Test
    void quandoAtualizarCursoEntaoRetornarSucesso() {
        when(cursoRepository.save(any())).thenReturn(cursoModel);

        CursoModel response = cursoService.atualizarCurso(cursoDomain);

        assertNotNull(response);
        assertEquals(CursoModel.class, response.getClass());
        assertEquals(DESCRICAO_DO_ASSUNTO, response.getDescricaoDoAssunto());
        assertEquals(LocalDate.parse(DATA_INICIO), response.getDataInicio());
        assertEquals(LocalDate.parse(DATA_TERMINO), response.getDataTermino());
    }

    @Test
    void quandoAtualizarCursoComDataInicioMenorQueAtualEntaoRetornarDataInicioNaoPermitidaException() {
        when(cursoRepository.save(any())).thenThrow(
                new DataInicioNaoPermitidaException("Data de início menor que a data atual."));
        try {
            cursoService.atualizarCurso(cursoDomain);
            cursoService.verificarDataInicio(cursoDomain);
        } catch (Exception ex) {
            assertEquals(DataInicioNaoPermitidaException.class, ex.getClass());
            assertEquals("Data de início menor que a data atual.", ex.getMessage());
        }

    }

    @Test
    void quandoExcluirPorIdEntaoRetornarSucesso() {
        when(cursoRepository.findById(anyLong())).thenReturn(optionalCursoModel);
        doNothing().when(cursoRepository).deleteById(anyLong());
        cursoService.excluirPorId(ID);
        verify(cursoRepository, times(1)).deleteById(anyLong());

    }

    @Test
    void quandoVerificarDataInicioMenorEntaoDataInicioNaoPermitidaException() {
        when(cursoRepository.save(any())).thenThrow(
                new DataInicioNaoPermitidaException("Data de início menor que a data atual."));
        try {
            cursoService.verificarDataInicio(cursoDomain);
            cursoService.atualizarCurso(cursoDomain);
        } catch (Exception ex) {
            assertTrue(LocalDate.parse(DATA_INICIO_MENOR).isBefore(LocalDate.now()));
            assertEquals("Data de início menor que a data atual.", ex.getMessage());
        }
    }



    private void startCurso() {

        cursoModel = new CursoModel(ID, DESCRICAO_DO_ASSUNTO,LocalDate.parse(DATA_INICIO) , LocalDate.parse(DATA_TERMINO), QUANTIDADE_ALUNO_POR_TURMA, new CategoriaModel(ID, "Programação"));
        cursoDomain = new CursoDomain(ID, DESCRICAO_DO_ASSUNTO,LocalDate.parse(DATA_INICIO) , LocalDate.parse(DATA_TERMINO), QUANTIDADE_ALUNO_POR_TURMA, new CategoriaModel(ID, "Programação"));
        optionalCursoModel = Optional.of(new CursoModel(ID, DESCRICAO_DO_ASSUNTO,LocalDate.parse(DATA_INICIO) , LocalDate.parse(DATA_TERMINO), QUANTIDADE_ALUNO_POR_TURMA, new CategoriaModel(ID, "Programação")));


    }
}