package org.jc.exercicios.excecoes;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.junit.WireMockRule;


public class PaginaTest {

	@Rule
	public WireMockRule wmr = new WireMockRule(8787);
	
	@Test
	public void testaDownload() throws Exception{
		System.out.println("testa download");

		
//		Pagina instance = new Pagina("http://portalintranet.redetst/web/tst-intranet/inicio");
//		String result = instance.download();
//		Assertions.assertThat(result).contains("Busca de Ramais");
		
		 stubFor(get(urlMatching("/test"))
		            .willReturn(aResponse()
		                .withHeader("Content-Type", "text/xml")
		                .withBody("<response>Conteudo de Teste</response>")));		
		Pagina instance = new Pagina("http://localhost:8787/test");
		String result = instance.download();
		Assertions.assertThat(result).isEqualTo("<response>Conteudo de Teste</response>");
	}

}
