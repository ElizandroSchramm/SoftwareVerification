package com.br.Model;

import java.text.SimpleDateFormat;

import com.br.DAO.CotacaoDAO;

public class Cotacao {
	
	private CotacaoDAO dao;
	
	public Cotacao() {
		this.dao = new CotacaoDAO();
	}
	
	public Cotacao(int codigoCotacao) throws Exception{
		this.dao = CotacaoDAO.loadFromDB(codigoCotacao);
		if(this.dao == null){
			throw new Exception("Cotação não encontrada.");
		}
	}
	
	public int save(){
		if (this.dao.saveToDB()) {
			return this.dao.getCodigo();
		} else {
			return -1;
		}
	}

	public void setValor(double valor) {
		this.dao.setValor(valor);
		this.dao.setComissao(valor * 0.1); //TODO: fazer calcular a comissao de acordo com o que estiver configurado
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ codigo: " + this.dao.getCodigo());
		sb.append(", comissao: " + this.dao.getComissao());
		sb.append(", dataCriacao: " + (new SimpleDateFormat("dd/MM/yyyy")).format(dao.getDataCriacao()));
		sb.append(", valor: " + this.dao.getValor());
		sb.append(", vigencia: " + (new SimpleDateFormat("dd/MM/yyyy")).format(dao.getVigencia()));
		sb.append("  }");
		return sb.toString();
	}

}
