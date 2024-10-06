package br.com.alura.tabela_fipe_service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.tabela_fipe_service.model.DadosBasicos;
import br.com.alura.tabela_fipe_service.model.Modelos;
import br.com.alura.tabela_fipe_service.model.Veiculo;
import br.com.alura.tabela_fipe_service.principal.Console;
import br.com.alura.tabela_fipe_service.service.ConsumoApi;
import br.com.alura.tabela_fipe_service.service.ConverteDados;

@SpringBootApplication
public class TabelaFipeServiceApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		ConverteDados converteDados = new ConverteDados();
		Console console = new Console();

		String endpoint;
		String json;


		String tipoVeiculo = console.obterTipoVeiculo();

		// consultar marcas
		endpoint = tipoVeiculo + "/marcas";
		json = consumoApi.consultar(endpoint);
		List<DadosBasicos> listaMarcas = converteDados.converterLista(json, DadosBasicos.class);

		String codigoMarca = console.obterCodigoMarca(listaMarcas);

		// consultar modelos
		endpoint = tipoVeiculo + "/marcas/" + codigoMarca + "/modelos";
		json = consumoApi.consultar(endpoint);
		Modelos listaModelos = converteDados.converterObjeto(json, Modelos.class);

		// filtrar carro
		String codigoCarro = console.obterCodigoCarro(listaModelos.modelos());
		
		// consultar anos
		endpoint = tipoVeiculo + "/marcas/" + codigoMarca + "/modelos/" + codigoCarro + "/anos";
		json = consumoApi.consultar(endpoint);
		List<DadosBasicos> listaAnos = converteDados.converterLista(json, DadosBasicos.class);

		// montar de lista veículos por ano do modelo
		List<Veiculo> listVeiculos = new ArrayList<Veiculo>();
		for (int i = 1; i < listaAnos.size(); i++) {
			endpoint = tipoVeiculo + "/marcas/" + codigoMarca + "/modelos/" + codigoCarro + "/anos/" + listaAnos.get(i).codigo();
			json = consumoApi.consultar(endpoint);
			Veiculo veiculo = converteDados.converterObjeto(json, Veiculo.class);
			listVeiculos.add(veiculo);
		}
		console.exibirComparacaoVeiculos(listVeiculos);
	}
}