package com.br.Controle;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.br.Model.Condutor;
import com.br.Model.Cotacao;
import com.br.Model.Veiculo;

public class CotacaoControle {
	
	private Cotacao cotacao;
	private double valoresPelaIdade = 0;
	
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
	
	private double getValorIdadeVeiculo(Veiculo veiculo){
		Calendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date());
		int anosCarro = cal.get(Calendar.YEAR) - veiculo.getAnoFabricacao();
		if(anosCarro > 10){
			double valor = this.calculaBasePremio() * 0.05; //TODO Sprint2 = fazer carregar da base o percentual
			this.valoresPelaIdade += valor;
			return valor;
		} else {
			return 0;
		}
	}
	
	private double calculaIdadeVeiculos(double valorPremio){
		for (Veiculo veiculo : this.cotacao.getVeiculos()) {
			valorPremio += this.getValorIdadeVeiculo(veiculo);
		}
		return valorPremio;
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
	
	private double calculaBasePremio(Veiculo veiculo){
		double valorCarro = veiculo.getValorFIP();
		return valorCarro * 0.03; //TODO: confirmar se a base inicial ÔøΩ 3%		
	}
	
	private double calculaBasePremio(){
		double valorPremio = 0;
		for (Veiculo veiculo : this.cotacao.getVeiculos()) {
			valorPremio += calculaBasePremio(veiculo);
		}
		return valorPremio;
	}
	
	public double calculaPremio(){
		double valorPremio = calculaBasePremio();
		valorPremio = calculaIdadeVeiculos(valorPremio);
		valorPremio = calculaPerfilCondutor(valorPremio);
		return valorPremio;
	}
	
	public double calculaFranquia(double valorPremio){
		return valorPremio * 1.1; //nossa franquia normal é 110% do valor do prêmio
	}
	
	/**
	 * Valor base do prêmio:  R$1200,00 <--
	 * Condutor menor de 25:  R$410,00 <--
	 * Carro com mais de 10 anos:  R$200,00 <--
	 * Franquia: R$1500,00 
	 */
	public String getValoresDetalhados(){
		StringBuilder sb = new StringBuilder();
		double valorPremio = this.calculaPremio();
		//descrição valor
		sb.append("{\"valores\":[");
		sb.append("{\"descricao\":\"Valor base do prêmio\",\"valor\":\"" + this.calculaBasePremio() + "\"},");
		if(getValorPerfilCondutor() > 0){
			sb.append("{\"descricao\":\"Perfil do condutor\",\"valor\":\"" + this.getValorPerfilCondutor() + "\"},");
		}
		if(this.valoresPelaIdade > 0){
			String p = this.cotacao.getVeiculos().size() > 1 ? "Carros" : "Carro";
			sb.append("{\"descricao\":\"" + p + " com mais de 10 anos\",\"valor\":\"" + this.valoresPelaIdade + "\"},");
		}
		sb.append("{\"id\":\"1\",\"descricao\":\"Valor do prêmio\",\"valor\":\"" + valorPremio + "\"},");
		sb.append("{\"id\":\"2\",\"descricao\":\"Valor da franquia\",\"valor\":\"" + this.calculaFranquia(valorPremio) + "\"}");
		sb.append("]}");
		return sb.toString();
	}
	
	//TODO: gravar na base as clausulas que o cara selecionou para a cotacao. Sprint2, pois ele calcula no client o valor e grava
	//TODO: fazer os gets do percentual de cada clausula. Sprint2, pois fica fixo para a 1
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
