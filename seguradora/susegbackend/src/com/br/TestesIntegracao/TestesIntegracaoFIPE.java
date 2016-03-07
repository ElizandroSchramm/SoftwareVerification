package com.br.TestesIntegracao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mockito.Mockito;

import com.br.Servlet.RetornaDadosFIPE;

public class TestesIntegracaoFIPE extends Mockito {

	@Test
	public void testGetFabricantesFIPE() {

		RetornaDadosFIPEFriendly dados = new RetornaDadosFIPEFriendly();
		try {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);

			when(request.getParameter("acao")).thenReturn("marcas");
			String nome = "testGetFabricantesFIPE.txt";
			PrintWriter writer = new PrintWriter(nome);
			when(response.getWriter()).thenReturn(writer);

			dados.callDoGet(request, response);

			writer.flush();
			assertTrue("N‹o carregou a marca.", FileUtils.readFileToString(new File(nome),"UTF-8").contains("Audi"));

		} catch (ServletException e) {
			e.printStackTrace();
			fail("Erro ao chamar a lista de marcas");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Erro ao chamar a lista de marcas");
		}

	}

	@Test
	public void testGetModelosPorFabricanteFIPE() {
		RetornaDadosFIPEFriendly dados = new RetornaDadosFIPEFriendly();
		try {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);

			when(request.getParameter("acao")).thenReturn("veiculos");
			when(request.getParameter("marca")).thenReturn("Audi");
			when(request.getParameter("anomodelo")).thenReturn("2015");
			String nome = "testGetModelosPorFabricanteFIPE.txt";
			PrintWriter writer = new PrintWriter(nome);
			when(response.getWriter()).thenReturn(writer);

			dados.callDoGet(request, response);

			writer.flush();
			assertTrue("N‹o carregou o modelo.", FileUtils.readFileToString(new File(nome),"UTF-8").contains("A6 2.0 TFSI 252cv S tronic 4p"));

		} catch (ServletException e) {
			e.printStackTrace();
			fail("Erro ao chamar a lista de modelos");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Erro ao chamar a lista de modelos");
		}
	}

	@SuppressWarnings("serial")
	class RetornaDadosFIPEFriendly extends RetornaDadosFIPE {

		public void callDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			super.doGet(request, response);
		}

	}

}
