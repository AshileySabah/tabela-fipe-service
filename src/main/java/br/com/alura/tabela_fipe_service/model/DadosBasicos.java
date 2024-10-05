package br.com.alura.tabela_fipe_service.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosBasicos(
    @JsonAlias("codigo") String codigo,
    @JsonAlias("nome") String descricao
) {
    @Override
    public final String toString() {
        return String.format("COD: %s - DESCRICAO: %s", this.codigo, this.descricao);
    }
}