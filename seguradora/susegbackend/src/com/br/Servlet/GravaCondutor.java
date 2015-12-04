package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.Model.Condutor;

/**
 * Servlet implementation class GravaCondutor
 */
@WebServlet("/GravaCondutor")
public class GravaCondutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GravaCondutor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//GravaCondutor?nome=Paulo&cpf=456.789.123-20&idade=27&sexo=M&temFilho=N&casado=S&cotacao=1
			String nome, cpf, sexo, casado, temFilho, codigo;
			int idade, cotacao;
			nome = request.getParameter("nome");
			cpf = request.getParameter("cpf");
			casado = request.getParameter("casado");
			temFilho = request.getParameter("temFilho");
			sexo = request.getParameter("sexo");
			idade = Integer.parseInt(request.getParameter("idade"));
			cotacao = Integer.parseInt(request.getParameter("cotacao"));
			codigo = request.getParameter("codigo");
			if(nome == null || cpf == null || sexo == null){
				throw new Exception();
			}
			Condutor condutor;
			if ((codigo == null) || (codigo == "")){
				condutor = new Condutor();				
			}else{
				condutor = new Condutor(Integer.parseInt(codigo));				
			}
			condutor.setCotacao(cotacao);
			condutor.setDados(nome, cpf, sexo, idade);
			condutor.setSituacao(casado, temFilho);
			condutor.save();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(condutor);
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
