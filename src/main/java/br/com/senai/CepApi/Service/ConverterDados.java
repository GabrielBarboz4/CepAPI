package br.com.senai.CepApi.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterDados implements iApi {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T obterDados(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
