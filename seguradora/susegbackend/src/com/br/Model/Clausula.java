package com.br.Model;

import com.br.DAO.ClausulaDAO;

public class Clausula {
	
	private ClausulaDAO dao;
	
	public Clausula(int codigo) {
		this.dao = ClausulaDAO.loadFromDB(codigo);
	}
	
	public Clausula(String tipo) throws Exception {
		this.dao = ClausulaDAO.loadFromDB(tipo);
		if(dao == null){
			throw new Exception("Cláusula tipo " + tipo + " não está cadastrada.");
		}
	}
	
	public String getDescricao(){
		return this.dao.getDescricao();
	}
	
	public String getTipo(){
		return this.dao.getTipo();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ \"codigo\": \"" + this.dao.getCodigo() + "\"");
		sb.append(", \"tipo\": \"" + this.dao.getTipo() + "\"");
		sb.append(", \"descricao\": \"" + this.dao.getDescricao() + "\"");
		sb.append("  }");
		return sb.toString();
	}

	public ClausulaDAO getDAO() {
		return this.dao;
	}

}
