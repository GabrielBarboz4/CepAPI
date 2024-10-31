package br.com.senai.CepApi.Main;

import br.com.senai.CepApi.Controller.EnderecoController;
import br.com.senai.CepApi.Model.Endereco;
import br.com.senai.CepApi.Model.EnderecoRecord;
import br.com.senai.CepApi.Repository.EnderecoRepository;
import br.com.senai.CepApi.Service.ConsultaApi;
import br.com.senai.CepApi.Service.ConverterDados;

import java.util.*;

public class ExibeMenu {

    private final Scanner input = new Scanner(System.in);
    private final ConsultaApi consultaApi = new ConsultaApi();
    private final ConverterDados converterDados = new ConverterDados();
    private final EnderecoRepository repository;
    private final String urlAPI = "https://viacep.com.br/ws/";

    private List<Endereco> enderecoList = new ArrayList<>();

    public ExibeMenu(EnderecoRepository repository) {
        this.repository = repository;
    }

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
        EnderecoRecord record = getDadosCep();
        Endereco enderecoRegistrado = new Endereco(record);
        repository.save(enderecoRegistrado);
        System.out.println("Endereço registrado!");
        System.out.println();
    }

    private EnderecoRecord getDadosCep() {
        System.out.println("Digite qual o CEP que você deseja cadastrar");
        var cepBuscado = input.nextLine();
        var json = consultaApi.obterDados(urlAPI + cepBuscado.replace("-", "") + "/json");
        EnderecoRecord enderecoRegistrado = converterDados.obterDados(json, EnderecoRecord.class);
        return enderecoRegistrado;
    }

    public void listarEndereco(){
        enderecoList = repository.findAll();
        System.out.println("Endereços registrados:");
        enderecoList.forEach(System.out::println);
    }

    public void editarEndereco() {
        List<Endereco> cepsListados = repository.findAll();
        System.out.println("CEPs registrados no sistema:");
        cepsListados.forEach(endereco -> System.out.printf("CEP: %s\n", endereco.getCep()));

        System.out.println("Qual dos CEPs vc deseja editar?");
        var cep = input.nextLine();
        Optional<Endereco> cepBuscado = repository.findByCepBuscado(cep);

        if (cepBuscado.isPresent()) {
            Endereco endereco = cepBuscado.get();
            String novaCidade;
            String novoBairro;
            String novaRua;

            System.out.print("""
                    Digite qual das opções você deseja alterar
                    1 - Editar Rua
                    2 - Editar Bairro
                    3 - Editar Cidade
                    4 - Editar os 3 parâmetros
                    --> """ + " ");
            var novaEscolha = input.nextInt();
            input.nextLine();
            switch (novaEscolha) {
                case 1:
                    System.out.println("Digite o nome da Rua:");
                    novaRua = input.nextLine();
                    endereco.setRua(novaRua);
                    repository.save(endereco);
                    break;
                case 2:
                    System.out.println("Digite o novo bairro:");
                    novoBairro = input.nextLine();
                    endereco.setRua(novoBairro);
                    repository.save(endereco);
                    break;
                case 3:
                    System.out.println("Digite a nova cidade:");
                    novaCidade = input.nextLine();
                    endereco.setRua(novaCidade);
                    repository.save(endereco);
                    break;
                case 4:
                    System.out.println("Digite o nome da Rua:");
                    novaRua = input.nextLine();

                    System.out.println("Digite o novo bairro:");
                    novoBairro = input.nextLine();

                    System.out.println("Digite a nova cidade:");
                    novaCidade = input.nextLine();

                    endereco.setRua(novaRua);
                    endereco.setBairro(novoBairro);
                    endereco.setCidade(novaCidade);

                    repository.save(endereco);
                    System.out.println("Endereço atualizado com sucesso!");
                    break;
                default:
                    System.out.println("Valor digitado é inválido, retornando ao menu principal");
                    break;
            }
        }
    }

    public void removerEndereco() {
        List<Endereco> cepsListados = repository.findAll();
        System.out.println("CEPs registrados no sistema:");
        cepsListados.forEach(endereco -> System.out.printf("CEP: %s\n", endereco.getCep()));

        System.out.println("Qual dos CEPs vc deseja Remover?");
        var cep = input.nextLine();
        Optional<Endereco> cepBuscado = repository.findByCepBuscado(cep);

        if (cepBuscado.isPresent()) {
            repository.delete(cepBuscado.get());
            System.out.println("Endereço removido com sucesso");
        } else {
            System.out.println("Endereço não encontrado");
        }
    }

    public void buscarEnderecoPorID() {
        listarEndereco();
    }

    public void buscarEnderencoPorCEP() {
        List<Endereco> cepsListados = repository.findAll();
        System.out.println("CEPs registrados no sistema:");
        cepsListados.forEach(endereco -> System.out.printf("CEP: %s\n", endereco.getCep()));

        System.out.println("Qual dos CEPs vc deseja especificiar?");
        var cep = input.nextLine();
        Optional<Endereco> cepBuscado = repository.findByCepBuscado(cep);

        if (cepBuscado.isPresent()) {
            cepBuscado.ifPresent(endereco -> System.out.printf("Endereço para o CEP: %s \nRua: %s \nBairro: %s \nCidade: %s \nEstado: %s ", endereco
                            .getCep(), endereco.getRua(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado()));
        } else {
            System.out.println("Cidade não encontrada");
        }
        System.out.println();
    }

    public  void  buscarEnderecoPorCidade() {
        List<Endereco> cidadesListados = repository.findAll();
        System.out.println("Cidades registradas no sistema:");
        cidadesListados.forEach(endereco -> System.out.printf("Cidade: %s\n", endereco.getCidade()));

        System.out.println("Qual das cidades vc deseja especificiar?");
        var cidade = input.nextLine();
        List<Endereco> cidadeBuscada = repository.findByCidadeBuscada(cidade);

        if (!cidadeBuscada.isEmpty()) {
            cidadeBuscada.forEach(endereco -> System.out.printf("Endereço para o CEP: %s \nRua: %s \nBairro: %s \nCidade: %s \nEstado: %s", endereco
                            .getCep(), endereco.getRua(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado()));
        } else {
            System.out.println("Cidade não encontrada");
        }
        System.out.println();
    }

    public void buscarEnderecoPorUF() {
        List<Endereco> ufListados = repository.findAll();
        System.out.println("UFs registradas no sistema:");
        ufListados.forEach(endereco -> System.out.printf("UF: %s\n", endereco.getUf()));

        System.out.println("Qual das UFs vc deseja especificiar?");
        var uf = input.nextLine();
        List<Endereco> ufBuscada = repository.findByUfBuscada(uf);

        if (!ufBuscada.isEmpty()) {
            ufBuscada.forEach(endereco -> System.out.printf("\nEndereço para o CEP: %s \nRua: %s \nBairro: %s \nCidade: %s \nEstado: %s", endereco
                            .getCep(), endereco.getRua(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado()));
        } else {
            System.out.println("Cidade não encontrada");
        }
        System.out.println();
    }

    public void cadastrarEndereco(Endereco endereco) {
    }
}
