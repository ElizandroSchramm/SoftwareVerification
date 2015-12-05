package com.br.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RelatorioDAO {
	
//	private String xHTML = "<!DOCTYPE html><html><body>
//<h2>Relatório com as apólices para vencer em 30 dias</h2><hr>
//<table border=1>
//  <tr>
//    <td>Apólice</td>
//    <td>Valor Prêmio</td>
//    <td>Vigência</td>
//    <td>Nome Segurado</td>
//    <td>CPF/CNPJ Segurado</td>
//  </tr>
//  <tr>
//    <td></td>
//    <td></td>
//    <td></td>
//    <td></td>
//    <td></td>
//  </tr>
//</table><hr></body></html>
//"
	
	private static String loadVeiculos(int cotacao){
		StringBuilder sb = new StringBuilder();
		String xCmd1 = "SELECT modelo, anomodelo FROM Veiculo WHERE idcotacao = ?";
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement(xCmd1);
				ps.setInt(1, cotacao);
				ResultSet rs = ps.executeQuery();
				int anoModelo = 0;
				String modelo = "";
				sb.append("<table border=1>");
				sb.append("<tr><td>Modelo</td>");
				sb.append("<td>Ano Modelo</td></tr>");
				while(rs.next()){
					sb.append("<tr>");
					modelo = rs.getString(1);
					anoModelo = rs.getInt(2);
					sb.append("<td>" + modelo + "</td>");
					sb.append("<td>" + anoModelo + "</td>");
					sb.append("</tr>");
				}
				sb.append("</table>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String loadApolicesVencendo(int dias){
		StringBuilder sb = new StringBuilder();
		String xCmd1 = "SELECT a.codigo, c.valor, c.vigencia, s.nome, (CASE WHEN s.cpf is null THEN s.cnpj ELSE s.cpf END) AS cpf_cnpj, c.codigo AS cotacao" +
					   "  FROM Apolice a, Cotacao c, Segurado s" +
					   " WHERE s.codigo = c.codsegurado and c.codigo = a.codigocotacao and DateDiff(c.vigencia, Date(Now())) < ?";
		 
		//seleciona todas as cotações prestes a vencer já trazendo o segurado
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement(xCmd1);
				ps.setInt(1, dias);
				ResultSet rs = ps.executeQuery();
				int codigoApolice = 0, codigoCotacao;
				double valorCotacao = 0;
				Date vigencia = null;
				String nomeSegurado = "", cpf_cnpj = "";
				//cabeçalho
				sb.append("<!DOCTYPE html><html><body>");				
				sb.append("<h2>Relatório com as apólices para vencer em " + dias + " dias</h2><hr>");
				sb.append("<link href='assets/css/gsdk-base.css' rel='stylesheet' />");
				sb.append("<link href='assets/css/bootstrap.min.css' rel='stylesheet' />");
				sb.append("<div id='no-more-tables'>");
				sb.append("<table border=1 class='col-md-10 col-sm-offset-1 table-bordered table-striped table-condensed cf'>");
				sb.append("<thead class='cf'>");
				sb.append("<tr><td>Apólice</td>");
				sb.append("<td>Valor Prêmio</td>");
				sb.append("<td>Vigência</td>");
				sb.append("<td>Nome Segurado</td>");
				sb.append("<td>CPF/CNPJ Segurado</td>");
				sb.append("<td>Veículo(s)</td></tr>");
				sb.append("</thead>");
				while(rs.next()){
					sb.append("<tr>");
					codigoApolice = rs.getInt(1);
					valorCotacao = rs.getDouble(2);
					vigencia = rs.getDate(3);
					nomeSegurado = rs.getString(4);
					cpf_cnpj = rs.getString(5);
					codigoCotacao = rs.getInt(6);
					sb.append("<td>" + codigoApolice + "</td>");
					sb.append("<td>" + valorCotacao + "</td>");
					sb.append("<td>" + vigencia + "</td>");
					sb.append("<td>" + nomeSegurado + "</td>");
					sb.append("<td>" + cpf_cnpj + "</td>");
					//carregar os veículos
					sb.append("<td>");
					sb.append(RelatorioDAO.loadVeiculos(codigoCotacao));
					sb.append("</td>");
					sb.append("</tr>");
				}
				sb.append("</table><hr></body></html>");
				sb.append("</div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
