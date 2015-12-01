package com.br.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CondutorDAO {

	private int codigo = -1, codCotacao, idade;
	private String nome, cpf, sexo, casado, temFilho;
	
	public static CondutorDAO loadFromDB(int codigo){
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT codCotacao, nome, idade, cpf, sexo, casado, temFilho FROM Condutor c WHERE c.codigo = ?");
				ps.setInt(1, codigo);
				ResultSet rs = ps.executeQuery();
					if(rs.next()){
						CondutorDAO dao = new CondutorDAO();
						dao.codigo = codigo;
						dao.setCodCotacao(rs.getInt(1));
						dao.setNome(rs.getString(2));
						dao.setIdade(rs.getInt(3));
						dao.setCpf(rs.getString(4));
						dao.setSexo(rs.getString(5));
						dao.setCasado(rs.getString(6));
						dao.setTemFilho(rs.getString(7));
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
	
	public boolean saveToDB(){
		if(this.codigo == -1){
			//aqui faz insert
			DBConnection db = new DBConnection();
			try {
				if(db.canExecuteCmd()){
					PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO Condutor(codCotacao, nome, idade, cpf, sexo, casado, temFilho) VALUES( ?, ?, ?, ?, ?, ?, ? )", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(1, this.codCotacao);
					ps.setString(2, this.nome);
					ps.setInt(3, this.idade);
					ps.setString(4, this.cpf);
					ps.setString(5, this.sexo);
					ps.setString(6, this.casado);
					ps.setString(7, this.temFilho);
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
			//aqui faz updade
			//TODO: TESTAR
			DBConnection db = new DBConnection();
			try {
				if(db.canExecuteCmd()){
					PreparedStatement ps = db.getConnection().prepareStatement("UPDATE Condutor set codCotacao=?, nome=?, idade=?, cpf=?, sexo=?, casado=?, temFilho=? WHERE codigo = ?", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(1, this.codCotacao);
					ps.setString(2, this.nome);
					ps.setInt(3, this.idade);
					ps.setString(4, this.cpf);
					ps.setString(5, this.sexo);
					ps.setString(6, this.casado);
					ps.setString(7, this.temFilho);
					ps.setInt(8, this.codigo);
					// Execute the UPDATE
					ps.executeUpdate();
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				db.FecharConexao();
			}				
		}
		return true;
	}

	public int getCodCotacao() {
		return codCotacao;
	}

	public void setCodCotacao(int codCotacao) {
		this.codCotacao = codCotacao;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
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

	public String getCasado() {
		return casado;
	}

	public void setCasado(String casado) {
		this.casado = casado;
	}

	public String getTemFilho() {
		return temFilho;
	}

	public void setTemFilho(String temFilho) {
		this.temFilho = temFilho;
	}

	public int getCodigo() {
		return codigo;
	}

	
}
