package com.br.Controle;

import com.br.Model.Condutor;
import com.br.Model.Cotacao;

public class CotacaoControle {
	
	private Cotacao cotacao;
	
	public CotacaoControle(Cotacao cotacao){
		this.cotacao = cotacao;
	}
	
	public Condutor getCondutorPiorPerfil(){
		int punicao = 0, indice = 0;
		Condutor condutor;
		for (int i = 0; i < this.cotacao.getCondutores().size(); i++) {
			condutor = this.cotacao.getCondutores().get(i);
			if(condutor.getPercentualCondutor() > punicao){
				punicao = condutor.getPercentualCondutor();
				indice = i;
			}
		}
		return this.cotacao.getCondutores().get(indice);
	}
	
	public double calculaPremio(){
		double valorCarro = this.cotacao.getValorFIPVeiculo();
		int percentualCondutor = this.getCondutorPiorPerfil().getPercentualCondutor();
		double baseValorPremio = valorCarro * 0.03; //TODO: confirmar se a base inicial Ž 3%
		double valorPremio = baseValorPremio;
		if(percentualCondutor > 0){
			valorPremio = baseValorPremio + (baseValorPremio * ((double)percentualCondutor / 100));
		}
		return valorPremio;
	}

}
