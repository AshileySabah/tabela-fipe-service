package br.com.alura.tabela_fipe_service.principal;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import br.com.alura.tabela_fipe_service.model.DadosBasicos;
import br.com.alura.tabela_fipe_service.model.Modelos;

public class Console {
    private Scanner scanner = new Scanner(System.in);

    public String obterTipoVeiculo() {
        boolean tipoVeiculoValido = false;
        String tipoVeiculo = "";

        while (!tipoVeiculoValido) {
            System.out.println("Tipos de veículo:");
            System.out.println("Carros");
            System.out.println("Motos");
            System.out.println("Caminhoes");
            System.out.println("Digite o tipo de veículo:");
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

        return tipoVeiculo;
    }

    public String obterCodigoMarca(List<DadosBasicos> listaMarcas) {
		boolean codigoMarcaValido = false;
        String finalCodigoMarca = "";

		while (!codigoMarcaValido) {
			System.out.println("Marcas:");
			listaMarcas.forEach(System.out::println);
			System.out.println("Digite o código da marca:");
			String codigoMarca = scanner.nextLine();

			Optional<DadosBasicos> findMarcaDesejada = listaMarcas.stream()
                .filter(marca -> marca.codigo().equals(codigoMarca))
                .findFirst();

			if (findMarcaDesejada.isPresent()) {
				codigoMarcaValido = true;
                finalCodigoMarca = codigoMarca;
			} else {
				System.out.println("Digite um código de marca válido!");
			}
		}

        return finalCodigoMarca;
    }

    public String obterCodigoCarro(List<DadosBasicos> listaModelos) {
        listaModelos.forEach(System.out::println);
        System.out.println("Digite um trecho do nome do veículo:");
        String trechoNomeVeiculo = scanner.nextLine().toLowerCase();

        List<DadosBasicos> listaFiltradaNomeVeiculo = listaModelos.stream()
            .filter(modelo -> modelo.descricao().toLowerCase().contains(trechoNomeVeiculo))
            .sorted(Comparator.comparing(modelo -> Integer.parseInt(modelo.codigo()))).toList();

        boolean codigoCarroValido = false;
        String finalCodigoCarro = "";

		while (!codigoCarroValido) {
			System.out.println("Veículos encontrados:");
			listaFiltradaNomeVeiculo.forEach(System.out::println);
			System.out.println("Digite o código do veículo:");
			String codigoCarro = scanner.nextLine();

			Optional<DadosBasicos> findCarroDesejado = listaFiltradaNomeVeiculo.stream()
                .filter(modelo -> modelo.codigo().equals(codigoCarro))
                .findFirst();

			if (findCarroDesejado.isPresent()) {
				codigoCarroValido = true;
                finalCodigoCarro = codigoCarro;
			} else {
				System.out.println("Digite um código de veículo válido!");
			}
		}

        return finalCodigoCarro;
    }
}