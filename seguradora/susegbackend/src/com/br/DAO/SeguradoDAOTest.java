package com.br.DAO;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class SeguradoDAOTest {
	
	private int codigo;
	private String nome = "Maria";
	
	@Test
	public final void testSaveToDB() {
		SeguradoDAO segurado = new SeguradoDAO();
		segurado.setNome(nome);
		segurado.setCpf("123.456.789-20");
		Calendar cal = new GregorianCalendar();
		cal.set(1988, 9, 8);
		segurado.setDataNascimento(new Date(cal.getTime().getTime()));
		segurado.setSexo("F");
		segurado.setTelefone("3333-8888"); //TODO: rever para funcionar com o DDD
		if(segurado.saveToDB()){
			this.codigo = segurado.getCodigo();
			assertTrue("Erro ao salvar o segurado.", this.codigo > -1);
		} else {
			fail("Erro ao salvar o segurado.");
		}
	}

	@Test
	public final void testLoadFromDB() {
		testSaveToDB();
		SeguradoDAO segurado = SeguradoDAO.loadFromDB(codigo);
		assertTrue("Erro ao carregar segurado.", nome.equals(segurado.getNome()));
	}

}
