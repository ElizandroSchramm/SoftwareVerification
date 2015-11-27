package com.br.Model;

public class Veiculo {
	
	private int anoFabricacao, anoModelo;
	private double valorFIP;
	
	public Veiculo() {
		this.anoFabricacao = 2004;
		this.anoModelo = 2005;
		this.valorFIP = 35000;
	}
	
	public double getValorFIP() {
		return valorFIP;
	}
	public void setValorFIP(double valorFIP) {
		this.valorFIP = valorFIP;
	}
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacoa(int anoFabricacoa) {
		this.anoFabricacao = anoFabricacoa;
	}
	public int getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

}
