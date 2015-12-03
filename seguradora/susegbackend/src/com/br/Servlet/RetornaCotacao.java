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
 * Servlet implementation class RetornaCotacao
 */
@WebServlet("/RetornaCotacao")
public class RetornaCotacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetornaCotacao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		  // RetornaCotacao?codigo=
			int valor = -1;
			valor = Integer.parseInt(request.getParameter("codigo"));
			
			if (valor == -1){
				throw new Exception();				
			}
			Cotacao cotacao = new Cotacao(valor);
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(cotacao);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500, "Par�metro n�o informado.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
