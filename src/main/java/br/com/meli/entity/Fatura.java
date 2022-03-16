package br.com.meli.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@ToString(callSuper = true)
public class Fatura extends BaseEntity {

    private Cliente cliente;
    private List<Item> itens;
    private BigDecimal total;

}
