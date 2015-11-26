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
			throw new Exception("Condutor não encontrada.");
		}
	}
	
	public Condutor(CondutorDAO dao){
		this.dao = dao;
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
	
	/**
	 * Calcula o % de acréscimo ao seguro devido ao perfil do condutor considerando:
	 * 	Se tiver menos de 26 anos, então pune em 15%, não somando nenhum % além desse.
	 * 	Se for homem maior que 25 anos, então pune em 5%, não somando nenhum % além desse.
	 * 	Se for casado(a), então reduz 2% de uma punição que houver.
	 * 	Se ter filho, então reduz 2% de uma punição que houver.
	 */
	public int getPercentualCondutor(){
		int punicao = 0;
		if(this.getIdade() <= 25){
			punicao = 15;
		} else if(this.getSexo().equals("M")){
			punicao = 5;
		}
		if(punicao > 0){
			if(!this.ehSolteiro()) {
				punicao -= 2;
			}
			if(!this.naoTemFilho()){
				punicao -=2;
			}
		}
		return punicao;
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

	public boolean naoTemFilho() {
		return this.dao.getTemFilho().equals("N");
	}

	public String getSexo() {
		return this.dao.getSexo();
	}
	

}
