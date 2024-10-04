package br.com.alura.tabela_fipe_service.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Marca(
    @JsonAlias("codigo") String codigo,
    @JsonAlias("nome") String nome
) {
    @Override
    public final String toString() {
        return String.format("COD: %s - MARCA: %s", this.codigo, this.nome);
    }
}