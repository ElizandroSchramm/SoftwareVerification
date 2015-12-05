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
	
	public static String loadApolicesVencendo(int dias){
		StringBuilder sb = new StringBuilder();
		String xCmd1 = "SELECT a.codigo, c.valor, c.vigencia, s.nome, (CASE WHEN s.cpf is null THEN s.cnpj ELSE s.cpf END) AS cpf_cnpj" +
					   "  FROM Apolice a, Cotacao c, Segurado s" +
					   " WHERE s.codigo = c.codsegurado and c.codigo = a.codigocotacao and DateDiff(c.vigencia, Date(Now())) < ?";
		 
		//seleciona todas as cotações prestes a vencer já trazendo o segurado
		DBConnection db = new DBConnection();
		try {
			if(db.canExecuteCmd()){
				PreparedStatement ps = db.getConnection().prepareStatement(xCmd1);
				ps.setInt(1, dias);
				ResultSet rs = ps.executeQuery();
				int codigoApolice = 0;
				double valorCotacao = 0;
				Date vigencia = null;
				String nomeSegurado = "", cpf_cnpj = "";
				//cabeçalho
				sb.append("<!DOCTYPE html><html><body>");
				sb.append("<h2>Relatório com as apólices para vencer em 30 dias</h2><hr>");
				sb.append("<table border=1>");
				sb.append("<tr><td>Apólice</td>");
				sb.append("<td>Valor Prêmio</td>");
				sb.append("<td>Vigência</td>");
				sb.append("<td>Nome Segurado</td>");
				sb.append("<td>CPF/CNPJ Segurado</td></tr>");
				sb.append("<tr>");
				while(rs.next()){
					codigoApolice = rs.getInt(1);
					valorCotacao = rs.getDouble(2);
					vigencia = rs.getDate(3);
					nomeSegurado = rs.getString(4);
					cpf_cnpj = rs.getString(5);
					sb.append("<td>" + codigoApolice + "</td>");
					sb.append("<td>" + valorCotacao + "</td>");
					sb.append("<td>" + vigencia + "</td>");
					sb.append("<td>" + nomeSegurado + "</td>");
					sb.append("<td>" + cpf_cnpj + "</td>");
					sb.append("\n");
				}
				sb.append("</tr>");
				sb.append("</table><hr></body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
