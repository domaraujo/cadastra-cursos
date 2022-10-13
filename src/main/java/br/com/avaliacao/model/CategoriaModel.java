package br.com.avaliacao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="categoria")
@SequenceGenerator(name = "sequence", sequenceName = "mySequence", initialValue = 4)
public class CategoriaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence" )
    private Long codigo;
    private String descricao;
    @OneToMany
    private List<CursoModel> cursoModel;

}
