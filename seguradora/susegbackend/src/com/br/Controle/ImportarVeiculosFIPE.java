package com.br.Controle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.br.DAO.VeiculoFIPEDAO;

public class ImportarVeiculosFIPE {

	public static String chamaUrl(String url) throws IOException {
		URL fipe = new URL(url);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fipe.openStream()));
		String retornoJson;
		StringBuilder builder = new StringBuilder();
		while ((retornoJson = bufferedReader.readLine()) != null){
			builder.append(retornoJson);
		}
		bufferedReader.close();
		return builder.toString();
	}
	
	private static void carregaVeiculo(Long idMarca, String idVeiculo, String idModelo) throws IOException, ParseException {
		String jsonVeiculo = chamaUrl("http://fipeapi.appspot.com/api/1/carros/veiculo/" + idMarca + "/" + idVeiculo + "/" + idModelo + ".json");
		JSONParser parser = new JSONParser();
		JSONObject veiculo = (JSONObject) parser.parse(jsonVeiculo);
		Map map = (Map) veiculo;
		String fipeCodigo, nome, marca, combustivel;
		int anoModelo;
		double preco;
		fipeCodigo = (String) map.get("fipe_codigo");
		anoModelo = Integer.parseInt((String) map.get("ano_modelo"));
		preco = 0; //TODO: transformar R$ em double
		nome = (String) map.get("name");
		marca = (String) map.get("marca");
		combustivel = (String) map.get("combustivel");
		VeiculoFIPEDAO dao = new VeiculoFIPEDAO(fipeCodigo, anoModelo, preco, nome, marca, combustivel);
		dao.save();
	}
	
	public static void carregaModelos(Long idMarca, String idVeiculo) throws IOException, ParseException{
		String jsonModelos = chamaUrl("http://fipeapi.appspot.com/api/1/carros/veiculo/" + idMarca + "/" + idVeiculo + ".json");
		JSONParser parser = new JSONParser();
		JSONArray arrayModelos = (JSONArray) parser.parse(jsonModelos);
		for (Object object : arrayModelos) {
			Map map = (Map) object;
			String id = (String) map.get("id");
			carregaVeiculo(idMarca, idVeiculo, id);
		}
	}

	public static void carregaVeiculos(Long idMarca) throws IOException, ParseException{
		String jsonVeiculos = chamaUrl("http://fipeapi.appspot.com/api/1/carros/veiculos/" + idMarca + ".json");
		JSONParser parser = new JSONParser();
		JSONArray arrayModelos = (JSONArray) parser.parse(jsonVeiculos);
		for (Object object : arrayModelos) {
			Map map = (Map) object;
			String id = (String) map.get("id");
			carregaModelos(idMarca, id);
		}
	}
	
	public static void carregaTodosVeiculosDeTodasMarcas() throws IOException, ParseException{
		String jsonMarcas = chamaUrl("http://fipeapi.appspot.com/api/1/carros/marcas.json");
		JSONParser parser = new JSONParser();
		JSONArray arrayMarcas = (JSONArray) parser.parse(jsonMarcas);
		for (Object object : arrayMarcas) {
			Map map = (Map) object;
			Long id = (Long) map.get("id");
			String marca = (String) map.get("fipe_name");
			carregaVeiculos(id);
		}
	}

	public static void main(String[] args) {
		try {
			carregaTodosVeiculosDeTodasMarcas();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
