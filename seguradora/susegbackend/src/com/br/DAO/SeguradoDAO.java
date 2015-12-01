package com.br.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.br.Model.Veiculo;

public class SeguradoDAO {
	
	private int codigo = -1;
	private int classeBonus;
	private String nome, cpf, sexo, telefone, cnpj, inscricaoEstadual;
	private Date dataNascimento;
	
	public static SeguradoDAO loadFromDB(int codigo){
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT nome, cpf, sexo, telefone, dataNascimento, classebonus, cnpj, inscricaoestadual FROM Segurado s WHERE s.codigo = ?");
				ps.setInt(1, codigo);
				ResultSet rs = ps.executeQuery();
					if(rs.next()){
						SeguradoDAO dao = new SeguradoDAO();
						dao.codigo = codigo;
						dao.setNome(rs.getString(1));
						dao.setCpf(rs.getString(2));
						dao.setSexo(rs.getString(3));
						dao.setTelefone(rs.getString(4));
						dao.setDataNascimento(rs.getDate(5));
						dao.setClasseBonus(rs.getInt(6));
						dao.setCnpj(rs.getString(7));
						dao.setInscricaoEstadual(rs.getString(8));
						return dao;
					} else {
						return null;
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			db.FecharConexao();
		}
		return null;
	}
	
	public static List<Integer> loadFromDB(String where){
		List<Integer> cotacoes = new ArrayList<Integer>();
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT codigo FROM Segurado s WHERE " + where);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					//SeguradoDAO dao = new SeguradoDAO();
					cotacoes.add(rs.getInt(1));									
				}		
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			db.FecharConexao();
		}
		return cotacoes;
	}	
	
	public boolean saveToDB(){
		if(this.codigo == -1){
			//aqui faz insert
			DBConnection db = new DBConnection();
			try {
				if(db.canExecuteCmd()){
					PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO Segurado(nome, cpf, sexo, telefone, dataNascimento, classebonus, cnpj, inscricaoestadual) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.nome);
					ps.setString(2, this.cpf);
					ps.setString(3, this.sexo);
					ps.setString(4, this.telefone);
					ps.setDate(5, this.dataNascimento);
					ps.setInt(6, this.classeBonus);
					ps.setString(7, this.cnpj);
					ps.setString(8, this.inscricaoEstadual);
					// Execute the INSERT
					if(ps.executeUpdate() > 0){
						ResultSet rs = ps.getGeneratedKeys();
						if(rs.next()){
							this.codigo = rs.getInt(1);
							return true;
						}
					}
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				db.FecharConexao();
			}
		} else {
			//TODO: fazer update
			//aqui faz updade
		}
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setClasseBonus(int bonus){
		this.classeBonus = bonus;
	}
	
	public int getClasseBonus(){
		return this.classeBonus;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	

}
