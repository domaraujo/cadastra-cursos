package br.com.avaliacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categoria")
@SequenceGenerator(name = "sequence", sequenceName = "mySequence", initialValue = 4)
public class CategoriaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence" )
    private Long codigo;
    private String descricao;


}
