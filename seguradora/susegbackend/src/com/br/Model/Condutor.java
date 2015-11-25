package com.br.Model;

import com.br.DAO.CondutorDAO;

public class Condutor {
	
	private CondutorDAO dao;
	
	public Condutor() {
		this.dao = new CondutorDAO();
	}
	
	public Condutor(int codigo) throws Exception{
		this.dao = CondutorDAO.loadFromDB(codigo);
		if(this.dao == null){
			throw new Exception("Condutor n‹o encontrada.");
		}
	}
	
	public int save(){
		if (this.dao.saveToDB()) {
			return this.dao.getCodigo();
		} else {
			return -1;
		}
	}
	
	public void setCotacao(int codCotacao){
		dao.setCodCotacao(codCotacao);
	}
	
	public void setDados(String nome, String cpf, String sexo, int idade){
		dao.setNome(nome);
		dao.setCpf(cpf);
		dao.setSexo(sexo);
		dao.setIdade(idade);
	}
	
	public void setSituacao(String casado, String temFilho){
		dao.setCasado(casado);
		dao.setTemFilho(temFilho);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ 'codigo': '" + this.dao.getCodigo() + "'");
		sb.append(", 'codCotacao': '" + this.dao.getCodCotacao() + "'");
		sb.append(", 'nome': '" + this.dao.getNome() + "'");
		sb.append(", 'cpf': '" + this.dao.getCpf() + "'");
		sb.append(", 'sexo': '" + this.dao.getSexo() + "'");
		sb.append(", 'idade': '" + this.dao.getIdade() + "'");
		sb.append(", 'casado': '" + this.dao.getCasado() + "'");
		sb.append(", 'temFilho': '" + this.dao.getTemFilho() + "'");
		sb.append("  }");
		return sb.toString();
	}

	public int getIdade() {
		return this.dao.getIdade();
	}

	public boolean ehSolteiro() {
		return this.dao.getCasado().equals("N");
	}

	public boolean temNaoFilho() {
		return this.dao.getTemFilho().equals("N");
	}

	public String getSexo() {
		return this.dao.getSexo();
	}
	

}
