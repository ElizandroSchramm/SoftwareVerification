package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.Model.Cotacao;

/**
 * Servlet implementation class RetornaCondutores
 */
@WebServlet("/RetornaCondutores")
public class RetornaCondutores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetornaCondutores() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// RetornaCondutores?codigo=
		try {
			int codigo = -1;
			codigo = Integer.parseInt(request.getParameter("codigo"));
			
			if (codigo == -1){
				throw new Exception();				
			}
			Cotacao cotacao = new Cotacao(codigo);			
			
			PrintWriter out = response.getWriter();
			out.println(cotacao.condutoresToString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500, "Parâmetro não informado.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
