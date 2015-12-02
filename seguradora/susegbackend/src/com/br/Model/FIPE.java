package com.br.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.br.DAO.DBConnection;

public class FIPE {
	
	public static JSONArray loadMarcas(){
		JSONArray marcas = new JSONArray();
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("select distinct(marca) marca FROM VeiculoFIPE");
				ResultSet rs = ps.executeQuery();
				JSONObject obj;
				while(rs.next()){
					obj = new JSONObject();
					obj.put("marca", rs.getString(1));
					marcas.add(obj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
		return marcas;
	}
	
	public static JSONArray loadVeiculos(String marca, int anoModelo){
		JSONArray veiculos = new JSONArray();
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("select codigofipe, nome FROM VeiculoFIPE v where v.marca = ? and v.anomodelo = ?");
				ps.setString(1, marca);
				ps.setInt(2, anoModelo);
				ResultSet rs = ps.executeQuery();
				JSONObject obj;
				while(rs.next()){
					obj = new JSONObject();
					obj.put("codigofipe", rs.getString(1));
					obj.put("nome", rs.getString(2));
					veiculos.add(obj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
		return veiculos;
	}

}
