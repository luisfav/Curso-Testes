package org.jc.exercicios.colecoes;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;


//@RunWith(Parameterized.class)
public class TagsTest {

	@Test
	public void testaTag() {
		//Organizar
		String tag = "tag1:tag2:tag3";
		Tags instance = new Tags(tag);
		List<String> resultEsperado = Arrays.asList("tag1","tag2","tag3");
		//Agir
		List<String> result = instance.toList();
		//Verificar
		assertEquals(resultEsperado, result);
	}
	
	
	@Test
	public void testaTagVazia() { //falta terminar
		//Organizar
		String tag = "          ";
		Tags instance = new Tags(tag);
		List<String> resultEsperado = Arrays.asList("tag1","tag2","tag3");
		//Agir
		List<String> result = instance.toList();
		//Verificar
		assertEquals(resultEsperado, result);
	}
}


