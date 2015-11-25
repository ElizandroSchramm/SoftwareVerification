package com.br.DAO;

import static org.junit.Assert.*;

import org.junit.Test;

public class CondutorDAOTest {
	
	private int codigo;
	private String nome = "Elizandro";

	@Test
	public final void testSaveToDB() {
		CondutorDAO dao = new CondutorDAO();
		dao.setCodCotacao(1);
		dao.setNome(nome);
		dao.setCpf("987.654.321-12");
		dao.setIdade(25);
		dao.setSexo("M");
		dao.setTemFilho("N");
		dao.setCasado("N");
		if(dao.saveToDB()){
			this.codigo = dao.getCodigo();
			assertTrue("Erro ao salvar o condutor.", this.codigo > -1);
		} else {
			fail("Erro ao salvar o condutor.");
		}
	}

	@Test
	public final void testLoadFromDB() {
		testSaveToDB();
		CondutorDAO dao = CondutorDAO.loadFromDB(this.codigo);
		assertTrue("Erro ao carregar condutor.", nome.equals(dao.getNome()));
	}
	
}
