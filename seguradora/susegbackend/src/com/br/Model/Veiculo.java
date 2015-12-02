package com.br.Model;

import com.br.DAO.VeiculoDAO;
import com.br.DAO.VeiculoFIPEDAO;

public class Veiculo {
	
	
	private VeiculoDAO dao;
	
	public Veiculo() {
		this.dao = new VeiculoDAO();
	}
	
	public Veiculo(int codigo) throws Exception{
		this.dao = VeiculoDAO.loadFromDB(codigo);
		if(this.dao == null){
			throw new Exception("Veículo não encontrada.");
		}
	}
	
	public Veiculo(String codigoFipe, int anoModelo, int anoFabricacao){
		VeiculoFIPEDAO fipe = VeiculoFIPEDAO.loadFromDB(codigoFipe, anoModelo);
		this.dao = new VeiculoDAO();
		this.setAnos(anoFabricacao, anoModelo);
		this.setDados(fipe.getNome(), fipe.getMarca());
		this.setValorFIP(fipe.getPreco());
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
	
	public void setRegistros(String chassi, String cor, String placa, String renavam, int mediaKMMes) {
		this.dao.setChassi(chassi);
		this.dao.setCor(cor);
		this.dao.setPlaca(placa);
		this.dao.setRenavam(renavam);
		this.dao.setMediaKMMes(mediaKMMes);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"codigo\":\"" + this.dao.getCodigo() + "\",");
		sb.append("\"anoFabricacao\":\"" + this.dao.getAnoFabricacao() + "\",");
		sb.append("\"anoModelo\":\"" + this.dao.getAnoModelo() + "\",");
		sb.append("\"mediaKMMes\":\"" + this.dao.getMediaKMMes() + "\",");
		sb.append("\"cotacao\":\"" + this.dao.getCotacao() + "\",");
		sb.append("\"chassi\":\"" + this.dao.getChassi() + "\",");
		sb.append("\"cor\":\"" + this.dao.getCor() + "\",");
		sb.append("\"modelo\":\"" + this.dao.getModelo() + "\",");
		sb.append("\"placa\":\"" + this.dao.getPlaca() + "\",");
		sb.append("\"renavam\":\"" + this.dao.getRenavam() + "\",");
		sb.append("\"marca\":\"" + this.dao.getMarca() + "\",");
		sb.append("\"valorFIP\":\"" + this.dao.getValorFIP() + "\"");
		sb.append("}");
		return sb.toString();
	}
	
	//TODO: o cara que retornar os veículos da cotação fará um loop pelos veículos igual a esse aqui:	
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("{\"veiculos\":[");
//		for(int i = 0; i < 3; i++){
//			sb.append(this.toString2());
//			sb.append(",");
//		}
//		sb.append(this.toString2());
//		sb.append("]}");
//		return sb.toString();
//	}

}
