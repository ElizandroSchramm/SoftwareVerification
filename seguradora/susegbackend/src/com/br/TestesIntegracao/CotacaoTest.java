package com.br.TestesIntegracao;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.br.Model.Cotacao;
import com.br.Model.Localizacao;
import com.br.Model.Segurado;
import com.br.Model.Veiculo;

public class CotacaoTest {
	private int codigoCotacao;
	private Cotacao cotacao;
	private Localizacao localizacao;
	private Segurado segurado;
	private Veiculo veiculo;
	@Before
	public void setUp() throws Exception {
		cotacao = new Cotacao();
		codigoCotacao = cotacao.save();
		localizacao = new Localizacao();
		localizacao.setEndereco("Rua Helma Buettner", 69, "89041-010");
		localizacao.setLocal("Blumenau", "SC", "Brasil");
		segurado = new Segurado();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		segurado.setDados("Ricardo Voigt", 
							"046.319.119-64", 
							"M", 
							new Date(sdf.parse("14/02/1988").getTime()), 
							5, 
							"", 
							"");
		
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

	@After
	public void tearDown()  {
				
	}
}
