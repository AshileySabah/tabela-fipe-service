package br.com.alura.tabela_fipe_service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(
    @JsonAlias("modelos") List<DadosBasicos> modelos
) {}