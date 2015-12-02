package com.br.Model;

import com.br.DAO.ApoliceDAO;

public class Apolice {

	private ApoliceDAO dao;
	
	public Apolice(int codCotacao) {
		this.dao = new ApoliceDAO();
		this.dao.setCodCotacao(codCotacao);		
	}
	
	public int save(){
		if (this.dao.saveToDB()) {
			return this.dao.getCodigo();
		} else {
			return -1;
		}
	}
	
	public static boolean EhApolice(int codCotacao){
		if (ApoliceDAO.loadFromDB(codCotacao) != null){
			return true;
		}	
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ 'codigo': '" + this.dao.getCodigo() + "'");
		sb.append(", 'codCotacao': '" + this.dao.getCodCotacao() + "'");
		sb.append("  }");
		return sb.toString();
		
	}

}
