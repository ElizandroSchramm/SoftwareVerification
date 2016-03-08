package com.br.TestesIntegracao;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.br.Controle.CotacaoControle;
import com.br.DAO.CotacaoDAO;
import com.br.DAO.VeiculoDAO;
import com.br.DAO.VeiculoFIPEDAO;
import com.br.Model.Condutor;
import com.br.Model.Cotacao;
import com.br.Model.Localizacao;
import com.br.Model.Segurado;
import com.br.Model.Veiculo;


public class TestesIntegracaoCotacao {
	private int codigoCotacao;
	private Cotacao cotacao;
	private Localizacao localizacao;
	private Segurado segurado;
	private Veiculo veiculo;
	private Condutor condutor;
	@Before
	public void setUp() throws Exception {
		cotacao = new Cotacao();
		codigoCotacao = cotacao.save();
		localizacao = new Localizacao();
		localizacao.setEndereco("Rua Helma Buettner", 69, "89041-010");
		localizacao.setLocal("Blumenau", "SC", "Brasil");
	}
	
	@Test
	public void testInserirVeiculoNaCotacao() throws Exception
	{	
		veiculo = new Veiculo();
		veiculo.setAnos(2015, 2016);
		veiculo.setDados("320iA GT Sport 2.0 Turbo 16V 184cv 5p", "BMW");
		veiculo.setRegistros("23165561", "Branco", "MKV-1015", "103265", 230);
		veiculo.setCotacao(codigoCotacao);
		veiculo.save();
		cotacao = new Cotacao(codigoCotacao);
		
		assertTrue("Cotacao deve possuir 1 veiculo", cotacao.getVeiculos().size() == 1);
	}
	
	@Test
	public void testInserirListaVeiculosNaCotacao() throws Exception{
		veiculo = new Veiculo();
		veiculo.setAnos(2015, 2016);
		veiculo.setDados("320iA GT Sport 2.0 Turbo 16V 184cv 5p", "BMW");
		veiculo.setRegistros("23165561", "Branco", "MKV-1015", "103265", 230);
		veiculo.setCotacao(codigoCotacao);
		veiculo.save();
		
		veiculo = new Veiculo();
		veiculo.setAnos(2015, 2016);
		veiculo.setDados("C3", "Citroen");
		veiculo.setRegistros("654654", "Branco", "MCU-1333", "656546", 490);
		veiculo.setCotacao(codigoCotacao);
		veiculo.save();
		
		cotacao = new Cotacao(codigoCotacao);
		
		assertTrue("Cotacao deve possuir 2 veiculos", cotacao.getVeiculos().size() ==2);
	}
	
	@Test
	public void testInserirCondutorNaCotacao() throws ParseException{
		condutor = new Condutor();
		condutor.setDados("Ricardo Voigt", "046.319.119-64", "M", 28);
		condutor.setSituacao("S", "N");
		condutor.setCotacao(codigoCotacao);
		condutor.save();
		List<Condutor> condutores = new ArrayList<Condutor>();
		condutores.add(condutor);
		cotacao.setCondutores(condutores);
		
		assertTrue("Salvar a cotacao sem erro", cotacao.getCondutores().size() == 1);
	}
	
	@Test
	public void testCalculoValorBasePremioVeiculoValor20Mil() throws Exception{
		condutor = new Condutor();
		condutor.setDados("Ricardo Voigt", "046.319.119-64", "M", 28);
		condutor.setSituacao("S", "N");
		condutor.setCotacao(codigoCotacao);
		condutor.save();
		List<Condutor> condutores = new ArrayList<Condutor>();
		condutores.add(condutor);
		cotacao.setCondutores(condutores);
		veiculo = new Veiculo("009185-5", 2016, 2015);
		veiculo.setCotacao(codigoCotacao);
		
		//veiculo.setValorFIP(20000);
		veiculo.save();
		cotacao.save();
		cotacao = new Cotacao(codigoCotacao);
		CotacaoControle cotacaoControle = new CotacaoControle(cotacao);
		double total = cotacaoControle.calculaBasePremio();

		assertTrue("O valor base da cotacao deve ser 4625 ",4625 == Math.round(total));
	}
	
	@Test
	public void testCalculoPercentualPerfilCondutorHomemMaior25Anos() throws Exception{
		condutor = new Condutor();
		condutor.setDados("Ricardo Voigt", "046.319.119-64", "M", 28);
		condutor.setSituacao("S", "N");
		condutor.setCotacao(codigoCotacao);
		condutor.save();
		List<Condutor> condutores = new ArrayList<Condutor>();
		condutores.add(condutor);
		cotacao.setCondutores(condutores);
		veiculo = new Veiculo("009185-5", 2016, 2015);
		veiculo.setCotacao(codigoCotacao);

		veiculo.save();
		cotacao.save();
		cotacao = new Cotacao(codigoCotacao);
		CotacaoControle cotacaoControle = new CotacaoControle(cotacao);
		double total = cotacaoControle.getValorPerfilCondutor();
		
		assertTrue("O valor de calculo do percentual é 139",139 == Math.round(total));
	}
	
	@Test
	public void testCalculoValorPremioCondutorHomemMaior25Anos() throws Exception{
		condutor = new Condutor();
		condutor.setDados("Ricardo Voigt", "046.319.119-64", "M", 28);
		condutor.setSituacao("S", "N");
		condutor.setCotacao(codigoCotacao);
		condutor.save();
		List<Condutor> condutores = new ArrayList<Condutor>();
		condutores.add(condutor);
		cotacao.setCondutores(condutores);
		veiculo = new Veiculo("009185-5", 2016, 2015);
		veiculo.setCotacao(codigoCotacao);
		
		veiculo.save();
		cotacao.save();
		cotacao = new Cotacao(codigoCotacao);
		CotacaoControle cotacaoControle = new CotacaoControle(cotacao);
		double total = cotacaoControle.calculaPremio();
		assertTrue("O valor de calculo do premio é 4764", 4764 == Math.round(total));
	}
	
	@After
	public void tearDown()  {
				
	}
}
