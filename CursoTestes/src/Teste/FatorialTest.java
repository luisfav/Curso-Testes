package Teste;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class FatorialTest {

	@Parameterized.Parameters
	public static Iterable<Object[]> parametros() {
		return Arrays.asList(new Object[][]{
			{0,1},{1,1},{2,2},{10,3628800},{12,479001600}
		});
	}

	private final int numero;
	private final int esperado;

	public FatorialTest(int numero, int esperado) {
	    this.numero = numero;
	    this.esperado = esperado;
	}
	
	@Test
	public void testaFatorial() {
		Fatorial instance = new Fatorial();
		int resultado = instance.calcula(numero);
		assertEquals(esperado, resultado);
	}
	
	/*@Test
	public void testaZero() {
		int numero = 0;
		Fatorial instance = new Fatorial();
		int resultEsperado = 1;
		int result = instance.calcula(numero);
		assertEquals(resultEsperado, result);
	}

	@Test
	public void testaUm() {
		int numero = 1;
		Fatorial instance = new Fatorial();
		int resultEsperado = 1;
		int result = instance.calcula(numero);
		assertEquals(resultEsperado, result);
	}

	@Test
	public void testaDez() {
		int numero = 10;
		Fatorial instance = new Fatorial();
		int resultEsperado = 3628800;
		int result = instance.calcula(numero);
		assertEquals(resultEsperado, result);
	}

	@Test
	public void testaDoze() {
		int numero = 12;
		Fatorial instance = new Fatorial();
		int resultEsperado = 479001600;
		int result = instance.calcula(numero);
		assertEquals(resultEsperado, result);
	}*/
}
