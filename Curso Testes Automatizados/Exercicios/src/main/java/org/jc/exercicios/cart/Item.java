
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.cart;

import java.math.BigDecimal;

/**
 *
 * @author jean
 */
public class Item {
    private final int quantidade;
    private final BigDecimal preco;

    public Item(int quantidade, BigDecimal preco) {
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    public BigDecimal total() {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
}