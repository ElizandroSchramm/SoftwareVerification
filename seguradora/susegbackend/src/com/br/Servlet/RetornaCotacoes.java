package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.Model.Cotacao;
import com.br.Model.Segurado;

/**
 * Servlet implementation class RetornaCotacoes
 */
@WebServlet("/RetornaCotacoes")
public class RetornaCotacoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetornaCotacoes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		  // RetornaCotacoes?cpf=	
		  // RetornaCotacoes?cnpj=
			String cnpj, cpf = "";
			cnpj = request.getParameter("cnpj");
			cpf = request.getParameter("cpf");
			Segurado segurado = new Segurado();
			if (cpf != null){
				segurado.loadByCPF(cpf);
				
			} else {
				if (cnpj != null){
					segurado.loadByCNPJ(cnpj);					
				}
			}
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(segurado.getCotacoes());
			out.flush();
		} catch (Exception e) {
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
