package com.br.Controle;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.br.Model.Condutor;
import com.br.Model.Cotacao;

public class CotacaoControleTest {
	
	/**
	 * Estes testes s‹o realiados com uma base que tenha uma cotacao com:
	 * - pelo menos 1 condutor em seu pior perfil;
	 * - apena 1 ve’culo no valor de 35000,00 com mais de 10 anos.
	 */

	@Test
	public final void testGetCondutorPiorPerfil() {
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao(69));
			Condutor cn = co.getCondutorPiorPerfil();
			assertTrue("Nao testou certo - se tiver idade menor que 26", cn.getIdade() < 26);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
	@Test
	public final void testCalculaPremio(){
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao(69));
			double premio = co.calculaPremio();
			assertTrue("Nao testou certo - se premio nao for 3605.2245", premio == 3605.2245);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
	@Test
	public final void testGetDetalhes(){
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao(69));
			String esperado = "{\"valores\":[{\"descricao\":\"Valor base do premio\",\"valor\":\"1050.0\"},{\"descricao\":\"Perfil do condutor\",\"valor\":\"136.5\"},{\"descricao\":\"Carro com mais de 10 anos\",\"valor\":\"52.5\"},{\"id\":\"1\",\"descricao\":\"Valor do premio\",\"valor\":\"3605.2245\"}]}";
			assertTrue("Nao testou certo", co.getValoresDetalhados().equals(esperado));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
	@Test
	public final void testCalculoValorFranquia(){
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao());
			double franquia = co.calculaFranquia(1650.0);
			assertTrue("Valor da franquia deve ser 1815 para premio de 1650 ", franquia == 1815.0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
	@Test
	public final void testCalculoValorPremioSeguro(){
		try {
			CotacaoControle co = new CotacaoControle(new Cotacao());
			double franquia = co.calculaPremio();
			assertTrue("Valor da franquia deve ser 1815 para premio de 1650 ", franquia == 1815.0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro ao criar o controle");
		}
	}
	
	@Test
	public final void testCalculoValorPremioCondutorMulherMenor25Anos(){
		/*try {
			Condutor condutor = new Condutor();
			condutor.setDados("Maria", "046.319.119-64", "M", 24);
			condutor.setSituacao("N", "N");
			List<Condutor> condutores;
			condutores.add(condutor);
			Cotacao cotacao = new Cotacao();
			cotacao.setCondutores(condutores);
			CotacaoControle co = new CotacaoControle(cotacao);
		}*/
	}
	
}
