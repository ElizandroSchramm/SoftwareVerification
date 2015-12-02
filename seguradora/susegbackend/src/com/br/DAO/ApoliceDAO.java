package com.br.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApoliceDAO {
	private int codigo, codCotacao;
	
	public static ApoliceDAO loadFromDB(int codigoCotacao){
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT codigo FROM Apolice c WHERE c.codigoCotacao = ?");
				ps.setInt(1, codigoCotacao);
				ResultSet rs = ps.executeQuery();
					if(rs.next()){
						ApoliceDAO dao = new ApoliceDAO();
						dao.codigo = rs.getInt(1);
						dao.setCodCotacao(codigoCotacao);
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
					PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO Apolice (codigocotacao) VALUES( ? )", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(1, this.codCotacao);
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
			//TODO: Update para ap—lice foi cortado de escopo				
		}
		return true;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getCodCotacao() {
		return codCotacao;
	}

	public void setCodCotacao(int codCotacao) {
		this.codCotacao = codCotacao;
	}
}
