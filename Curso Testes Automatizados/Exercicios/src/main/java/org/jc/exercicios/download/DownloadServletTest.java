package org.jc.exercicios.download;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.junit.Test;
import org.mockito.Mockito;

public class DownloadServletTest {

	@Rule
	public final TemporaryFolder temp = new TemporaryFolder(); 

	private File dir;
	
	@Before
	
	@After
	public void Teardown() {
		
	}
	
	
	@Test
	public void testeObtemConteudoDeUmaNotaFiscal() throws Exception {
		System.out.println("Obtem Conteudo De Uma Nota Fiscal");
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		
		Mockito.when(req.authenticate(resp)).thenReturn(Boolean.TRUE);
		
		File diretorio = new File();
		
		FileWriter fileWriter = new FileWriter(diretorio);
		fileWriter.flush();
		
		System.setProperty("nfe.dir",dir.getAbsolutePath());
		DownloadServlet instance = new DownloadServlet();
		instance.doGet(req, resp);
	}
	
/*	
	@Test
	public void testDoGet() throws Exception {
		System.out.println("calcula Total Sem Desconto");
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		DownloadServlet instance = new DownloadServlet();
		instance.doGet(req, resp);
	}
*/
}
