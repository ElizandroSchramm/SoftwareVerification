package com.br.Controle;

import java.util.ArrayList;
import java.util.List;

import com.br.Model.Condutor;
import com.br.Model.Cotacao;

public class CotacaoControle {
	
	private Cotacao cotacao;
	
	public CotacaoControle(Cotacao cotacao){
		this.cotacao = cotacao;
	}
	
	public Condutor getCondutorPiorPerfil(){
		//separa os homens das mulheres
		List<Condutor> homens = new ArrayList<Condutor>();
		List<Condutor> mulheres = new ArrayList<Condutor>();
		for (Condutor condutor : this.cotacao.getCondutores()) {
			if(condutor.getSexo() == "M"){
				homens.add(condutor);
			} else {
				mulheres.add(condutor);
			}
		}
		
		//seleciona o condutor com menos de 25 anos
		for (Condutor condutor : homens) {
			if(condutor.getIdade() <= 25){
				return condutor;
			}
		}
		
		//seleciona a condutora com menos de 25 anos
		for (Condutor condutor : mulheres) {
			if(condutor.getIdade() <= 25){
				return condutor;
			}
		}
		
		//seleciona o condutor solteiro e sem filho
		for (Condutor condutor : homens) {
			if(condutor.ehSolteiro() && condutor.temNaoFilho()){
				return condutor;
			}
		}
		
		//seleciona a condutora solteira e sem filho
		for (Condutor condutor : mulheres) {
			if(condutor.ehSolteiro() && condutor.temNaoFilho()){
				return condutor;
			}
		}
		
		//seleciona o condutor solteiro com filho
		for (Condutor condutor : homens) {
			if(condutor.ehSolteiro() && !condutor.temNaoFilho()){
				return condutor;
			}
		}
		
		//seleciona a condutora solteira com filho
		for (Condutor condutor : mulheres) {
			if(condutor.ehSolteiro() && !condutor.temNaoFilho()){
				return condutor;
			}
		}
		
		//os demais casos n‹o influenciam no c‡lculo do seguro, ent‹o retorna qualquer condutora se houver.
		if (mulheres.size() > 0) {
			return mulheres.get(0);
		} else if (homens.size() > 0) { //testa s— para n‹o causar erro
			return homens.get(0);
		} else {
			return null;
		}
		
	}

}
