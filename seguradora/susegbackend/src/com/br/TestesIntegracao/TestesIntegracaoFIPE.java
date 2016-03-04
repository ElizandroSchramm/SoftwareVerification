package com.br.TestesIntegracao;

import static org.junit.Assert.fail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.br.Servlet.RetornaDadosFIPE;

public class TestesIntegracaoFIPE {

	@Test
	public void testGetFabricantesFIPE() {
		
		RetornaDadosFIPEFriendly dados = new RetornaDadosFIPEFriendly();
		try {
			dados.callDoGet(null, null); //ToDo: dar um jeito de instanciar um request e um response para fazer os testes
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
		fail("Not yet implemented");
	}
	
	class RetornaDadosFIPEFriendly extends RetornaDadosFIPE {
		
		public void callDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			super.doGet(request, response);
		}
		
	}
	
}
