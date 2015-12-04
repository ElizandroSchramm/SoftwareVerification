package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.Model.Clausula;
import com.br.Model.Cotacao;

/**
 * Servlet implementation class AdicionaClausula
 */
@WebServlet("/AdicionaClausula")
public class AdicionaClausula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionaClausula() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//AdicionaClausula?cotacao=68&tipo=Carro reserva 7
			int cotacao = Integer.parseInt(request.getParameter("cotacao"));
			String tipoClausula = request.getParameter("tipo");
			if(tipoClausula == null){
				response.sendError(500, "Parâmetro tipo da clausula não informado.");
			} else {
				try {
					Clausula clausula = new Clausula(tipoClausula);
					Cotacao cotacaoobj = new Cotacao(cotacao);
					cotacaoobj.addClausula(clausula);
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.println(cotacaoobj.clausulasToString());
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
					response.sendError(500, e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
