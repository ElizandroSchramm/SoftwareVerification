package com.br.DAO;

import static org.junit.Assert.*;

import org.junit.Test;

public class CotacaoDAOTest {
	
	private int codigo;

	@Test
	public final void testSaveToDB() {
		CotacaoDAO cotacao = new CotacaoDAO();
		assertTrue("Erro ao salvar a cota��o.", cotacao.saveToDB());
		this.codigo = cotacao.getCodigo();
		assertTrue("Erro ao salvar a cota��o.", this.codigo > -1);
	}
	
	@Test
	public final void testLoadFromDB() {
		testSaveToDB();
		CotacaoDAO cotacao = CotacaoDAO.loadFromDB(this.codigo);
		assertTrue("Erro ao carregar a cota��o.", this.codigo == cotacao.getCodigo());
	}

}
