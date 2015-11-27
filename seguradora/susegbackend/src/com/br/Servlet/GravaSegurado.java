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
     * @see HttpServlet#HttpServlet()
     */
    public GravaSegurado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//nome=Paulo&cpf=456.789.123-20&dataNascimento=08%2F09%2F1988&telefone=3332-3344&sexo=M
			String nome, cpf, telefone, sexo, classeBonus;
			Date dataNascimento;
			nome = request.getParameter("nome");
			cpf = request.getParameter("cpf");
			telefone = request.getParameter("telefone");
			sexo = request.getParameter("sexo");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataNascimento = new Date(sdf.parse(request.getParameter("dataNascimento")).getTime()); 
			classeBonus = request.getParameter("bonus");
			if(nome == null || cpf == null || telefone == null || sexo == null || dataNascimento == null){
				throw new Exception();
			}
			Segurado segurado = new Segurado();
			segurado.setDados(nome, cpf, sexo, dataNascimento, Integer.parseInt(classeBonus));
			segurado.setContato(telefone);
			segurado.save();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(segurado);
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(500, "Par‰metro n‹o informado.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
