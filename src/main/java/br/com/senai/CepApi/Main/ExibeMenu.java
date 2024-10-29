package br.com.senai.CepApi.Main;

import br.com.senai.CepApi.Model.Endereco;
import br.com.senai.CepApi.Model.EnderecoRecord;
import br.com.senai.CepApi.Service.ConsultaApi;
import br.com.senai.CepApi.Service.ConverterDados;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExibeMenu {

    private Scanner input = new Scanner(System.in);
    private ConsultaApi consultaApi = new ConsultaApi();
    private ConverterDados converterDados = new ConverterDados();
    private String urlAPI = "viacep.com.br/ws/";

    private List<Endereco> enderecoList = new ArrayList<>();

    public void rodarAplicacao () {
        System.out.println("Bem vindo ao sistema de consulta e cadastro de CEP's");
        while (true){
            System.out.print("""
                    Qual ação você deseja realizar?
                    1 - Cadastrar
                    2 - Listar
                    3 - Editar
                    4 - Remover
                    5 - Buscar por ID
                    6 - Buscar por CEP
                    7 - Buscar por Cidade
                    8 - Buscar por UF
                    
                    0 - Fechar o sistema
                    -->""" + " ");
            var escolha = input.nextInt();
            input.nextLine();

            switch (escolha) {
                case 0:
                    System.out.println("Sistema finalizado!");
                    return;
                case 1:
                    cadastrarEndereco();
                    break;
                case 2:
                    listarEndereco();
                    break;
                case 3:
                    editarEndereco();
                    break;
                case 4:
                    removerEndereco();
                    break;
                case 5:
                    buscarEnderecoPorID();
                    break;
                case 6:
                    buscarEnderencoPorCEP();
                    break;
                case 7:
                    buscarEnderecoPorCidade();
                    break;
                case 8:
                    buscarEnderecoPorUF();
                    break;
                default:
                    System.out.println("Valor digitádo é inválido, tente novamente");
            }
        }
    }
    public void cadastrarEndereco(){
        System.out.println("Digite qual o CEP que você deseja cadastrar");
        var cepBuscado = input.nextLine();
        var json = consultaApi.obterDados(urlAPI + cepBuscado + "/json");
        EnderecoRecord enderecoRegistrado = converterDados.obterDados(json, EnderecoRecord.class);
        System.out.println(enderecoRegistrado);

    }

    public void listarEndereco(){

    }

    public void editarEndereco() {

    }

    public void removerEndereco() {

    }

    public void buscarEnderecoPorID() {

    }

    public void buscarEnderencoPorCEP() {

    }

    public  void  buscarEnderecoPorCidade() {

    }

    public void buscarEnderecoPorUF() {

    }
}
