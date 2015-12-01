package com.br.Controle;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
	
	private double getValorIdadeVeiculo(){
		Calendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date());
		int anosCarro = cal.get(Calendar.YEAR) - this.cotacao.getVeiculos().get(0).getAnoFabricacao(); //TODO: fazer guardar o valor aplicado em multa por veículos mais velhos que 10 anos para somar e retornar nos detalhes depois.
		if(anosCarro > 10){
			return this.calculaBasePremio() * 0.05; //TODO Sprint2 = fazer carregar da base
		} else {
			return 0;
		}
	}
	
	private double calculaIdadeVeiculo(double valorPremio){
		return valorPremio + getValorIdadeVeiculo();
	}
	
	private double getValorPerfilCondutor(){
		int percentualCondutor = this.getCondutorPiorPerfil().getPercentualCondutor();
		if(percentualCondutor > 0){
			return this.calculaBasePremio() * ((double)percentualCondutor / 100);
		} else {
			return 0;
		}
	}
	
	private double calculaPerfilCondutor(double valorPremio){
		return valorPremio + getValorPerfilCondutor();
	}
	
	private double calculaBasePremio(){
		double valorCarro = this.cotacao.getVeiculos().get(0).getValorFIP(); //TODO: ajustar para fazer um for e calcular a basePremio para cada veículo
		return valorCarro * 0.03; //TODO: confirmar se a base inicial é 3%		
	}
	
	public double calculaPremio(){
		double valorPremio = calculaBasePremio();
		valorPremio = calculaIdadeVeiculo(valorPremio);
		valorPremio = calculaPerfilCondutor(valorPremio);
		return valorPremio;
	}
	
	/**
	 * Valor base do prêmio:  R$1200,00 <--
	 * Condutor menor de 25:  R$410,00 <--
	 * Carro com mais de 10 anos:  R$200,00 <--
	 */
	public String getValoresDetalhados(){
		StringBuilder sb = new StringBuilder();
		//descrição valor
		sb.append("{\"valores\":[");
		sb.append("{\"descricao\":\"Valor base do prêmio\",\"valor\":\"" + calculaBasePremio() + "\"},");
		if(getValorPerfilCondutor() > 0){
			sb.append("{\"descricao\":\"Perfil do condutor\",\"valor\":\"" + getValorPerfilCondutor() + "\"},");
		}
		if(getValorIdadeVeiculo() > 0){
			sb.append("{\"descricao\":\"Carro com mais de 10 anos\",\"valor\":\"" + getValorIdadeVeiculo() + "\"},");
		}
		sb.append("{\"id\":\"1\",\"descricao\":\"Valor do prêmio\",\"valor\":\"" + calculaPremio() + "\"}");
		sb.append("]}");
		return sb.toString();
	}
	
	//TODO: gravar na base as cláusulas que o cara selecionou para a cotação. Sprint2, pois ele calcula no client o valor e grava
	//TODO: fazer os gets do percentual de cada cláusula. Sprint2, pois fica fixo para a 1
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
