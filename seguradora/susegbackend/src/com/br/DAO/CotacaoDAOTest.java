package com.br.DAO;

import static org.junit.Assert.*;

import org.junit.Test;

public class CotacaoDAOTest {

	@Test
	public final void testCotacaoDAO() {
		try {
			new CotacaoDAO();
		} catch (Exception e) {
			fail("Erro ao criar a CotacaoDAO");
		}
	}

	@Test
	public final void testLoadFromDB() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSaveToDB() {
		CotacaoDAO cotacao = new CotacaoDAO();
		assertTrue(cotacao.saveToDB());
	}

	@Test
	public final void testGetComissao() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetComissao() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetValor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetValor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetVigencia() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetVigencia() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetCodigo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetDataCriacao() {
		fail("Not yet implemented"); // TODO
	}

}
