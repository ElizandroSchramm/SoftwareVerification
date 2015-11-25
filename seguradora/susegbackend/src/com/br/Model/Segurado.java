package com.br.Model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.br.DAO.SeguradoDAO;

public class Segurado {
	
	private SeguradoDAO dao;
	
	public Segurado() {
		this.dao = new SeguradoDAO();
	}
	
	public Segurado(int codigo){
		this.dao = SeguradoDAO.loadFromDB(codigo);
	}
	
	public void setDados(String nome, String cpf, String sexo, Date dataNascimento){
		dao.setNome(nome);
		dao.setCpf(cpf);
		dao.setSexo(sexo);
		dao.setDataNascimento(dataNascimento);
	}
	
	public void setContato(String telefone){
		dao.setTelefone(telefone);
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
		sb.append("{ 'codigo': '" + this.dao.getCodigo() + "'");
		sb.append(", 'nome': '" + this.dao.getNome() + "'");
		sb.append(", 'cpf': '" + this.dao.getCpf() + "'");
		sb.append(", 'sexo': '" + this.dao.getSexo() + "'");
		sb.append(", 'telefone': '" + this.dao.getTelefone() + "'");
		sb.append(", 'dataNascimento': '" + (new SimpleDateFormat("dd/MM/yyyy")).format(dao.getDataNascimento()) + "'");
		sb.append("  }");
		return sb.toString();
	}

}
