package com.br.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class CotacaoDAO {
	
	private int codigo, codSegurado, codLocalizacao;
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
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT comissao, dataCriacao, valor, vigencia FROM Cotacao c WHERE c.codigo = ?");
				ps.setInt(1, codigo);
				ResultSet rs = ps.executeQuery();
					if(rs.next()){
						CotacaoDAO dao = new CotacaoDAO();
						dao.codigo = codigo;
						dao.setComissao(rs.getDouble(1));
						dao.dataCriacao = rs.getDate(2);
						dao.setValor(rs.getDouble(3));
						dao.setVigencia(rs.getDate(4));
						//TODO: resta fazer o get das demais dados como Veiculo, Segurado...
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
					PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO Cotacao(comissao, dataCriacao, valor, vigencia, codsegurado, codlocalizacao) VALUES( ?, ?, ?, ?, ?, ? )", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setDouble(1, this.comissao);
					ps.setDate(2, (java.sql.Date) this.dataCriacao);
					ps.setDouble(3, this.valor);
					ps.setDate(4, (java.sql.Date) this.vigencia);
					ps.setInt(5, this.codSegurado);
					ps.setInt(6, this.codLocalizacao);
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

	public int getCodLocalizacao() {
		return codLocalizacao;
	}

	public void setCodLocalizacao(int codLocalizacao) {
		this.codLocalizacao = codLocalizacao;
	}

	public int getCodSegurado() {
		return codSegurado;
	}

	public void setcodSegurado(int codSegurado) {
		this.codSegurado = codSegurado;
	}

}
