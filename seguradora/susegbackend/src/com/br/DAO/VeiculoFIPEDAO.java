package com.br.DAO;

public class VeiculoFIPEDAO {

	private String fipeCodigo, nome, marca, combustivel;
	private int anoModelo;
	private double preco;
	
	public VeiculoFIPEDAO(String fipeCodigo, int anoModelo, double preco, String nome, String marca, String combustivel) {
		this.fipeCodigo = fipeCodigo;
		this.nome = nome;
		this.marca = marca;
		this.combustivel = combustivel;
		this.anoModelo = anoModelo;
		this.preco = preco;
	}
	
	public void save() {
		//TODO: criar a tabela VeiculoFIPE no banco (talvez uma MarcaFIPE também) e fazer salvar o veículo lá
	}

	public String getFipeCodigo() {
		return fipeCodigo;
	}

	public void setFipeCodigo(String fipeCodigo) {
		this.fipeCodigo = fipeCodigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public int getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
