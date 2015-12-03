package com.br.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClausulaDAO {

	
	//carro reserva 7 dias = INSERT INTO Clausula(tipo, descricao) VALUES('Carro reserva 7', 'Carro reserva 7 dias')
	//carro reserva 15 dias = INSERT INTO Clausula(tipo, descricao) VALUES('Carro reserva 15', 'Carro reserva 15 dias')
	//cobertura vidros = INSERT INTO Clausula(tipo, descricao) VALUES('Vidros', 'Cobertura vidros')
	//franquia reduzida = INSERT INTO Clausula(tipo, descricao) VALUES('Franquia', 'Franquia reduzida')
	//franquia 50% = INSERT INTO Clausula(tipo, descricao) VALUES('Franquia 50', 'Franquia reduzida em 50%')
	//serviços 24hs = INSERT INTO Clausula(tipo, descricao) VALUES('Serviços', 'Serviços 24hs')
	
	private int codigo;
	private String tipo, descricao;
	
	public static ClausulaDAO loadFromDB(int codigo){
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT tipo, descricao FROM Clausula where codigo = ?");
				ps.setInt(1, codigo);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					ClausulaDAO clausula = new ClausulaDAO();
					clausula.setCodigo(codigo);
					clausula.setTipo(rs.getString(1));
					clausula.setDescricao(rs.getString(2));
					return clausula;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
		return null;
	}
	
	public static ClausulaDAO loadFromDB(String tipo){
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT codigo, descricao FROM Clausula where tipo = ?");
				ps.setString(1, tipo);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					ClausulaDAO clausula = new ClausulaDAO();
					clausula.setCodigo(rs.getInt(1));
					clausula.setDescricao(rs.getString(2));
					return clausula;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
		return null;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
