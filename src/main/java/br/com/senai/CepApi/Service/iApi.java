package br.com.senai.CepApi.Service;

public interface iApi {
    <T> T obterDados(String json, Class<T>tClass);
}
