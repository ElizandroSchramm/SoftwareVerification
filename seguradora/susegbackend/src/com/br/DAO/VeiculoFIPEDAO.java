package com.br.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VeiculoFIPEDAO {

	private String fipeCodigo, nome, marca, combustivel;
	private int anoModelo;
	private double preco;
	
	public VeiculoFIPEDAO(String fipeCodigo, int anoModelo, double preco, String nome, String marca, String combustivel) {
		this.fipeCodigo = fipeCodigo;
		this.nome = nome;
		this.marca = marca;
		this.combustivel = combustivel;
		this.anoModelo = anoModelo;
		this.preco = preco;
	}
	
	public VeiculoFIPEDAO(){
		super();
	}
	
	public void save() {
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO VeiculoFIPE(codigofipe, nome, marca, combustivel, anomodelo, preco) VALUES(?, ?, ?, ?, ?, ?)");
				ps.setString(1, this.fipeCodigo);
				ps.setString(2, this.nome);
				ps.setString(3, this.marca);
				ps.setString(4, this.combustivel);
				ps.setInt(5, anoModelo);
				ps.setDouble(6, this.preco);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
	}
	
	public static VeiculoFIPEDAO loadFromDB(String codigoFipe, int anoModelo){
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("select nome, marca, combustivel, preco FROM VeiculoFIPE v where v.codigofipe = ? and v.anomodelo = ?");
				ps.setString(1, codigoFipe);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					VeiculoFIPEDAO veiculo = new VeiculoFIPEDAO();
					veiculo.setFipeCodigo(codigoFipe);
					veiculo.setAnoModelo(anoModelo);
					veiculo.setNome(rs.getString(1));
					veiculo.setMarca(rs.getString(2));
					veiculo.setCombustivel(rs.getString(3));
					veiculo.setPreco(rs.getDouble(4));
					return veiculo;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
		return null;
	}

	public String getFipeCodigo() {
		return fipeCodigo;
	}

	public void setFipeCodigo(String fipeCodigo) {
		this.fipeCodigo = fipeCodigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public int getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
