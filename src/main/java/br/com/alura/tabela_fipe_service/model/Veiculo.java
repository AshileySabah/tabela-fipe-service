package br.com.alura.tabela_fipe_service.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
    @JsonAlias("Valor") String valor,
    @JsonAlias("Marca") String marca,
    @JsonAlias("Modelo") String modelo,
    @JsonAlias("AnoModelo") Integer ano,
    @JsonAlias("Combustivel") String combustivel,
    @JsonAlias("CodigoFipe") String codigoFipe
) {
    @Override
    public final String toString() {
        return String.format(
            """
            COD: %s
            Ano: %d
            Valor: %s
            """, this.codigoFipe, this.ano, this.valor);
    }
}