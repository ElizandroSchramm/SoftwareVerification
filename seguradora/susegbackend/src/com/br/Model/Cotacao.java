package com.br.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import com.br.DAO.CotacaoDAO;

public class Cotacao {
	
	private CotacaoDAO dao;
	private List<Condutor> condutores;
	private List<Veiculo> veiculos;
	private List<Clausula> clausulas;
	
	public Cotacao() {
		this.dao = new CotacaoDAO();
	}
	
	public Cotacao(int codigoCotacao) throws Exception{
		this.dao = CotacaoDAO.loadFromDB(codigoCotacao);
		if(this.dao == null){
			throw new Exception("Cotação não encontrada.");
		}
		this.condutores = new ArrayList<Condutor>();
		for (Integer condutor: this.dao.getCondutores()) {
			this.condutores.add(new Condutor(condutor));
		}
		this.veiculos = new ArrayList<Veiculo>();
		for (Integer veiculo : this.dao.getVeiculos()) {
			this.veiculos.add(new Veiculo(veiculo));
		}
		this.clausulas = new ArrayList<Clausula>();
		for (Integer clausula : this.dao.getClausulas()) {
			this.clausulas.add(new Clausula(clausula));
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
		sb.append("{ \"codigo\": \"" + this.dao.getCodigo() + "\"");
		sb.append(", \"comissao\": \"" + this.dao.getComissao() + "\"");
		sb.append(", \"dataCriacao\": \"" + (new SimpleDateFormat("dd/MM/yyyy")).format(dao.getDataCriacao()) + "\"");
		sb.append(", \"valor\": \"" + this.dao.getValor() + "\"");
		sb.append(", \"vigencia\": \"" + (new SimpleDateFormat("dd/MM/yyyy")).format(dao.getVigencia()) + "\"");
		sb.append(", \"codigoSeg\": \"" + this.dao.getCodSegurado() + "\"");
		sb.append(", \"codigoLoc\": \"" + this.dao.getCodLocalizacao() + "\"");
		sb.append("  }");
		return sb.toString();
	}

	public List<Condutor> getCondutores() {
		return condutores;
	}

	public String condutoresToString(){
		JSONArray condutores = new JSONArray();
		for (Condutor condutor: this.condutores) {
			try{
				condutores.add(condutor);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return "{\"condutores\":" + condutores.toJSONString() + "}";
	}

	public String veiculosToString(){
		JSONArray veiculos = new JSONArray();
		for (Veiculo veiculo: this.veiculos) {
			try{
				veiculos.add(veiculo);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return "{\"veiculos\":" + veiculos.toJSONString() + "}";
	}
	
	public String clausulasToString(){
		JSONArray clausulas = new JSONArray();
		for(Clausula clausula: this.clausulas){
			clausulas.add(clausula);
		}
		return "{\"clausulas\":" + clausulas.toJSONString() + "}";
	}
	
	public void setCondutores(List<Condutor> condutores) {
		this.condutores = condutores;
	}
	
	public List<Veiculo> getVeiculos() {
		return this.veiculos;
	}

}
