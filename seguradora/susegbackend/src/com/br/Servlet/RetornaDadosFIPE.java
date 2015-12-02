package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.Model.FIPE;

/**
 * Servlet implementation class RetornaDadosFIPE
 */
@WebServlet("/RetornaDadosFIPE")
public class RetornaDadosFIPE extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetornaDadosFIPE() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RetornaDadosFIPE?acao=marcas
		//RetornaDadosFIPE?acao=veiculos&marca=Audi&anomodelo=2013
		//RetornaDadosFIPE?acao=veiculo&codigofipe=015065-7
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String acao = request.getParameter("acao");
		if(acao.equals("marcas")){
			//retorna JSON com as marcas da tabela FIPE
			out.print("{\"marcas\":" + FIPE.loadMarcas() + "}");		
		} else if(acao.equals("veiculos")){
			//retorna JSON com os ve’culos da tabela FIPE para uma marca de um ano de modelo
			String marca = request.getParameter("marca");
			int anoModelo = Integer.parseInt(request.getParameter("anomodelo"));
			out.print("{\"veiculos\":" + FIPE.loadVeiculos(marca, anoModelo) + "}");
		} else if(acao.equals("veiculo")){
			//retorna JSON com os dados do ve’culo
			//TODO: fazer s— se for necess‡rio
		}
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
