package com.br.Model;

import com.br.DAO.CotacaoDAO;

public class Cotacao {
	
	private CotacaoDAO dao;
	
	public Cotacao() {
		this.dao = new CotacaoDAO();
	}
	
	public int save(){
		if (this.dao.saveToDB()) {
			return this.dao.getCodigo();
		} else {
			return -1;
		}
	}
	
	

}
