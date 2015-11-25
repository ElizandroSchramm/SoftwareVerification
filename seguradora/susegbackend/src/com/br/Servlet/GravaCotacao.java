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
 * Servlet implementation class GravaCotacao
 */
@WebServlet("/GravaCotacao")
public class GravaCotacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GravaCotacao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codCotacao = Integer.parseInt(request.getParameter("codigoCotacao"));
			double valor = Double.parseDouble(request.getParameter("valorCotacao"));
			int codSegurado = Integer.parseInt(request.getParameter("codSegurado"));
			int codLocalizacao = Integer.parseInt(request.getParameter("codLocalizacao"));
			try {
				Cotacao cotacao = new Cotacao();
				cotacao.setValor(valor);
				cotacao.setCodLocalizacao(codLocalizacao);				
				cotacao.setCodSegurado(codSegurado);
				cotacao.save();
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(cotacao);
				out.flush();
			} catch (Exception e) {
				response.sendError(500, e.getMessage());
			}
		} catch (Exception e) {
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
