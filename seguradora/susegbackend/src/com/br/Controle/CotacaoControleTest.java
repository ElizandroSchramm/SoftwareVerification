package com.br.Controle;

import static org.junit.Assert.*;

import org.junit.Test;

import com.br.Model.Condutor;
import com.br.Model.Cotacao;

public class CotacaoControleTest {
	
	/**
	 * Estes testes são realiados com uma base que tenha uma cotação com:
	 * - pelo menos 1 condutor em seu pior perfil;
	 * - apena 1 veículo no valor de 35000,00 com mais de 10 anos.
	 */

	@Test
	public final void testGetCondutorPiorPerfil() {
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao(1));
			Condutor cn = co.getCondutorPiorPerfil();
			assertTrue("Não testou certo - se tiver idade menor que 26", cn.getIdade() < 26);
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
			assertTrue("Não testou certo - se premio nao for 1239", premio == 1239);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
	@Test
	public final void testGetDetalhes(){
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao(1));
			String esperado = "{\"valores\":[{\"descricao\":\"Valor base do prêmio\",\"valor\":\"1050.0\"},{\"descricao\":\"Perfil do condutor\",\"valor\":\"136.5\"},{\"descricao\":\"Carro com mais de 10 anos\",\"valor\":\"52.5\"},{\"id\":\"1\",\"descricao\":\"Valor do prêmio\",\"valor\":\"1239.0\"}]}";
			assertTrue("Não testou certo", co.getValoresDetalhados().equals(esperado));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
}
