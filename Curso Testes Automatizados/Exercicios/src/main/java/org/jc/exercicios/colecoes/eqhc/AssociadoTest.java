package org.jc.exercicios.colecoes.eqhc;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.function.Predicate;

import org.junit.Test;

public class AssociadoTest {

	@Test
	public void filtraDependentesPorParteDoNome() {
		System.out.println("filtraDependentesPor");
		Predicate<Dependente> predicado = new Dependente.NomeContem("pje");
		Associado instance = new Associado(1,"Jean pje","999.999.999-88","666.666",LocalDate.now(),LocalDate.now());

		instance.incluiDependente("Jeanzinho", LocalDate.now());
        Dependente dependente = new Dependente(instance,"pje", LocalDate.now());
		instance.incluiDependente("pje", LocalDate.now());
		instance.incluiDependente("Clayzinho", LocalDate.now());
		
		Collection<Dependente> result = instance.filtraDependentesPor(predicado);
		
		assertThat(result).hasSize(1).containsExactly(dependente);
	}
}
