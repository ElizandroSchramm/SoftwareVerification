package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.Model.Apolice;

/**
 * Servlet implementation class GravaApolice
 */
@WebServlet("/GravaApolice")
public class GravaApolice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GravaApolice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// /GravaApolice?codigoCotacao=1
		try {
			int codCotacao = -1; 
			codCotacao = Integer.parseInt(request.getParameter("codigoCotacao"));
			if (codCotacao == -1){
				throw new Exception();
			}
			Apolice apolice = new Apolice(codCotacao);
			apolice.save();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(apolice);
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
