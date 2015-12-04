package com.br.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;



public class CotacaoDAO {
	
	private int codigo, codSegurado, codLocalizacao;
	private double comissao, valor;
	private Date dataCriacao, vigencia;
	private List<Integer> condutores;
	private List<Integer> veiculos;
	private List<Integer> clausulas;
	
	public CotacaoDAO() {
		this.codigo = -1;
		Calendar cal = new GregorianCalendar();
		this.dataCriacao = new Date(new java.util.Date().getTime());		
		cal.add(Calendar.YEAR, 1); //defatul 1 ano
		this.vigencia = new Date(cal.getTime().getTime());
	}
	
	public static CotacaoDAO loadFromDB(int codigo){
		CotacaoDAO dao = null;
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT comissao, dataCriacao, valor, vigencia, codsegurado, codlocalizacao FROM Cotacao c WHERE c.codigo = ?");
				ps.setInt(1, codigo);
				ResultSet rs = ps.executeQuery();
					if(rs.next()){
						dao = new CotacaoDAO();
						dao.codigo = codigo;
						dao.setComissao(rs.getDouble(1));
						dao.dataCriacao = rs.getDate(2);
						dao.setValor(rs.getDouble(3));
						dao.setVigencia(rs.getDate(4));
						dao.setcodSegurado(rs.getInt(5));
						dao.setCodLocalizacao(rs.getInt(6));
					} else {
						return null;
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
		if(dao != null){
			dao.loadCondutoresFromDB();
			dao.loadVeiculosFromDB();
			dao.loadClausulasFromDB();
		}
		return dao;
	}
	
	public boolean saveToDB(){
		if(this.codigo == -1){
			//aqui faz insert
			DBConnection db = new DBConnection();
			try {
				if(db.canExecuteCmd()){
					PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO Cotacao(comissao, dataCriacao, valor, vigencia) VALUES( ?, ?, ?, ? )", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setDouble(1, this.comissao);
					ps.setDate(2, (java.sql.Date) this.dataCriacao);
					ps.setDouble(3, this.valor);
					ps.setDate(4, (java.sql.Date) this.vigencia);
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
			DBConnection db = new DBConnection();
			try {
				if(db.canExecuteCmd()){
					PreparedStatement ps = db.getConnection().prepareStatement("UPDATE Cotacao set comissao=?, dataCriacao=?, valor=?, vigencia=?, codsegurado=?, codlocalizacao=? WHERE codigo = ?", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setDouble(1, this.comissao);
					ps.setDate(2, (java.sql.Date) this.dataCriacao);
					ps.setDouble(3, this.valor);
					ps.setDate(4, (java.sql.Date) this.vigencia);
					ps.setInt(5, this.codSegurado);
					ps.setInt(6, this.codLocalizacao);
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
	
	private void loadCondutoresFromDB(){
		this.condutores = new ArrayList<Integer>();
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT codigo FROM Condutor WHERE CodCotacao = ?");
				ps.setInt(1, this.codigo);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					this.condutores.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
	}
	
	private void loadVeiculosFromDB(){
		this.veiculos = new ArrayList<Integer>();
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT codigo FROM Veiculo WHERE idcotacao = ?");
				ps.setInt(1, this.codigo);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					this.veiculos.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
	}
	
	private void loadClausulasFromDB(){
		this.clausulas = new ArrayList<Integer>();
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT codclau FROM ItemClausula where codcot = ?");
				ps.setInt(1, this.codigo);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					this.clausulas.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
	}
	
	public void addClausula(ClausulaDAO clausula) {
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO ItemClausula(codCot, codClau) values(?, ?)");
				ps.setInt(1, this.codigo);
				ps.setInt(2, clausula.getCodigo());
				// Execute the INSERT
				ps.executeUpdate();
				this.clausulas.add(clausula.getCodigo());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.FecharConexao();
		}
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

	public List<Integer> getCondutores() {
		return this.condutores;
	}
	
	public List<Integer> getVeiculos() {
		return this.veiculos;
	}
	
	public List<Integer> getClausulas() {
		return this.clausulas;
	}

}
