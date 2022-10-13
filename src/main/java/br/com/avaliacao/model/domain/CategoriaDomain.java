package br.com.avaliacao.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.SequenceGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDomain {
    private Long codigo;
    private String descricao;

}
