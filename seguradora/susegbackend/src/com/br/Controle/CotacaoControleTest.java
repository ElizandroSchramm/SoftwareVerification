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
			assertTrue("N‹o testou certo - se tiver idade menor que 26", premio == 1239);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
	@Test
	public final void testGetDetalhes(){
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao(1));
			String esperado = "{'valores':[{'descricao':'Valor base do prmio','valor':'1050.0'}{'descricao':'Perfil do condutor','valor':'136.5'}{'descricao':'Carro com mais de 10 anos','valor':'52.5'}{'descricao':'Valor do prmio','valor':'1239.0'}]}";
			assertTrue("N‹o testou certo", co.getValoresDetalhados().equals(esperado));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
}
