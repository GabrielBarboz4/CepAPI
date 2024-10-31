package br.com.senai.CepApi.Model;

import jakarta.persistence.*;

@Entity @Table(name = "endereco")
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String uf;
    private String estado;

    public Endereco(EnderecoRecord endereco) {
        this.id = getId();
        this.cep = endereco.cep();
        this.rua = endereco.rua();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.estado = endereco.estado();
        this.uf = endereco.uf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco() {}

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return  "\nCEP: " + cep +
                "\nRua: " + rua +
                "\nBairro: " + bairro +
                "\nCidade: " + cidade +
                "\nUF: " + uf +
                "\nEstado: " + estado+ "\n";
    }
}
