package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.Controle.CotacaoControle;
import com.br.Model.Cotacao;

/**
 * Servlet implementation class CalculaValoresPremio
 */
@WebServlet("/CalculaValoresPremio")
public class CalculaValoresPremio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculaValoresPremio() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//CalculaValoresPremio?codigoCotacao=1
			int codCotacao = Integer.parseInt(request.getParameter("codigoCotacao"));
			try {
				CotacaoControle controle = new CotacaoControle(new Cotacao(codCotacao));
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(controle.getValoresDetalhados());
				out.flush();
			} catch (Exception e) {
				response.sendError(500, e.getMessage());
			}
		} catch (Exception e) {
			response.sendError(500, "Parâmetro não informado.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
