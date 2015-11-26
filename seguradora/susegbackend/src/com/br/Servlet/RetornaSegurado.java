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

import com.br.Model.Condutor;
import com.br.Model.Segurado;

/**
 * Servlet implementation class RetornaSegurado
 */
@WebServlet("/RetornaSegurado")
public class RetornaSegurado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetornaSegurado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//codigoSegurado=1
			int codigo = -1;
			codigo = Integer.parseInt(request.getParameter("codigoSegurado"));
			if(codigo == -1){
				throw new Exception();
			}
			Segurado segurado = new Segurado(codigo);
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
