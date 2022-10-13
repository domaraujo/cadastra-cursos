package br.com.avaliacao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="categoria")
@SequenceGenerator(name = "sequence", sequenceName = "mySequence", initialValue = 4)
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence" )
    private Long codigo;
    private String descricao;

}
