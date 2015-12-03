package com.br.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.br.Model.Veiculo;

/**
 * Servlet implementation class GravaVeiculo
 */
@WebServlet("/GravaVeiculo")
public class GravaVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GravaVeiculo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//GravaVeiculo?anofabricacao=2013&anomodelo=2014&chassi=12345678&cor=Preto&mediakmmes=500&modelo=Celta&placa=ABC-1234&renavam=abc123&marca=GM&cotacao=1
			//GravaVeiculo?anofabricacao=2013&anomodelo=2014&chassi=12345678&cor=Preto&mediakmmes=500&codigofipe=008153-1&placa=ABC-1234&renavam=abc123&cotacao=1
			int anoFabricacao, anoModelo, mediaKMMes, cotacao;
			String chassi, cor, placa, renavam, codigoFipe;
			chassi = request.getParameter("chassi");
			cor = request.getParameter("cor");
			placa = request.getParameter("placa");
			renavam = request.getParameter("renavam");
			codigoFipe = request.getParameter("codigofipe");
			anoFabricacao = Integer.parseInt(request.getParameter("anofabricacao"));
			anoModelo = Integer.parseInt(request.getParameter("anomodelo"));
			mediaKMMes = Integer.parseInt(request.getParameter("mediakmmes"));
			cotacao= Integer.parseInt(request.getParameter("cotacao"));
			if(codigoFipe == null){
				throw new Exception();
			}
			Veiculo veiculo = new Veiculo(codigoFipe, anoModelo, anoFabricacao);
			veiculo.setCotacao(cotacao);
			veiculo.setRegistros(chassi, cor, placa, renavam, mediaKMMes);
			veiculo.save();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(veiculo);
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
		this.doGet(request, response);
	}

}
