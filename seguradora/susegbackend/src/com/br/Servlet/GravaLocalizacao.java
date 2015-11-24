package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.Model.Localizacao;

/**
 * Servlet implementation class GravaLocalizacao
 */
@WebServlet("/GravaLocalizacao")
public class GravaLocalizacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GravaLocalizacao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//numero=123&rua=teste&cep=89110-000&cidade=Blumenau&estado=SC&pais=Brasil
			int numero = Integer.parseInt(request.getParameter("numero"));
			String rua, cep, cidade, estado, pais;
			rua = request.getParameter("rua");
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			estado = request.getParameter("estado");
			pais = request.getParameter("pais");
			if(rua == null || cep == null || cidade == null || estado == null || pais == null){
				throw new Exception();
			}
			Localizacao local = new Localizacao();
			local.setEndereco(rua, numero, cep);
			local.setLocal(cidade, estado, pais);
			local.save();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(local);
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
