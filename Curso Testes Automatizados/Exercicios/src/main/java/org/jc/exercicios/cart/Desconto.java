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
public interface Desconto {
    BigDecimal calcula(BigDecimal valor);
}