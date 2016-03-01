package com.br.Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CotacaoTest {

	@Test
	public void testInserirNovaCotacao() {
		Cotacao cotacao = new Cotacao();
		assertTrue("N‹o foi poss’vel inserir a cota‹o.", cotacao.save() > 0);
	}

}
