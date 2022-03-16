package br.com.meli.entity;

import lombok.*;

@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends BaseEntity {

    private String nome;
    private String sobrenome;

}
