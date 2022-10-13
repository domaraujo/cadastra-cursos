package br.com.avaliacao.controller;

import br.com.avaliacao.model.domain.CategoriaDomain;
import br.com.avaliacao.model.domain.CursoDomain;
import br.com.avaliacao.service.CategoriaService;
import br.com.avaliacao.service.CursoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    public static final String ID = "/{id}";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<CategoriaDomain>> listarCursos() {
        return ResponseEntity.ok().body(categoriaService.listarCategorias().stream().map(x -> modelMapper
                .map(x, CategoriaDomain.class)).collect(Collectors.toList()));
    }

    @GetMapping(value = ID)
    public ResponseEntity<CategoriaDomain> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(modelMapper.map(categoriaService.buscarPorId(id),CategoriaDomain.class));
    }

    @PostMapping
    public ResponseEntity<CategoriaDomain> cadastrarCurso(@RequestBody CategoriaDomain categoriaDomain) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(categoriaService.salvar(categoriaDomain).getCodigo()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<CategoriaDomain> atualizarCurso(@PathVariable Long id, @RequestBody CategoriaDomain categoriaDomain) {
        categoriaDomain.setCodigo(id);
        return ResponseEntity.ok().body(modelMapper.map(categoriaService.atualizarCategoria(categoriaDomain), CategoriaDomain.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<CategoriaDomain> deletarCurso(@PathVariable Long id) {
        categoriaService.excluirPorId(id);
        return ResponseEntity.noContent().build();

    }
}









