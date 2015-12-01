package com.br.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VeiculoDAO {
	
	private int codigo = -1, anoFabricacao, anoModelo, mediaKMMes, cotacao;
	private String chassi, cor, modelo, placa, renavam, marca;
	private double valorFIP;
	
	public static VeiculoDAO loadFromDB(int codigo){
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT anofabricacao, anomodelo, chassi, cor, mediakmmes, modelo, placa, renavam, idcotacao, marca, valorfip FROM Veiculo v WHERE v.codigo = ?");
				ps.setInt(1, codigo);
				ResultSet rs = ps.executeQuery();
					if(rs.next()){
						VeiculoDAO dao = new VeiculoDAO();
						dao.codigo = codigo;
						dao.setAnoFabricacao(rs.getInt(1));
						dao.setAnoModelo(rs.getInt(2));
						dao.setChassi(rs.getString(3));
						dao.setCor(rs.getString(4));
						dao.setMediaKMMes(rs.getInt(5));
						dao.setModelo(rs.getString(6));
						dao.setPlaca(rs.getString(7));
						dao.setRenavam(rs.getString(8));
						dao.setCotacao(rs.getInt(9));
						dao.setMarca(rs.getString(10));
						dao.setValorFIP(rs.getDouble(11));
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
					PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO Veiculo(anofabricacao, anomodelo, chassi, cor, mediakmmes, modelo, placa, renavam, idcotacao, marca, valorfip) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(1, this.anoFabricacao);
					ps.setInt(2, this.anoModelo);
					ps.setString(3, this.chassi);
					ps.setString(4, this.cor);
					ps.setInt(5, this.mediaKMMes);
					ps.setString(6, this.modelo);
					ps.setString(7, this.placa);
					ps.setString(8, this.renavam);
					ps.setInt(9, this.cotacao);
					ps.setString(10, this.marca);
					ps.setDouble(11, this.valorFIP);
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
					PreparedStatement ps = db.getConnection().prepareStatement("UPDATE Veiculo set anofabricacao=?, anomodelo=?, chassi=?, cor=?, mediakmmes=?, modelo=?, placa=?, renavam=?, idcotacao=?, marca=?, valorfip=? WHERE codigo = ?", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(1, this.anoFabricacao);
					ps.setInt(2, this.anoModelo);
					ps.setString(3, this.chassi);
					ps.setString(4, this.cor);
					ps.setInt(5, this.mediaKMMes);
					ps.setString(6, this.modelo);
					ps.setString(7, this.placa);
					ps.setString(8, this.renavam);
					ps.setInt(9, this.cotacao);
					ps.setString(10, this.marca);
					ps.setDouble(11, this.valorFIP);
					ps.setInt(12, this.codigo);
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

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public int getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

	public int getMediaKMMes() {
		return mediaKMMes;
	}

	public void setMediaKMMes(int mediaKMMes) {
		this.mediaKMMes = mediaKMMes;
	}

	public int getCotacao() {
		return cotacao;
	}

	public void setCotacao(int cotacao) {
		this.cotacao = cotacao;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getCodigo() {
		return codigo;
	}

	public double getValorFIP() {
		return valorFIP;
	}

	public void setValorFIP(double valorFIP) {
		this.valorFIP = valorFIP;
	}

}
