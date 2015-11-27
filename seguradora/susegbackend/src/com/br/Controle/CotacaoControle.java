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
		double baseValorPremio = valorCarro * 0.03; //TODO: confirmar se a base inicial é 3%
		double valorPremio = baseValorPremio;
		if(percentualCondutor > 0){
			valorPremio = baseValorPremio + (baseValorPremio * ((double)percentualCondutor / 100));
		}
		//Veículo com mais de 10 anos.
		return valorPremio;
	}
	
	//TODO: implementar o getDetalhesValores, que deve retornar um JSON com 
	/**
	 * Valor base do prêmio:  R$1200,00 <--
	 * Condutor menor de 25:  R$410,00 <--
	 * Carro com mais de 10 anos:  R$200,00 <--
	 */
	
	//TODO: gravar na base as cláusulas que o cara selecionou para a cotação. Sprint2, pois ele calcula no client o valor e grava
	//TODO: fazer os gets do percentual de cada cláusula. Sprint2, pois fica fixo para a 1

}
