package br.com.alura.tabela_fipe_service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.tabela_fipe_service.model.Marca;
import br.com.alura.tabela_fipe_service.model.Modelo;
import br.com.alura.tabela_fipe_service.service.ConsumoApi;
import br.com.alura.tabela_fipe_service.service.ConverteDados;

@SpringBootApplication
public class TabelaFipeServiceApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		ConsumoApi consumoApi = new ConsumoApi();
		ConverteDados converteDados = new ConverteDados();
		String endpoint;
		String json;

		Optional<Marca> findMarcaDesejada;
		
		// digitar tipo de veículo
		boolean tipoVeiculoValido = false;
		String tipoVeiculo = "";
		while (!tipoVeiculoValido) {
			System.out.println("Digite o tipo de veículo:");
			System.out.println("Carros");
			System.out.println("Motos");
			System.out.println("Caminhoes");
			tipoVeiculo = scanner.nextLine();

			if (
				tipoVeiculo.equalsIgnoreCase("carros") ||
				tipoVeiculo.equalsIgnoreCase("motos") ||
				tipoVeiculo.equalsIgnoreCase("caminhoes")
			) {
				tipoVeiculoValido = true;
			} else {
				System.out.println("Digite um tipo de veículo válido!");
			}
		}

		// consultar marcas
		endpoint = tipoVeiculo + "/marcas";
		json = consumoApi.consultar(endpoint);
		List<Marca> listaMarcas = converteDados.converterLista(json, Marca.class);

		// digitar código da marca
		boolean codigoMarcaValido = false;
		String codigoMarca = "";
		while (!codigoMarcaValido) {
			System.out.println("Digite o código da marca:");
			listaMarcas.forEach(System.out::println);
			codigoMarca = scanner.nextLine();

			findMarcaDesejada = listaMarcas.stream().filter(marca -> marca.codigo().equals(codigoMarca)).findFirst();

			if (findMarcaDesejada.isPresent()) {
				codigoMarcaValido = true;
			} else {
				System.out.println("Digite um código de marca válido!");
			}
		}

		// consultar modelos
		endpoint = tipoVeiculo + "/marcas/" + codigoMarca + "/modelos";
		json = consumoApi.consultar(endpoint);
		List<Modelo> listaModelos = converteDados.converterLista(json, Modelo.class); 
		listaModelos.forEach(System.out::println);

		scanner.close();
	}
}