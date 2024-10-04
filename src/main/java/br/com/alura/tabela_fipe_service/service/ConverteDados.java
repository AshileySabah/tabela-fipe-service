package br.com.alura.tabela_fipe_service.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;


public class ConverteDados {
    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T converterObjeto(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> converterLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory()
            .constructCollectionType(List.class, classe);

        try {
            return mapper.readValue(json, lista);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}