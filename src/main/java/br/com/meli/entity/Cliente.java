package br.com.meli.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString(callSuper = true)
public class Cliente extends BaseEntity {

    private String nome;
    private String sobrenome;

}
