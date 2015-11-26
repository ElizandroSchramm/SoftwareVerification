package com.br.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.br.DAO.CondutorDAO;
import com.br.DAO.CotacaoDAO;

public class Cotacao {
	
	private CotacaoDAO dao;
	private List<Condutor> condutores;
	
	public Cotacao() {
		this.dao = new CotacaoDAO();
	}
	
	public Cotacao(int codigoCotacao) throws Exception{
		this.dao = CotacaoDAO.loadFromDB(codigoCotacao);
		if(this.dao == null){
			throw new Exception("Cotação não encontrada.");
		}
		this.condutores = new ArrayList<Condutor>();
		List<CondutorDAO> condutoresDAO = this.dao.getCondutoresDAO();
		for (CondutorDAO condutorDAO : condutoresDAO) {
			this.condutores.add(new Condutor(condutorDAO));
		}
	}
	
	public int save(){
		if (this.dao.saveToDB()) {
			return this.dao.getCodigo();
		} else {
			return -1;
		}
	}
	
	public void setCodLocalizacao(int codigo){
		this.dao.setCodLocalizacao(codigo);
	}
	
	public void setCodSegurado(int codigo){
		this.dao.setcodSegurado(codigo);
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
		sb.append(", codigoSeg: " + this.dao.getCodSegurado());
		sb.append(", codigoLoc: " + this.dao.getCodLocalizacao());
		sb.append("  }");
		return sb.toString();
	}

	public List<Condutor> getCondutores() {
		return condutores;
	}

	public void setCondutores(List<Condutor> condutores) {
		this.condutores = condutores;
	}

	public double getValorFIPVeiculo() {
		// TODO Implementar para retornar o valor do veículo
		return 35000;
	}

}
