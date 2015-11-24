package com.br.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class CotacaoDAO {
	
	private int codigo;
	private double comissao, valor;
	private Date dataCriacao, vigencia;
	
	public CotacaoDAO() {
		this.codigo = -1;
		Calendar cal = new GregorianCalendar();
		this.dataCriacao = new Date(new java.util.Date().getTime());		
		cal.add(Calendar.YEAR, 1); //defatul 1 ano
		this.vigencia = new Date(cal.getTime().getTime());
	}
	
	public static CotacaoDAO loadFromDB(int codigo){
		return null;
	}
	
	public boolean saveToDB(){
		if(this.codigo == -1){
			//aqui faz insert
			DBConnection db = new DBConnection();
			try {
				if(db.canExecuteCmd()){
					PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO Cotacao(comissao, dataCriacao, valor, vigencia) VALUES( ?, ?, ?, ? )");
					ps.setDouble(1, this.comissao);
					ps.setDate(2, (java.sql.Date) this.dataCriacao);
					ps.setDouble(3, this.valor);
					ps.setDate(4, (java.sql.Date) this.vigencia);
					// Execute the INSERT
					this.codigo = ps.executeUpdate();
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				db.FecharConexao();
			}
		} else {
			//aqui faz updade
		}
		return true;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getVigencia() {
		return vigencia;
	}

	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}

	public int getCodigo() {
		return codigo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

}
