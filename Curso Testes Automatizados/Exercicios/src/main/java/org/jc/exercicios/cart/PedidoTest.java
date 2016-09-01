package org.jc.exercicios.cart;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class PedidoTest {

	@Test
	public void calculaTotalSemDesconto() {
		System.out.println("calcula Total Sem Desconto");
		Desconto desconto = new Desconto() {
			@Override
			public BigDecimmal calcula(BigDecimal valor) {
				return BigDecimal.ZERO
			}
		};
		Pedido instance = new Pedido();
		instance.inclui(new Item(2,BigDecimal.TEN));
		instance.inclui(new Item(3,BigDecimal.TEN));
		BigDecimal expResult = BigDecimal.valueOf(50);
		BigDecimal result = instance.total(desconto);
		assertEquals(expResult, result);
		
	}

}
