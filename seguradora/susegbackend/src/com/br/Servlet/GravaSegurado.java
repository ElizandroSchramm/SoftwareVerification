package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.br.Model.Segurado;

/**
 * Servlet implementation class GravaSegurado
 */
@WebServlet("/GravaSegurado")
public class GravaSegurado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//GravaSegurado?nome=Paulo&cpf=456.789.123-20&dataNascimento=08%2F09%2F1988&telefone=3332-3344&sexo=M&cnpj=2345678&ie=111222333&bonus=2
			//GravaSegurado?nome=Paulo&cpf=456.789.123-20&dataNascimento=08%2F09%2F1988&telefone=3332-3344&sexo=M&cnpj=2345678&ie=111222333&bonus=2
			String codigo, nome, cpf, telefone = null, sexo = null, classeBonus, cnpj, ie = null;
			Date dataNascimento = null;
			int bonus = 0;
			codigo = request.getParameter("codigo");
			nome = request.getParameter("nome");
			cpf = request.getParameter("cpf");
			cnpj  = request.getParameter("cnpj");
			if(cpf != null){
				telefone = request.getParameter("telefone");
				sexo = request.getParameter("sexo");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				dataNascimento = new Date(sdf.parse(request.getParameter("dataNascimento")).getTime()); 
			} else if(cnpj != null){
				ie = request.getParameter("ie");
			}
			classeBonus = request.getParameter("bonus");
			if(classeBonus != null){
				bonus = Integer.parseInt(classeBonus);
			}
			//trata os par�metros obrigat�rios
			if(nome == null || (cpf == null && cnpj == null)){
				throw new Exception();
			}

			Segurado segurado;
			if (codigo == null){
				segurado = new Segurado();
			}else{
				segurado = new Segurado(Integer.parseInt(codigo));
			}			
			segurado.setDados(nome, cpf, sexo, dataNascimento, bonus, cnpj, ie);
			segurado.setContato(telefone);
			segurado.save();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(segurado);
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(500, "Par�metro n�o informado.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
