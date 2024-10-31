package br.com.senai.CepApi.Controller;

import br.com.senai.CepApi.Main.ExibeMenu;
import br.com.senai.CepApi.Model.Endereco;
import br.com.senai.CepApi.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final ExibeMenu exibeMenu;

    @Autowired
    public EnderecoController(EnderecoRepository repository) {
        this.exibeMenu = new ExibeMenu(repository);
    }

    @GetMapping("/listar")
    public void listarEnderecos() {
        exibeMenu.listarEndereco();
    }

    @PostMapping("/cadastrar")
    public Endereco cadastrarEndereco(@RequestBody Endereco endereco) {
        exibeMenu.cadastrarEndereco(endereco);
        return endereco;
    }

    @PutMapping("/editar")
    public void editarEndereco() {
        exibeMenu.editarEndereco();
    }

    @DeleteMapping("/remover")
    public void removerEndereco() {
        exibeMenu.removerEndereco();
    }

    // Outros endpoints conforme necessário
}
