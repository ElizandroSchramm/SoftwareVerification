package com.br.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static String status = "N‹o conectado.";
	private Connection connection = null;

	public DBConnection() {
		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			String serverName = "localhost";
			String dataBase = "suseg_banco";
			String url = "jdbc:mysql://" + serverName + "/" + dataBase;
			String username = "root";
			String password = ""; //
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
				status = "Conectado.";
			} else {
				status = "N‹o foi poss’vel conectar.";
			}
		} catch (ClassNotFoundException e) {
			System.out.println("O driver expecificado nao foi encontrado.");
		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
		}
	}
	
	public Connection getConnection(){
		return connection;
	}

	public String statusConection() {
		return status;
	}

	public boolean FecharConexao() {
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean canExecuteCmd() {
		boolean result = false;
		try {
			result = connection != null && !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

}
