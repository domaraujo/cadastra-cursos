package br.com.avaliacao.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
