package br.com.avaliacao.controller;

import br.com.avaliacao.model.domain.CursoDomain;
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
@RequestMapping("/curso")
public class CursoController {

    public static final String ID = "/{id}";

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private CursoService cursoService;


    @GetMapping
    public ResponseEntity<List<CursoDomain>> listarCursos() {
        return ResponseEntity.ok().body(cursoService.listarCursos().stream().map(x -> modelMapper
                .map(x, CursoDomain.class)).collect(Collectors.toList()));
    }

    @GetMapping(value = ID)
    public ResponseEntity<CursoDomain> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(modelMapper.map(cursoService.buscarPorId(id),CursoDomain.class));
    }

    @PostMapping
    public ResponseEntity<CursoDomain> cadastrarCurso(@RequestBody CursoDomain cursoDomain) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(cursoService.salvar(cursoDomain).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<CursoDomain> atualizarCurso(@PathVariable Long id, @RequestBody CursoDomain cursoDomain) {
        cursoDomain.setId(id);
        return ResponseEntity.ok().body(modelMapper.map(cursoService.atualizarCurso(cursoDomain), CursoDomain.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<CursoDomain> deletarCurso(@PathVariable Long id) {
        cursoService.excluirPorId(id);
        return ResponseEntity.noContent().build();

    }
}
















