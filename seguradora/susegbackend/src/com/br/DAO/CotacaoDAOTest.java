package com.br.DAO;

import static org.junit.Assert.*;

import org.junit.Test;

public class CotacaoDAOTest {
	
	private int codigo;

	@Test
	public final void testSaveToDB() {
		CotacaoDAO cotacao = new CotacaoDAO();
		assertTrue("Erro ao salvar a cotacao.", cotacao.saveToDB());
		this.codigo = cotacao.getCodigo();
		assertTrue("Erro ao salvar a cotacao.", this.codigo > -1);
	}
	
	@Test
	public final void testLoadFromDB() {
		testSaveToDB();
		CotacaoDAO cotacao = CotacaoDAO.loadFromDB(this.codigo);
		assertTrue("Erro ao carregar a cotacao.", this.codigo == cotacao.getCodigo());
	}
	
	@Test
	public final void testClausulas() {
		testSaveToDB();
		CotacaoDAO cotacao = CotacaoDAO.loadFromDB(this.codigo);
		cotacao.addClausula(ClausulaDAO.loadFromDB(1)); //adiciona uma cobertura para carro reserva de 7 dias
		assertTrue(cotacao.getClausulas().get(0) == 1);
	}

}
