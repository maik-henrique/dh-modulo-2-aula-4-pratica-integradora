package br.com.meli.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Item extends BaseEntity {

    private String codigo;
    private String nome;
    private int quantidade;
    private BigDecimal precoUnitario;
}
