package com.br.Model;

import com.br.DAO.LocalizacaoDAO;

public class Localizacao {
	
	private LocalizacaoDAO dao;
	
	public Localizacao(){
		this.dao = new LocalizacaoDAO();
	}
	
	public Localizacao(int codigo){
		this.dao = LocalizacaoDAO.loadFromDB(codigo);
	}
	
	public void setEndereco(String rua, int numero, String cep){
		dao.setRua(rua);
		dao.setNumero(numero);
		dao.setCep(cep);
	}
	
	public void setLocal(String cidade, String estado, String pais){
		dao.setCidade(cidade);
		dao.setEstado(estado);
		dao.setPais(pais);
	}
	
	//TODO: implementar os dados da pernoite e di‡ria (garagem) na sprint 2.
	
	public int save(){
		if(dao.saveToDB()){
			return dao.getCodigo();
		} else {
			return -1; 
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ 'codigo': '" + this.dao.getCodigo() + "'");
		sb.append(", 'rua': '" + this.dao.getRua() + "'");
		sb.append(", 'numero': '" + this.dao.getNumero() + "'");
		sb.append(", 'cep': '" + this.dao.getCep() + "'");
		sb.append(", 'cidade': '" + this.dao.getCidade() + "'");
		sb.append(", 'estado': '" + this.dao.getEstado() + "'");
		sb.append(", 'pais': '" + this.dao.getPais() + "'");
		sb.append("  }");
		return sb.toString();
	}

}
