package com.br.Model;

import com.br.DAO.VeiculoDAO;

public class Veiculo {
	
	
	private VeiculoDAO dao;
	
	public Veiculo() {
		this.dao = new VeiculoDAO();
	}
	
	public Veiculo(int codigo) throws Exception{
		this.dao = VeiculoDAO.loadFromDB(codigo);
		if(this.dao == null){
			throw new Exception("Ve’culo n‹o encontrada.");
		}
	}
	
	public double getValorFIP() {
		return this.dao.getValorFIP();
	}
	public void setValorFIP(double valorFIP) {
		this.dao.setValorFIP(valorFIP);
	}
	
	public void setAnos(int anoFab, int anoMod){
		this.dao.setAnoFabricacao(anoFab);
		this.dao.setAnoModelo(anoMod);
	}
	
	public int getAnoFabricacao() {
		return this.dao.getAnoFabricacao();
	}

	public int getAnoModelo() {
		return this.dao.getAnoModelo();
	}

	public void setDados(String modelo, String marca) {
		this.dao.setModelo(modelo);
		this.dao.setMarca(marca);
	}

	public void save() {
		this.dao.saveToDB();
	}

	public void setCotacao(int cotacao) {
		this.dao.setCotacao(cotacao);
	}

}
