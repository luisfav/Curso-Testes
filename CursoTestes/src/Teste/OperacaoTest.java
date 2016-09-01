package Teste;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class OperacaoTest {

	@Test
	public void retornaProprioNumeroQuandoOperacaoForSemOperadores() {
		String expressao = "2";
		int esperado = 2;
		Nodo nodo = new Nodo(expressao);
		int resultado = nodo.avalia();
		Assertions.assertThat(resultado).isEqualTo(esperado);
	} 
	
	public void retornaCincooQuandoOperacaoForTresMaisCinco() {
		String expressao = "2+3";
		int esperado = 5;
		Nodo nodo = new Nodo(expressao);
		int resultado = nodo.avalia();
		Assertions.assertThat(resultado).isEqualTo(esperado);
	}

	public void retornaDezoitoQuandoOperacaoForTresVezesSeis() {
		String expressao = "3*6";
		int esperado = 18;
		Nodo nodo = new Nodo(expressao);
		int resultado = nodo.avalia();
		Assertions.assertThat(resultado).isEqualTo(esperado);
	}

}
