package com.br.Model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import com.br.DAO.SeguradoDAO;

public class Segurado {
	
	private SeguradoDAO dao;
	private List<Cotacao> cotacoes;
	
	public Segurado() {
		this.dao = new SeguradoDAO();
	}
	
	public Segurado(int codigo){
		this.dao = SeguradoDAO.loadFromDB(codigo);
	}
	
	public void setDados(String nome, String cpf, String sexo, Date dataNascimento, int bonus, String cnpj, String ie){
		dao.setNome(nome);
		dao.setCpf(cpf);
		dao.setSexo(sexo);
		dao.setDataNascimento(dataNascimento);
		dao.setClasseBonus(bonus);
		dao.setCnpj(cnpj);
		dao.setInscricaoEstadual(ie);
	}
	
	public void setContato(String telefone){
		dao.setTelefone(telefone);
	}

	
	public void loadByCPF(String cpf){
		try {
			this.cotacoes = new ArrayList<Cotacao>();
			for (Integer cotacao: this.dao.loadFromDB("cpf = '" + cpf + "'")) {
				this.cotacoes.add(new Cotacao(cotacao));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadByCNPJ(String cnpj){
		try {
			this.cotacoes = new ArrayList<Cotacao>();
			for (Integer cotacao: this.dao.loadFromDB("cnpj = '" + cnpj + "'")) {
				this.cotacoes.add(new Cotacao(cotacao));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}	
	
	public String getCotacoes(){
		JSONArray JSONcotacoes = new JSONArray();

		for (Cotacao cotacao: cotacoes){
			JSONcotacoes.add(cotacao);
		}
		return "{\"cotacoes\":" + JSONcotacoes.toJSONString() + "}";
	}
	
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
		sb.append("{\"codigo\": \"" + this.dao.getCodigo() + "\"");
		sb.append(",\"nome\": \"" + this.dao.getNome() + "\"");
		if(this.dao.getCpf() != null){
			sb.append(",\"cpf\": \"" + this.dao.getCpf() + "\"");
			sb.append(",\"sexo\": \"" + this.dao.getSexo() + "\"");
			sb.append(",\"dataNascimento\": \"" + (new SimpleDateFormat("dd/MM/yyyy")).format(dao.getDataNascimento()) + "\"");
		} else {
			sb.append(",\"cnpj\": \"" + this.dao.getCnpj() + "\"");
			sb.append(",\"inscricaoestadual\": \"" + this.dao.getInscricaoEstadual() + "\"");
		}
		sb.append(",\"classeBonus\": \"" + this.dao.getClasseBonus() + "\"");
		sb.append(",\"telefone\": \"" + this.dao.getTelefone() + "\"");
		sb.append("  }");
		return sb.toString();
	}

}
