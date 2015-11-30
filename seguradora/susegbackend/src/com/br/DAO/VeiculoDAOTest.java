package com.br.DAO;

import static org.junit.Assert.*;

import org.junit.Test;

public class VeiculoDAOTest {
	
	private int codigo;
	private String modelo = "Celta";
	private double valorFIP = 35000;
	
	@Test
	public final void testSaveToDB() {
		VeiculoDAO veiculo = new VeiculoDAO();
		veiculo.setModelo(this.modelo);
		veiculo.setValorFIP(this.valorFIP);
		veiculo.setCotacao(1);
		if(veiculo.saveToDB()){
			this.codigo = veiculo.getCodigo();
			assertTrue("Erro ao salvar o ve’culo.", this.codigo > -1);
		} else {
			fail("Erro ao salvar ve’culo.");
		}
	}

	@Test
	public final void testLoadFromDB() {
		testSaveToDB();
		VeiculoDAO dao = VeiculoDAO.loadFromDB(this.codigo);
		assertTrue("Erro ao carregar ve’culo.", this.modelo.equals(dao.getModelo()));
	}


}
