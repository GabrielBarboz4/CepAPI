package br.com.senai.CepApi.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoRecord(    @JsonAlias("cep") String cep,
                                 @JsonAlias("logradouro") String rua,
                                 @JsonAlias("bairro") String bairro,
                                 @JsonAlias("localidade") String cidade,
                                 @JsonAlias("estado") String estado,
                                 @JsonAlias("uf") String uf
) {}