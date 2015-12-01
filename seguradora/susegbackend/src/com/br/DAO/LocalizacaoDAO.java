package com.br.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalizacaoDAO {
	
	private String cep, cidade, estado, pais, rua;
	private int codigo = -1, numero;
	
	public boolean saveToDB(){
		if(this.codigo == -1){
			//aqui faz insert
			DBConnection db = new DBConnection();
			try {
				if(db.canExecuteCmd()){
					PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO Localizacao(cidade, estado, numero, pais, rua, cep) VALUES( ?, ?, ?, ?, ?, ? )", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.cidade);
					ps.setString(2, this.estado);
					ps.setInt(3, this.numero);
					ps.setString(4, this.pais);
					ps.setString(5, this.rua);
					ps.setString(6, this.cep);
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
					PreparedStatement ps = db.getConnection().prepareStatement("UPDATE Localizacao set cidade=?, estado=?, numero=?, pais=?, rua=?, cep=? WHERE codigo = ?", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.cidade);
					ps.setString(2, this.estado);
					ps.setInt(3, this.numero);
					ps.setString(4, this.pais);
					ps.setString(5, this.rua);
					ps.setString(6, this.cep);
					ps.setInt(7, this.codigo);
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
	
	public static LocalizacaoDAO loadFromDB(int codigo) {
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT cidade, estado, numero, pais, rua, cep FROM Localizacao l WHERE l.codigo = ?");
				ps.setInt(1, codigo);
				ResultSet rs = ps.executeQuery();
					if(rs.next()){
						LocalizacaoDAO dao = new LocalizacaoDAO();
						dao.codigo = codigo;
						dao.setCidade(rs.getString(1));
						dao.setEstado(rs.getString(2));
						dao.setNumero(rs.getInt(3));
						dao.setPais(rs.getString(4));
						dao.setRua(rs.getString(5));
						dao.setCep(rs.getString(6));
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
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	

}
