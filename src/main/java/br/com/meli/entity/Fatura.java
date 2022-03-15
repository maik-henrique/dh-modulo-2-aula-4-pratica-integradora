package br.com.meli.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class Fatura extends BaseEntity {

    private Cliente cliente;
    private List<Item> itens;
    private BigDecimal total;

}
