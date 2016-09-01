/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jean
 */
public class Pedido {

    private final List<Item> itens;

    public Pedido(List<Item> itens) {
        this.itens = new ArrayList<>(itens);
    }

    public Pedido() {
        this(new ArrayList<>());
    }

    public boolean inclui(Item item) {
        boolean incluido;
        if (item == null) {
            incluido = false;
        } else {
            incluido = this.itens.add(item);
        }
        return incluido;
    }

    public BigDecimal total(Desconto desconto) {
        final BigDecimal bruto = this.itens.stream()
            .map(Item::total)
            .reduce(BigDecimal::add)
            .get();
        final BigDecimal abatimento = desconto.calcula(bruto);
        return bruto.subtract(abatimento);
    }
}