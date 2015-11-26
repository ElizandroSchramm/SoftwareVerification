package com.br.Controle;

import static org.junit.Assert.*;

import org.junit.Test;

import com.br.Model.Condutor;
import com.br.Model.Cotacao;

public class CotacaoControleTest {

	@Test
	public final void testGetCondutorPiorPerfil() {
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao(1));
			Condutor cn = co.getCondutorPiorPerfil();
			assertTrue("N‹o testou certo - se tiver idade menor que 26", cn.getIdade() < 26);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
	@Test
	public final void testCalculaPremio(){
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao(1));
			double premio = co.calculaPremio();
			assertTrue("N‹o testou certo - se tiver idade menor que 26", premio == 1186.5);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
}
